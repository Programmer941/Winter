package com.winterclient.gui.elements.newSettingElements;

import com.winterclient.gui.core.SettingGuiElement;
import com.winterclient.gui.elements.newSettingElements.blockElement.BlockElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.setting.Setting;
import com.winterclient.setting.implementations.BooleanSetting;

import java.awt.*;

public class BooleanElement extends SettingGuiElement<Boolean> {
    public BooleanElement(String name, BooleanSetting setting,int x,int y) {
        super(name, setting,x,y);
        addElement(new BlockElement(x+width,y){

            @Override
            public void drawOverlay(){
                if(setting.getValue()){
                    RenderUtil.drawRect(x+8,y+8,34,34,new Color(128, 60, 60));
                }else{
                    RenderUtil.drawRect(x+8,y+8,34,34,new Color(60, 128, 60));
                }
            }

            });
    }

    @Override
    public int getExtensionSize() {
        return 50;
    }

    @Override
    public void drawSettingElement(int xPosition) {
//        RenderUtil.drawRect(xPosition,y,50,50,new Color(0x90000000,true));
//        if(setting.getValue()){
//            RenderUtil.drawRect(xPosition+8,y+8,34,34,new Color(128, 60, 60));
//        }else{
//            RenderUtil.drawRect(xPosition+8,y+8,34,34,new Color(60, 128, 60));
//        }
    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {
        super.onClick(mouseX,mouseY,mouseButton);
        //setting.setValue(!setting.getValue());
    }

}
