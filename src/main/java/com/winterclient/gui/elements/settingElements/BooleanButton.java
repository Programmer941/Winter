package com.winterclient.gui.elements.settingElements;

import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;
import com.winterclient.setting.implementations.BooleanSetting;

import java.awt.*;

public class BooleanButton extends WinterGuiElement {

    String description;
    BooleanSetting booleanSetting;

    public BooleanButton(String description, int x, int y, int width, int height, BooleanSetting booleanSetting) {
        super(x, y, width, height);
        this.description=description;
        this.booleanSetting=booleanSetting;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        RenderUtil.drawRect(x, y, width, height, new Color(0x90000000,true));
        Fonts.raleway.drawCenteredString(description, x + width / 2, y + height / 2 - Fonts.raleway.FONT_HEIGHT / 2);
        Color c = new Color(0x4DFF2B00, true);
        if (booleanSetting.getValue())
            c = new Color(0x4D26FF00, true);

        Images.selected.draw(x,y,20,20,c);
    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {
        booleanSetting.setValue(!booleanSetting.getValue());
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
