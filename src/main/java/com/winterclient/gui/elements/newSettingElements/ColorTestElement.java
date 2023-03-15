package com.winterclient.gui.elements.newSettingElements;

import com.winterclient.gui.core.WinterGuiElement;

import java.awt.*;
import java.util.ArrayList;

public class ColorTestElement extends WinterGuiElement {

    ArrayList<Color> selectableColors;
    int selectedIndex;
    public Color selectedValue;

    public ColorTestElement(int x, int y, Color... colors) {
        super(x, y, 0,50);
        selectableColors=new ArrayList<>();
        for (Color color : colors) {
            selectableColors.add(color);
            width += 55;
        }

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
