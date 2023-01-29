package com.winterclient.gui.screens;

import com.winterclient.Winter;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.elements.TextButton;
import net.minecraft.client.Minecraft;

public class OverlayMenu extends WinterGuiScreen {

    @Override
    public void init() {
        drawBackground=false;
        addElement(new TextButton("Mods",width/2-90,height/2-85,180,50){
            @Override
            public void onClick(int mouseX, int mouseY) {
                Minecraft.getMinecraft().displayGuiScreen(new ModMenu());
            }
        });

        addElement(new TextButton("HUD",width/2-90,height/2-25,180,50){
            @Override
            public void onClick(int mouseX, int mouseY) {
                Minecraft.getMinecraft().displayGuiScreen(new HUDEditor());
            }
        });
        addElement(new TextButton("KeyBinds",width/2-90,height/2+35,180,50));
    }

    @Override
    public void end() {

    }

    @Override
    public void draw(int mouseX, int mouseY) {

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
