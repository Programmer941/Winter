package com.winterclient.gui.elements;

import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Images;

public class TextBox extends WinterGuiElement {
    public TextBox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Images.uibackground.draw(x,y,width,height);
    }

    @Override
    public void onClick(int mouseX, int mouseY) {

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
