package com.winterclient.gui.screens;

import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.util.RenderUtil;

public class GuiTest extends WinterGuiScreen {
    @Override
    public void init() {

    }

    @Override
    public void end() {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        RenderUtil.drawRect(100,100,100,100,0x90000000);
        RenderUtil.drawCircle(100,300,50,0x90000000);
    }

    @Override
    public void update() {

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
