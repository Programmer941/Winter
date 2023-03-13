package com.winterclient.gui.core;

import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.setting.Setting;

import java.awt.*;

public abstract class SettingGuiElement<T> extends WinterGuiElement{
    protected String name;
    protected Setting<T> setting;
    public SettingGuiElement(String name, Setting setting) {
        super(0,0,0,50);
        this.name=name;
        this.setting=setting;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        //RenderUtil.drawRect(x,y,width,height,new Color(0x90000000,true));
        Fonts.raleway.drawString(name,x,y+height/2-Fonts.raleway.FONT_HEIGHT/2);
        int width = Fonts.raleway.getStringWidth(name);
        drawSettingElement(x+width+5);
    }

    public void setPositionandSize(int xP,int yP){
        this.x=xP;
        this.y=yP;
        this.width=Fonts.raleway.getStringWidth(name)+getExtensionSize()+5;
    }

    public abstract int getExtensionSize();

    public abstract void drawSettingElement(int xPosition);

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void onRelease(int mouseX, int mouseY) {

    }

    @Override
    public void onType(char key, int keyCode) {

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public boolean isCollided(int mouseX, int mouseY) {
        return true;
    }
}
