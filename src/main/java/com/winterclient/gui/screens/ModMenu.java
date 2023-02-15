package com.winterclient.gui.screens;

import com.winterclient.Winter;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.elements.ModButton;
import com.winterclient.gui.elements.TextButton;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Images;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ModMenu extends WinterGuiScreen {

    int positionX;
    int positionY;

    @Override
    public void init() {
        drawBackground=false;
        int padding=10;

        positionX=width / 2 - 415;
        positionY=height / 2 - 235;
        int buttonX = positionX+45;
        int buttonY = positionY+30;
        int buttonWidth=240;
        int buttonHeight=60;
        for (int i = 0; i < Winter.instance.modManager.getModules().size(); i++) {
            addElement(new ModButton(Winter.instance.modManager.getModules().get(i), buttonX, buttonY, buttonWidth, buttonHeight));
            buttonX += buttonWidth+padding;
            if((i+1)%3==0){
                buttonX=positionX+45;
                buttonY += buttonHeight+padding;
            }
        }
    }

    @Override
    public void end() {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Images.loadingBar.draw(width/2-250,height/2-235-33-10,500,33,new Color(0xEAEEF0));
        GL11.glPushMatrix();
        GL11.glTranslatef(width/2-250, 0, 0.0f);
        GL11.glScalef(-1, -1, 0.0f);
        GL11.glTranslatef(-width/2-250, 0, 0.0f);
        Images.loadingBar.draw(width/2-250,-height/2-235-33-10, 500, 33, new Color(0xEAEEF0));
        GL11.glPopMatrix();
        RenderUtil.drawRect(width/2-415,height/2-235,830,470,new Color(0x90000000,true));
    }

    @Override
    public void update() {

    }

    @Override
    public void click(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void release(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void drag(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void type(char typedChar, int keyCode) {

    }
}
