package com.winterclient.mixins.implementations;

import com.winterclient.Winter;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiDownloadTerrain.class)
public class MixinGuiDownloadTerrain {
    @Shadow
    private int progress;


    @Overwrite
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        GL11.glPushMatrix();
        GL11.glScalef(.5f,.5f,1);
        Winter.instance.background.draw();
        GL11.glPopMatrix();

        //Fonts.raleway.drawCenteredString("Downloading Terrain "+String.valueOf(progress*5)+"%",Minecraft.getMinecraft().displayWidth/2/2,(Minecraft.getMinecraft().displayHeight/2-Fonts.raleway.FONT_HEIGHT/2)/2);
    }
}
