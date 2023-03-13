package com.winterclient.gui.util;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class RenderUtil {

    public static void color(Color c) {
        glColor4f(c.getRed()/255f, c.getGreen()/255f, c.getBlue()/255f, c.getAlpha()/255f);
    }

    //Draw Shapes
    public static void drawLine(float x1, float y1, float x2, float y2, int width, Color color) {
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

    public static void drawRect(float x, float y, float width, float height, Color color) {
//        color(color);
//
//        GlStateManager.pushMatrix();
//        GlStateManager.disableTexture2D();
//        GlStateManager.enableAlpha();
//        GlStateManager.enableBlend();
//        GlStateManager.disableCull();
//        GlStateManager.enableColorMaterial();
//        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 0, 1);
//        glBegin(GL_QUADS);
//        glVertex2f(x, y);
//        glVertex2f(x + width, y);
//        glVertex2f(x + width, y + height);
//        glVertex2f(x, y + height);
//        glEnd();
//        GlStateManager.enableCull();
//        GlStateManager.disableAlpha();
//        GlStateManager.disableBlend();
//        GlStateManager.disableColorMaterial();
//        GlStateManager.enableTexture2D();
//        GlStateManager.popMatrix();

        float curved=4;
        color(color);

        if(curved>=height/2) curved = height/2;
        if(curved>=width/2) curved = width/2;
        int step= (int) curved;

        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 0, 1);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);

        for (int i = step; i >= 0; --i) {
            final float angle = i * 90/step+90;
            GL11.glVertex2f((float)(x + curved + curved * Math.cos(Math.toRadians(angle))), (float)(y + curved - curved * Math.sin(Math.toRadians(angle))));

        }
        for (int i = step; i >= 0; --i) {
            final float angle = i * 90/step;
            GL11.glVertex2f((float)(x+width - curved+ curved * Math.cos(Math.toRadians(angle))), (float)(y + curved- curved * Math.sin(Math.toRadians(angle))));
        }

        for (int i = step; i >= 0; --i) {
            final float angle = i * 90/step-90;
            GL11.glVertex2f((float)(x+width - curved+ curved * Math.cos(Math.toRadians(angle))), (float)(y+height - curved- curved * Math.sin(Math.toRadians(angle))));
        }

        for (int i = step; i >= 0; --i) {
            final float angle = i * 90/step+180;
            GL11.glVertex2f((float)(x + curved+ curved * Math.cos(Math.toRadians(angle))), (float)(y+height - curved- curved * Math.sin(Math.toRadians(angle))));
        }
        GL11.glEnd();
        GlStateManager.enableCull();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static void drawCircle(float x, float y, float radius, Color color) {
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
        for (int i = 0; i < radius; i++) {
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

    public static void drawRoundRect(int x, int y, int width, int height,Color color,int curved){
        color(color);

        if(curved>=height/2) curved = height/2;
        if(curved>=width/2) curved = width/2;
        int step=curved;

        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 0, 1);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);

        for (int i = step; i >= 0; --i) {
            final float angle = i * 90/step+90;
            GL11.glVertex2f((float)(x + curved + curved * Math.cos(Math.toRadians(angle))), (float)(y + curved - curved * Math.sin(Math.toRadians(angle))));

        }
        for (int i = step; i >= 0; --i) {
            final float angle = i * 90/step;
            GL11.glVertex2f((float)(x+width - curved+ curved * Math.cos(Math.toRadians(angle))), (float)(y + curved- curved * Math.sin(Math.toRadians(angle))));
        }

        for (int i = step; i >= 0; --i) {
            final float angle = i * 90/step-90;
            GL11.glVertex2f((float)(x+width - curved+ curved * Math.cos(Math.toRadians(angle))), (float)(y+height - curved- curved * Math.sin(Math.toRadians(angle))));
        }

        for (int i = step; i >= 0; --i) {
            final float angle = i * 90/step+180;
            GL11.glVertex2f((float)(x + curved+ curved * Math.cos(Math.toRadians(angle))), (float)(y+height - curved- curved * Math.sin(Math.toRadians(angle))));
        }
        GL11.glEnd();
        GlStateManager.enableCull();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

}
