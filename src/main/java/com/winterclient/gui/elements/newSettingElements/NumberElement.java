package com.winterclient.gui.elements.newSettingElements;

import com.winterclient.gui.core.SettingGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.setting.Setting;

import java.awt.*;
import java.util.ArrayList;

public class NumberElement extends SettingGuiElement<Number> {

    ArrayList<Number> selectableNumbers;

    public NumberElement(String name, Setting setting,int start,int end,int boxes,int x,int y) {
        super(name, setting,x,y);
        selectableNumbers=new ArrayList<>();
        for(int i=0;i<boxes;i++){
            float currentNumber = (end-start)/ (float) (boxes-1) *  (float) (i);
            selectableNumbers.add(currentNumber);
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
        for (int i=0;i<selectableNumbers.size();i++){
            RenderUtil.drawRect(xPosition+xOffset+5,y,50,50,new Color(0x90000000,true));
            int index=3;
            if(selectableNumbers.get(i).toString().charAt(2)=='.')
                index=2;
            Fonts.ralewaySmall.drawCenteredString(String.valueOf(selectableNumbers.get(i)).substring(0,index),xPosition+xOffset+50/2+5,y+25-Fonts.ralewaySmall.FONT_HEIGHT/2);
            xOffset+=55;
        }
    }
}
