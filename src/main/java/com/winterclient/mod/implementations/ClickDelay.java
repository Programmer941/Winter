package com.winterclient.mod.implementations;

import com.winterclient.event.Subscribe;
import com.winterclient.event.implementations.OverlayEvent;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.mod.HUDMod;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;

import java.awt.*;
import java.util.ArrayList;

@Info(name = "Click Delay", description = "Enable / View Effects of Click Delay", category = Category.Visual, enabled = true)
public class ClickDelay extends HUDMod {

    public static ClickDelay instance;

    public int clicksCanceled;
    public boolean clickDelayOff=true;
    public ClickDelay(int x, int y, int width, int height) {
        super(x, y, width, height, true);
        instance=this;
    }

    @Subscribe
    public void onRenderOverlay(OverlayEvent o) {
        RenderUtil.drawRect(x,y,width,height,new Color(0x90000000,true));
        Fonts.raleway.drawCenteredString(String.valueOf(clicksCanceled),x+width/2,y+height/2-Fonts.raleway.FONT_HEIGHT/2);
    }
}
