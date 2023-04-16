package com.winterclient.gui.screens;

import com.winterclient.Winter;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.elements.ServerObject;
import com.winterclient.gui.elements.WorldObject;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.SaveFormatComparator;

import java.util.Collections;

public class Singleplayer extends WinterGuiScreen {

    private java.util.List<SaveFormatComparator> servers;

    @Override
    public void init() {
        ISaveFormat isaveformat = this.mc.getSaveLoader();
        try {
            this.servers = isaveformat.getSaveList();
        } catch (AnvilConverterException e) {
            e.printStackTrace();
        }
        Collections.sort(this.servers);
        servers.forEach(server -> {

        });

        int yOffset=0;
        for(int i=0;i< servers.size();i++){
            WorldObject current=new WorldObject(servers.get(i),width/2-300,height/2- servers.size()*(120+10)/2+yOffset);
            addElement(current);
            yOffset+=130;

        }
    }

    @Override
    public void end() {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Winter.instance.background.draw();
        Winter.instance.blurShader.renderBlur(0, 0, width, height, 20f);
        Winter.instance.background.drawSnow();
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
