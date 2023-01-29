package com.winterclient.mod.implementations;

import com.winterclient.event.Subscribe;
import com.winterclient.event.implementations.OverlayEvent;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.mod.HUDMod;
import com.winterclient.mod.Mod;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

import java.awt.*;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

@Info(name = "FPS", description = "Frames Per Second!", category = Category.Visual, enabled = true)
public class FPS extends HUDMod {

    public FPS(int x, int y, int width, int height) {
        super(x, y, width, height, true);
    }

    @Subscribe
    public void onRenderOverlay(OverlayEvent o) {
        int fps = Minecraft.getDebugFPS();
        Fonts.raleway.drawCenteredString(Integer.toString(fps), x+width/2, y+height/2-Fonts.raleway.FONT_HEIGHT/2, Color.white);
    }
}
