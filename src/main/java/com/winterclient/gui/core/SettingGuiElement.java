package com.winterclient.gui.core;

import com.winterclient.setting.Setting;

public abstract class SettingGuiElement<T> extends WinterGuiElement{
    public String name;
    public Setting<T> setting;
    public SettingGuiElement(Setting setting) {
        super(0,0,0,50);
        this.setting=setting;
    }

    public void setElementValue(){

    }


    public void setPosition(int xP, int yP){
        this.x=xP;
        this.y=yP;
    }

    @Override
    public boolean isCollided(int mouseX, int mouseY) {
        return true;
    }
}
