package com.winterclient.gui.screens;

import com.winterclient.Winter;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.mod.HUDMod;

import java.awt.*;
import java.util.ArrayList;

public class HUDEditor extends WinterGuiScreen {

    ArrayList<HUDMod> HUDMods;

    HUDMod selectedMod;
    int offsetX;
    int offsetY;
    @Override
    public void init() {
        drawBackground=false;
        HUDMods=new ArrayList<HUDMod>();
        Winter.instance.modManager.getModules().forEach(mod -> {
            if(mod instanceof HUDMod && mod.enabled){
                HUDMods.add((HUDMod) mod);
            }
        });
    }

    @Override
    public void end() {

    }

    @Override
    public void draw(int mouseX, int mouseY) {

        HUDMods.forEach(hudMod -> {
            RenderUtil.drawRect(hudMod.x, hudMod.y, hudMod.width, hudMod.height, new Color(0x50000000,true));
        });

        HUDMods.forEach(hudMod -> {
            if(mouseX>hudMod.x && mouseX<hudMod.x+hudMod.width && mouseY>hudMod.y && mouseY<hudMod.y+hudMod.height){
                RenderUtil.drawRect(hudMod.x, hudMod.y, hudMod.width, hudMod.height, new Color(0x30000000,true));
                return;
            }
        });

        if(selectedMod!=null){
            int xValue=mouseX-offsetX;
            int yValue=mouseY-offsetY;
            if(xValue<0)
                xValue=0;
            if(yValue<0)
                yValue=0;
            if(xValue+selectedMod.width>width)
                xValue=width-selectedMod.width;
            if(yValue+selectedMod.height>height)
                yValue=height-selectedMod.height;
            selectedMod.updatePosition(xValue,yValue);

        }
    }

    @Override
    public void update() {

    }

    @Override
    public void click(int mouseX, int mouseY, int mouseButton) {
        HUDMods.forEach(hudMod -> {
            if(mouseX>hudMod.x && mouseX<hudMod.x+hudMod.width && mouseY>hudMod.y && mouseY<hudMod.y+hudMod.height){
                selectedMod=hudMod;
                offsetX=mouseX-hudMod.x;
                offsetY=mouseY-hudMod.y;
                return;
            }
        });
    }

    @Override
    public void release(int mouseX, int mouseY, int mouseButton) {
        selectedMod=null;
    }

    @Override
    public void drag(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void type(char typedChar, int keyCode) {

    }
}
