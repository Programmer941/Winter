package com.winterclient.mod.implementations;

import com.winterclient.event.Subscribe;
import com.winterclient.event.implementations.OverlayEvent;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.mod.HUDMod;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiNewChat;

import java.awt.*;
import java.util.ArrayList;

@Info(name = "Chat", description = "Better Chat", category = Category.Visual, enabled = true)
public class Chat extends HUDMod {

    ArrayList<String> messages;

    public Chat(int x, int y, int width, int height) {
        super(x, y, width, height, false);
        messages=new ArrayList<>();
    }

    public void addMessage(String msg){
        messages.add(msg);
    }

    @Subscribe
    public void onRenderOverlay(OverlayEvent o) {
        RenderUtil.drawRect(x,y,width,height,new Color(0x90000000,true));
        int yy=0;
        for(int i=0;i<messages.size();i++){
            Fonts.mcFont.drawString(messages.get(i),x,y+yy,0xffffffff,false);
            yy+=30;
        }
    }
}
