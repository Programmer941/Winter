package com.winterclient.gui.elements.newSettingElements;

import com.winterclient.gui.core.SettingGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.setting.Setting;

import java.awt.*;
import java.util.ArrayList;

public class ColorElement extends SettingGuiElement<Color> {

    ArrayList<Color> colors;

    public ColorElement(String name, Setting<Color> setting, int x,int y,Color... color) {
        super(name, setting,x,y);
        colors=new ArrayList<>();
        colors.add(setting.getValue());

        for (Color c : color) {
            colors.add(c);
            width+=55;
        }
    }

    @Override
    public int getExtensionSize() {
        return width;
    }

    @Override
    public void drawSettingElement(int xPosition) {
        int xOffset=0;
        for (int i=0;i<colors.size();i++){
            RenderUtil.drawRect(xPosition+xOffset+5,y,50,50,new Color(0x90000000,true));
            RenderUtil.drawRect(xPosition+xOffset+5+8,y+8,34,34,colors.get(i));
            xOffset+=55;
    }
    }
}
