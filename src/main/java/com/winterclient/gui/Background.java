package com.winterclient.gui;

import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Images;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiSelectWorld;

public class Background extends WinterGuiElement {

    boolean drawImage = true;

    public Background(int width, int height) {
        super(0, 0, width, height);
    }

    public int formulaY(int x){
        float s = (width/2-(height/-3.73205080757f)/2f);
        double y = -3.73205080757f*(x-s);
        return (int) y;
    }

    public void transitionToSinglePlayer(){
        Minecraft.getMinecraft().displayGuiScreen(new GuiSelectWorld(Minecraft.getMinecraft().currentScreen));
    }

    public void transitionToMultiPlayer(){
        Minecraft.getMinecraft().displayGuiScreen(new GuiMultiplayer(Minecraft.getMinecraft().currentScreen));
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Images.background.draw(0,0,width,height);
        RenderUtil.drawLine(0,formulaY(0),width,formulaY(width),0xffffffff,4);
    }

    @Override
    public void onClick(int mouseX, int mouseY) {
        if(mouseY > formulaY(mouseX)) {
            transitionToMultiPlayer();
        } else {
            transitionToSinglePlayer();
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
}