package com.winterclient.mixins.implementations;

import com.winterclient.Winter;
import com.winterclient.event.implementations.OverlayEvent;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;
import com.winterclient.mod.implementations.Crosshair;
import com.winterclient.mod.implementations.Scoreboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;

@Mixin(GuiIngame.class)
public class MixinGuiInGame {

    @Overwrite
    private void renderScoreboard(ScoreObjective objective, ScaledResolution scaledRes){
        Scoreboard.instance.objective=objective;
    }

    @Inject(method = "renderGameOverlay", at = @At("TAIL"))
    public void renderGameOverlay(float partialTicks, CallbackInfo callbackInfo) {
        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());

        GlStateManager.clear(256);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, Display.getWidth(), Display.getHeight(), 0.0D, 1000.0D, 3000.0D);
        //GlStateManager.ortho(0.0D, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);

        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);

        Winter.instance.eventBus.fire(new OverlayEvent());
        GlStateManager.clear(256);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        //GlStateManager.ortho(0.0D, Display.getWidth(), Display.getHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.ortho(0.0D, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);

        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
    }

    @Overwrite
    protected boolean showCrosshair(){
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.gameSettings.showDebugInfo && !mc.thePlayer.hasReducedDebug() && !mc.gameSettings.reducedDebugInfo)
            return false;
        if(mc.thePlayer.isSpectator())
            return false;
        if(mc.currentScreen != null)
            return false;
        if(Crosshair.custom){
            GL11.glPushMatrix();
            Images.crosshair.draw(mc.displayWidth/2-12,mc.displayHeight/2-12,24,24);
            GL11.glPopMatrix();
            Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
            return false;
        }

        return true;
    }


//    @Overwrite
//    public void renderTooltip(ScaledResolution sr, float partialTicks) {
//        int width = Minecraft.getMinecraft().displayWidth;
//        int height = Minecraft.getMinecraft().displayHeight;
//        int padding = 4;
//        EntityPlayer entityplayer = (EntityPlayer) Minecraft.getMinecraft().getRenderViewEntity();
//        int currentSlot = entityplayer.inventory.currentItem;
//        //RenderUtil.drawRect(width / 2 - 180, height - 40, 360, 40, new Color(0x90000000,true));
//        Winter.instance.blurShader.renderBlur(width / 2 - 180, height - 40, 360, 40,9);
//        RenderUtil.drawRect(width / 2 - 180 + currentSlot * 40 + 2, height - 40 + 2, 36, 36, new Color(0x50ffffff,true));
//        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//        GlStateManager.enableRescaleNormal();
//        GlStateManager.enableBlend();
//        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
//        RenderHelper.enableGUIStandardItemLighting();
//        GlStateManager.enableAlpha();
//        GlStateManager.alphaFunc(516, 0.1F);
//        GlStateManager.blendFunc(770, 771);
//        int xPos = width / 2 - 180 + 4;
//        RenderUtil.drawLine(-10,-10,-10,-10,2,Color.white);
//        for (int i = 0; i < 9; i++) {
//            ItemStack itemstack = Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i];
//            GL11.glPushMatrix();
//            GL11.glTranslatef(xPos, height - 36, 1);
//            GL11.glScalef(2, 2, 1);
//            GL11.glTranslatef(-xPos, -height + 36, 1);
//            Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(itemstack, xPos, height - 40 + 4);
//            GL11.glPopMatrix();
//            xPos += 40;
//        }
//        xPos = width / 2 - 180 + 4;
//        for (int i = 0; i < 9; i++) {
//            ItemStack itemstack = Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i];
//            if (itemstack != null) {
//                if (itemstack.stackSize > 1)
//                    Fonts.ralewaySmallest.drawString(Integer.toString(itemstack.stackSize), xPos + 32 - Fonts.ralewaySmallest.getStringWidth(Integer.toString(itemstack.stackSize)), height - Fonts.ralewaySmallest.FONT_HEIGHT - 2);
//
//                if (itemstack.isItemDamaged()) {
//                    float durabilityPercent = 1 - ((float) itemstack.getItemDamage() / (float) itemstack.getMaxDamage());
//                    RenderUtil.drawLine(xPos, height - 4, xPos + 32, height - 4, 2, Color.red);
//                    RenderUtil.drawLine(xPos, height - 4, xPos + 32, height - 4, 2, new Color(0,1,0,durabilityPercent));
//                }
//            }
//            xPos += 40;
//        }
//        RenderHelper.disableStandardItemLighting();
//        GlStateManager.disableRescaleNormal();
//        GlStateManager.disableBlend();
//        GlStateManager.disableAlpha();
//        GlStateManager.disableRescaleNormal();
//        GlStateManager.disableLighting();
//
//        if(!Minecraft.getMinecraft().playerController.gameIsSurvivalOrAdventure())
//        Minecraft.getMinecraft().ingameGUI.renderExpBar(sr,0);
//
////        if(!Minecraft.getMinecraft().playerController.shouldDrawHUD())
////            Minecraft.getMinecraft().ingameGUI.drawSTATSWHY
//    }



