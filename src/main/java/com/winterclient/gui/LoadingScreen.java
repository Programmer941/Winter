package com.winterclient.gui;

import com.winterclient.Winter;
import com.winterclient.gui.util.resources.Images;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class LoadingScreen {

    int totalProgress=10;
    int barWidth=500;
    int progressWidth=0;

    public void setProgress(int progress){
        float res = progress/(totalProgress*1f);
        progressWidth= (int) (barWidth*res);
        draw();
    }

    public void draw(){

        int width = Display.getWidth();
        int height = Display.getHeight();
        Framebuffer framebuffer = new Framebuffer(width, height, true);
        framebuffer.bindFramebuffer(false);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, width, height, 0.0D, -10.0D, 3000.0D);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Winter.instance.background.draw();
        Images.loadingBar.draw(177,Display.getHeight()-100,500,33,new Color(244, 219, 163, 140));

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        System.out.println(progressWidth);
        int scissorX=(int) (width/2-progressWidth/2f);
        GL11.glScissor(scissorX, 0, progressWidth, height);
        Images.loadingBar.draw(177,Display.getHeight()-100,500,33,new Color(0xF4DBA3));
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glPopMatrix();
        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(width, height);

        Display.update();
    }

}
