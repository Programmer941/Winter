package com.winterclient.gui.util;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class RenderUtil {

    public static void color(int color) {
        float alpha = (color >> 24 & 0xFF) / 255f;
        float red = (color >> 16 & 0xFF) / 255f;
        float green = (color >> 8 & 0xFF) / 255f;
        float blue = (color & 0xFF) / 255f;
        glColor4f(red, green, blue, alpha);
    }

    //Draw Shapes
    public static void drawLine(float x1, float y1, float x2, float y2, int width, int color) {
        color(color);

        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 0, 1);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(width);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x2, y2);
        GL11.glEnd();
        GlStateManager.enableCull();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static void drawRect(float x, float y, float width, float height, int color) {
        color(color);

        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 0, 1);
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x+width, y);
        glVertex2f(x+width, y+height);
        glVertex2f(x, y+height);
        glEnd();
        GlStateManager.enableCull();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static void drawCircle(float x, float y, float radius, int color) {
        color(color);

        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 0, 1);
        glBegin(GL_POLYGON);
        for(int i = 0; i < radius; i++)
        {
            float theta = 2.0f * 3.1415926f * i / radius;//get the current angle

            float xx = (float) (radius * Math.cos(theta));//calculate the x component
            float yy = (float) (radius * Math.sin(theta));//calculate the y component

            glVertex2f(x + xx, y + yy);//output vertex

        }
        glEnd();
        GlStateManager.enableCull();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

}