//    @Inject(method = "renderPlayerStats", at = @At("HEAD"))
//    public void renderPlayerStats(ScaledResolution scaledRes, CallbackInfo callbackInfo) {
//
////        GL11.glPushMatrix();
////
////        GL11.glTranslatef(Minecraft.getMinecraft().displayWidth / 2, Minecraft.getMinecraft().displayHeight, 1);
////        GL11.glScalef(2f, 2f, 1);
////        GL11.glTranslatef(-Minecraft.getMinecraft().displayWidth / 2, -Minecraft.getMinecraft().displayHeight, 1);
//
//    }

//    @Inject(method = "renderPlayerStats", at = @At("TAIL"))
//    public void renderPlayerStatsEnd(ScaledResolution scaledRes, CallbackInfo callbackInfo) {
//        GL11.glPopMatrix();
//        if (BossStatus.bossName != null && BossStatus.statusBarTime > 0){
//            --BossStatus.statusBarTime;
//            int width = Minecraft.getMinecraft().displayWidth;
//            float percentHealth = BossStatus.healthScale;
//            int percentWidth = (int) (percentHealth*500f);
//            int stringWidth = Fonts.mcFont.getStringSize(BossStatus.bossName);
//            GL11.glPushMatrix();
//            Fonts.mcFont.drawString(BossStatus.bossName,width/2-stringWidth/2,4,-1,false);
//            GL11.glTranslatef(width/2-250, 0, 1);
//            GL11.glScalef(-1, -1, 1);
//            GL11.glTranslatef(-width/2-250, 0, 1);
//            Images.loadingBar.draw(width/2-250,-33-30-6-4, 500, 33, Color.white);
//            Images.loadingBar.draw(width / 2 - 250+percentWidth, -33-30-6-4, 500-percentWidth, 33,percentWidth,0,(500-percentWidth),33,new Color(69, 82, 134, 255));
//
//            GL11.glPopMatrix();
//            Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
//        }
//    }

//    @Overwrite
//    public void renderExpBar(ScaledResolution scaledRes, int x) {
//
//        Minecraft mc = Minecraft.getMinecraft();
//
//        if (mc.thePlayer.xpBarCap() > 0) {
//            int xpWidth = (int) (mc.thePlayer.experience * 360);
//            Images.loadingBar.draw(Minecraft.getMinecraft().displayWidth / 2 - 180, Minecraft.getMinecraft().displayHeight - 70, xpWidth, 24,0,0,(int) xpWidth/360f*500f,33, new Color(69, 82, 134, 255));
//            Images.loadingBar.draw(Minecraft.getMinecraft().displayWidth / 2 - 180+xpWidth, Minecraft.getMinecraft().displayHeight - 70, 360-xpWidth, 24,xpWidth/360f*500f,0,(360-xpWidth)/360f*500f,33,Color.white);
//
//        }
//
//        int level = mc.thePlayer.experienceLevel;
//        if (level > 0) {
//            Fonts.ralewaySmall.drawCenteredString(Integer.toString(level),mc.displayWidth/2,mc.displayHeight-104);
//        }
//
//    }


//    @Overwrite
//    public void renderBossHealth(){
////        if (BossStatus.bossName != null && BossStatus.statusBarTime > 0){
////            --BossStatus.statusBarTime;
////            int width = Minecraft.getMinecraft().displayWidth;
////            float percentHealth = BossStatus.healthScale;
////            int percentWidth = (int) (percentHealth*500f);
////            int stringWidth = Fonts.mcFont.getStringSize(BossStatus.bossName);
////            GL11.glPushMatrix();
////            Fonts.mcFont.drawString(BossStatus.bossName,width/2-stringWidth/2,4,-1,false);
////            GL11.glTranslatef(width/2-250, 0, 1);
////            GL11.glScalef(-1, -1, 1);
////            GL11.glTranslatef(-width/2-250, 0, 1);
////            Images.loadingBar.draw(width/2-250,-33-30-6+4, 500, 33, Color.white);
////            Images.loadingBar.draw(width / 2 - 250+percentWidth, -33-30-6+4, 500-percentWidth, 33,percentWidth,0,(500-percentWidth),33,new Color(69, 82, 134, 255));
////
////            GL11.glPopMatrix();
////            Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
////        }
//    }


}
