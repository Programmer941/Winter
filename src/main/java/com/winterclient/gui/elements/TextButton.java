package com.winterclient.gui.elements;

import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;

import java.awt.*;

public class TextButton extends WinterGuiElement {

    String text;

    public TextButton(String text, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.text = text;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        RenderUtil.drawRect(x,y,width,height,0x90000000);
        Fonts.raleway.drawCenteredString(text,x+width/2,y+height/2-Fonts.raleway.FONT_HEIGHT/2);
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
        return true;
    }
}
