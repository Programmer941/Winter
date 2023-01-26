package com.winterclient.mod.implementations;

import com.winterclient.event.Subscribe;
import com.winterclient.event.implementations.OverlayEvent;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.mod.Mod;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

import java.awt.*;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

@Info(name = "FPS", description = "Frames Per Second!", category = Category.Visual, enabled = true)
public class FPS extends Mod {

    @Subscribe
    public void onRenderOverlay(OverlayEvent o) {
        int fps = Minecraft.getDebugFPS();
        Fonts.raleway.drawString(Integer.toString(fps), 20, 20, Color.white);
    }
}
