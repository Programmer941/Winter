package com.winterclient.gui.util.image;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL14;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;

public class DefaultImage extends WinterGuiImage{

    public DefaultImage(BufferedImage image) {
        super(image);
    }

    public void draw(float x, float y, float width, float height) {
        draw(x, y, width, height, 0f, 0f, imageWidth, imageHeight);
    }

    public void draw(float x, float y, float width, float height, Color c) {
        draw(x, y, width, height, 0f, 0f, imageWidth, imageHeight, c);
    }

    public void draw(float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight) {
        draw(x, y, width, height, srcX, srcY, srcWidth, srcHeight, Color.white);
    }

    public void draw(float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight, Color c) {
        glPushMatrix();
        glDisable(GL_DEPTH_TEST);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.003921569F);
        glEnable(GL_BLEND);
        GlStateManager.enableBlend();
        GL14.glBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 0, 1);
        glColor4f(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, c.getAlpha() / 255f);
        glBindTexture(GL_TEXTURE_2D, textureID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        drawSprite(x, y, width, height, srcX, srcY, srcWidth, srcHeight);
        glPopMatrix();
    }

    protected void drawSprite(float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight) {
        glBegin(GL_TRIANGLES);
        float renderSRCX = srcX / imageWidth;
        float renderSRCY = srcY / imageHeight;
        float renderSRCWidth = srcWidth / imageWidth;
        float renderSRCHeight = srcHeight / imageHeight;
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
}
