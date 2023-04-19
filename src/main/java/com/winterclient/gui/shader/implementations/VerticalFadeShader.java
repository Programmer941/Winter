package com.winterclient.gui.shader.implementations;

import com.winterclient.gui.shader.Shader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

import static net.minecraft.client.renderer.GlStateManager.resetColor;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL20.glUniform1;

public class VerticalFadeShader extends Shader {

    Framebuffer framebuffer;

    public VerticalFadeShader() {
        super("/assets/shaders/vertical.vert", "/assets/shaders/vertical.frag");
        framebuffer=new Framebuffer(1,1,false);
    }

    public void setupUniforms(float bottom) {
        setUniformi("textureIn", 0);
        setUniformf("texelSize", 1.0F / (float) Minecraft.getMinecraft().getFramebuffer().framebufferWidth, 1.0F / (float) Minecraft.getMinecraft().getFramebuffer().framebufferHeight);
        setUniformf("bottom",bottom);
    }

    public void renderVerticalFade(Framebuffer buffer,float amount){
        Minecraft mc = Minecraft.getMinecraft();
        GlStateManager.enableBlend();
        GlStateManager.color(1, 1, 1, 1);
        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);

        framebuffer = createFrameBuffer(framebuffer, mc.displayWidth, mc.displayHeight);
        framebuffer.bindFramebuffer(true);

        load();
        drawQuads(0,0, buffer.framebufferWidth, buffer.framebufferHeight, 0, 0, buffer.framebufferWidth, buffer.framebufferHeight, mc.displayWidth, mc.displayHeight);
        unload();

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

    public Framebuffer createFrameBuffer(Framebuffer framebuffer, int width, int height) {
        if (framebuffer == null || framebuffer.framebufferWidth != width || framebuffer.framebufferHeight != height) {
            if (framebuffer != null) {
                framebuffer.deleteFramebuffer();
            }
            return new Framebuffer(width, height, true);
        }
        return framebuffer;
    }
}
