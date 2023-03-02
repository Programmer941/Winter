package com.winterclient.gui.elements.settingElements;

import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;

import java.awt.*;
import java.util.ArrayList;

public class NumberSelectObject extends WinterGuiElement {

    ArrayList<Float> selectableNumbers;

    public NumberSelectObject(int x, int y, int start, int end, int boxes) {
        super(x, y, 830, 60);
        selectableNumbers=new ArrayList<>();
        for(int i=0;i<boxes;i++){
            float currentNumber = (end-start)/ (float) (boxes-1) *  (float) (i);
            selectableNumbers.add(currentNumber);
        }
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        RenderUtil.drawRect(x,y,width,height,new Color(0x90000000,true));
        int xOffset=0;
        for (int i=0;i<selectableNumbers.size();i++){
            RenderUtil.drawRect(x+xOffset+5,y+5,50,50,new Color(0x90000000,true));
            int index=3;
            if(selectableNumbers.get(i).toString().charAt(2)=='.')
                index=2;
            Fonts.ralewaySmall.drawCenteredString(String.valueOf(selectableNumbers.get(i)).substring(0,index),x+xOffset+50/2+5,y+25-Fonts.ralewaySmall.FONT_HEIGHT/2+5);
            xOffset+=60;
        }
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
