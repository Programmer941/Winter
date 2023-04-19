package com.winterclient.gui.elements.settingElements;

import com.winterclient.gui.core.SettingGuiElement;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.setting.implementations.NumberSetting;

import java.awt.*;
import java.util.ArrayList;

public class NumberElement extends SettingGuiElement<Number> {

    ArrayList<Number> selectableNumbers;
    int selectedIndex;

    public NumberElement(NumberSetting s, int start, int end, int boxes) {
        super(s);
        selectableNumbers = new ArrayList<>();
        for (int i = 0; i < boxes; i++) {
            float currentNumber = start + (end - start) / (float) (boxes - 1) * (float) (i);
            selectableNumbers.add(currentNumber);
            width += 55;
        }
        height=50;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        int xOffset = 0;
        int index = 0;

        for (Number number : selectableNumbers) {
            if (index == selectedIndex) {
                RenderUtil.drawRect(x + xOffset, y, 50, 50, new Color(0x90525F7F, true));
            } else if (mouseX > x + xOffset && mouseX < x + xOffset + 50 && mouseY > y && mouseY < y + 50) {
                    RenderUtil.drawRect(x + xOffset, y, 50, 50, new Color(0x46525F7F, true));
            } else {
                RenderUtil.drawRect(x + xOffset, y, 50, 50, new Color(0x1B737984, true));
            }
            Fonts.ralewaySmall.drawCenteredString(String.valueOf(number).substring(0, 3), x + xOffset + 25, y + 25 - Fonts.ralewaySmall.FONT_HEIGHT / 2);
            xOffset += 55;
            index += 1;
        }
    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {
        int xOffset = 0;
        int index = 0;

        for (Number number : selectableNumbers) {
            if (mouseInBounds(mouseX, mouseY)) {
                if (mouseX > x + xOffset && mouseX < x + xOffset + 50 && mouseY > y && mouseY < y + 50) {
                    selectedIndex = index;
                    setting.setValue(number);
                    System.out.println("setting setting value to: " + number);
                }
            }
            xOffset += 55;
            index += 1;
        }

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

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
