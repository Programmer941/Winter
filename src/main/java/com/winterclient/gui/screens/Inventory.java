package com.winterclient.gui.screens;

import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class Inventory extends WinterGuiScreen {

    @Override
    public void init() {
        drawBackground=false;
    }

    @Override
    public void end() {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        int scale=150;
        int playerX=width/2-310;
        int playerY= (int) (height/2+scale*0.535714286f)+60;
        int inventoryWidth=440;
        int inventoryHeight=320;
        drawEntityOnScreen(playerX,playerY, scale,playerX-mouseX,playerY-mouseY-scale*2+scale/3f+6,mc.thePlayer);
        //RenderUtil.drawRect(width/2-inventoryWidth/2,height/2-inventoryHeight/2,inventoryWidth,inventoryHeight,new Color(0x90000000,true));
        int boxSize=64;
        int offsetX=width/2-300+scale/2;
        int offsetY=height/2+boxSize+8;
        int offsetItem = (boxSize-16*3)/2;
        System.out.println(Minecraft.getMinecraft().thePlayer.inventory.mainInventory.length);
        for(int i=0;i<36;i++){
            int yIndex=(i+1)%9;
            ItemStack itemstack = Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i];
            RenderUtil.drawRect(offsetX,offsetY,boxSize,boxSize,new Color(0x90000000,true));
            GL11.glPushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glTranslatef(offsetX+offsetItem, offsetY+offsetItem, 1);
            GL11.glScalef(3, 3, 1);
            GL11.glTranslatef(-offsetX-offsetItem, -offsetY-offsetItem, 1);
            Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(itemstack, offsetX+offsetItem, offsetY+offsetItem);
            GL11.glPopMatrix();
            offsetX+=boxSize+8;
            if(yIndex==0){
                offsetY-=boxSize+8;
                offsetX=width/2-300+scale/2;
            }
        }
    }

    public void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
        ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
        ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntityWithPosYaw(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    @Override
    public void click(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void release(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void drag(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void type(char typedChar, int keyCode) {

    }
}
