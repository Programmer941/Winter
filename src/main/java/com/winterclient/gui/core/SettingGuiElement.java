package com.winterclient.gui.core;

import com.winterclient.setting.Setting;

public class SettingGuiElement extends WinterGuiElement{
    public SettingGuiElement(String name, Setting setting) {
        super(0,0,50,50);
    }

    @Override
    public void draw(int mouseX, int mouseY) {

    }

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
        return false;
    }
}
