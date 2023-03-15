package com.winterclient.gui.core;

import com.winterclient.gui.elements.newSettingElements.blockElement.BlockElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.setting.Setting;

import java.awt.*;
import java.util.ArrayList;

public abstract class SettingGuiElement<T> extends WinterGuiElement{
    protected String name;
    protected Setting<T> setting;
    protected ArrayList<BlockElement> elements;
    public SettingGuiElement(String name, Setting setting,int x,int y) {
        super(0,0,0,50);
        this.x=x;
        this.y=y;
        this.name=name;
        this.setting=setting;
        this.width=Fonts.raleway.getStringWidth(name)+5;
        elements=new ArrayList<>();
    }

    public void addElement(BlockElement element){
        elements.add(element);
        width+=55;
    }


    @Override
    public void draw(int mouseX, int mouseY) {
        //RenderUtil.drawRect(x,y,width,height,new Color(0x90000000,true));
        Fonts.raleway.drawString(name,x,y+height/2-Fonts.raleway.FONT_HEIGHT/2);
        elements.forEach(element -> {
            element.draw(mouseX,mouseY);
        });
    }

    public void setPosition(int xP, int yP){
        this.x=xP;
        this.y=yP;
    }

    public abstract int getExtensionSize();

    public abstract void drawSettingElement(int xPosition);

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {
        elements.forEach(element -> {
            element.onClick(mouseX,mouseY,mouseButton);
        });
    }

    @Override
    public void onRelease(int mouseX, int mouseY) {
        elements.forEach(element -> {
            element.onRelease(mouseX,mouseY);
        });
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
