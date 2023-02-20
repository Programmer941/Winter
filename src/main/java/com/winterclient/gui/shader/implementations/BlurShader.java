package com.winterclient.gui.shader.implementations;

import com.winterclient.gui.shader.Shader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

import static net.minecraft.client.renderer.GlStateManager.resetColor;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glUniform1;

public class BlurShader extends Shader {

    Framebuffer framebuffer;
    public BlurShader() {
        super("/assets/shaders/blur.vert", "/assets/shaders/blur.frag");
        framebuffer=new Framebuffer(1,1,false);
    }

    public void setupUniforms(float dir1, float dir2, float radius) {
        setUniformi("textureIn", 0);
        setUniformf("texelSize", 1.0F / (float) Minecraft.getMinecraft().getFramebuffer().framebufferWidth, 1.0F / (float) Minecraft.getMinecraft().getFramebuffer().framebufferHeight);
        setUniformf("direction", dir1, dir2);
        setUniformf("radius", radius);

        final FloatBuffer weightBuffer = BufferUtils.createFloatBuffer(256);
        for (int i = 0; i <= radius; i++) {
            weightBuffer.put(calculateGaussianValue(i, radius / 2f));
        }

        weightBuffer.rewind();
        glUniform1(getUniform("weights"), weightBuffer);
    }

    public void renderBlur(int x,int y,int width,int height,float radius){
        Minecraft mc = Minecraft.getMinecraft();
        GlStateManager.enableBlend();
        GlStateManager.color(1, 1, 1, 1);
        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);

        framebuffer = createFrameBuffer(framebuffer, mc.displayWidth, mc.displayHeight);
        framebuffer.framebufferClear();
        framebuffer.bindFramebuffer(true);

        load();
        setupUniforms(1, 0, radius);
        GL11.glBindTexture(GL_TEXTURE_2D,mc.getFramebuffer().framebufferTexture);

        drawQuads(x-15, y-15, width+30, height+30, x-15, y-15, width+30, height+30, mc.getFramebuffer().framebufferWidth, mc.getFramebuffer().framebufferHeight);
        framebuffer.unbindFramebuffer();
        unload();


        mc.getFramebuffer().bindFramebuffer(true);
        load();
        setupUniforms(0, 1, radius);

        GL11.glBindTexture(GL_TEXTURE_2D,framebuffer.framebufferTexture);
        drawQuads(x, y, width, height, x, y, width, height, mc.getFramebuffer().framebufferWidth, mc.getFramebuffer().framebufferHeight);
        unload();
        resetColor();
        GlStateManager.bindTexture(0);
    }

    public void drawFirstBuffer() {
        float width = Minecraft.getMinecraft().displayWidth;
        float height = Minecraft.getMinecraft().displayHeight;
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(0, 0);
        glTexCoord2f(0, 1);
        glVertex2f(0, height);
        glTexCoord2f(1, 1);
        glVertex2f(width, height);
        glTexCoord2f(1, 0);
        glVertex2f(width, 0);
        glEnd();
    }

    public void drawQuads(float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight,float imageWidth,float imageHeight) {
        glBegin(GL_TRIANGLES);
        float renderSRCX = srcX / imageWidth;
        float renderSRCY = 1-srcY / imageHeight;
        float renderSRCWidth = srcWidth / imageWidth;
        float renderSRCHeight = -srcHeight / imageHeight;
        glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        glVertex2f(x + width, y);
        glTexCoord2f(renderSRCX, renderSRCY);
        glVertex2f(x, y);
        glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        glVertex2f(x, y + height);
        glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        glVertex2f(x, y + height);
        glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
        glVertex2f(x + width, y + height);
        glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        glVertex2f(x + width, y);
        glEnd();
    }
    public void drawSprite(float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight) {
        glBegin(GL_TRIANGLES);
        float renderSRCX = srcX / srcWidth;
        float renderSRCY = 1-(srcY / srcHeight);
        float renderSRCWidth = width / srcWidth;
        float renderSRCHeight = -height / srcHeight;
        glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        glVertex2f(x + width, y);
        glTexCoord2f(renderSRCX, renderSRCY);
        glVertex2f(x, y);
        glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        glVertex2f(x, y + height);
        glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        glVertex2f(x, y + height);
        glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
        glVertex2f(x + width, y + height);
        glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        glVertex2f(x + width, y);
        glEnd();
    }
    public Framebuffer createFrameBuffer(Framebuffer framebuffer, int width, int height) {
        if (framebuffer == null || framebuffer.framebufferWidth != width || framebuffer.framebufferHeight != height) {
            if (framebuffer != null) {
                framebuffer.deleteFramebuffer();
            }
            return new Framebuffer(width, height, true);
        }
        return framebuffer;
    }

    public float calculateGaussianValue(float x, float sigma) {
        double output = 1.0f / Math.sqrt(2.0f * Math.PI * (sigma * sigma));
        return (float) (output * (Math.exp(-(x * x) / (2.0f * (sigma * sigma)))));
    }
}
