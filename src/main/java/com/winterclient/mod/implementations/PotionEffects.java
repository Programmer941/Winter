package com.winterclient.mod.implementations;

import com.winterclient.event.Subscribe;
import com.winterclient.event.implementations.OverlayEvent;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.mod.HUDMod;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@Info(name = "Potion Effects", description = "Potion Effects where you can see them!", category = Category.Visual, enabled = true)
public class PotionEffects extends HUDMod {

    ResourceLocation potions = new ResourceLocation("textures/gui/container/inventory.png");

    public PotionEffects(int x, int y, int width, int height) {
        super(x, y, width, height, true);
    }

    @Subscribe
    public void onRenderOverlay(OverlayEvent o) {
        //RenderUtil.drawRect(x,y,width,height,new Color(0x90000000,true));
        mc.getTextureManager().bindTexture(Gui.icons);
        int posX = x;
        int posY = y;

        for (PotionEffect potioneffect : this.mc.thePlayer.getActivePotionEffects()) {
            Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
            if (potion.hasStatusIcon()) {
                int index = potion.getStatusIconIndex();
                mc.getTextureManager().bindTexture(potions);

                GL11.glPushMatrix();
                GL11.glTranslatef(posX,posY,1);
                GL11.glScalef(3,3,1);
                GL11.glTranslatef(-posX,-posY,1);
                mc.ingameGUI.drawTexturedModalRect(posX, posY, index % 8 * 18, 198 + index / 8 * 18, 18, 18);
                GL11.glPopMatrix();
                posY += 54;
            }


        }
         posX = x;
         posY = y;

        for (PotionEffect potioneffect : this.mc.thePlayer.getActivePotionEffects()) {

            Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
            if (potion.hasStatusIcon()) {
                String duration = Potion.getDurationString(potioneffect);
                Fonts.raleway.drawString(duration, posX + 55, posY + 27 - Fonts.raleway.FONT_HEIGHT / 2);
                posY += 54;
            }

        }


    }

}
