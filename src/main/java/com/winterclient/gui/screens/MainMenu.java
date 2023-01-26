package com.winterclient.gui.screens;

import com.winterclient.Winter;
import com.winterclient.gui.Background;
import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.elements.*;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Images;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;

public class MainMenu extends WinterGuiScreen {

    @Override
    public void init() {
        int x = 10;
        for (int i = 0; i < Winter.instance.accountManager.accountList.size(); i++) {
            addElement(new AccountButton(Winter.instance.accountManager.accountList.get(i), x, 10));
            x += 65;
        }

        addElement(new Button(width / 2 - 30, height - 80, 60, 60) {
            @Override
            public void onClick(int mouseX, int mouseY) {
                mc.displayGuiScreen(new GuiOptions(mc.currentScreen, Minecraft.getMinecraft().gameSettings));
            }
        });
        addElement(new Button(width - 70, 10, 60, 60) {
            @Override
            public void onClick(int mouseX, int mouseY) {
                if(GuiScreen.getClipboardString().length()>40){
                    Winter.instance.accountManager.addAccountThroughMicrosoftToken(GuiScreen.getClipboardString());
                }
            }
        });

        addElement(new PlayButton("Singleplayer",60,height/2-60-25,300,60){
            @Override
            public void onClick(int mouseX, int mouseY) {
                mc.displayGuiScreen(new GuiSelectWorld(mc.currentScreen));
            }
        });
        addElement(new PlayButton("Multiplayer",60,height/2+25,300,60){
            @Override
            public void onClick(int mouseX, int mouseY) {
                mc.displayGuiScreen(new GuiMultiplayer(mc.currentScreen));
            }
        });
        //addElement(new PlayButton("Multiplayer",60,height/2+40,500,33));

//        addElement(new CircleButton(width / 2 - 116, height / 2-50, 100){
//            @Override
//            public void onClick(int mouseX, int mouseY) {
//                mc.displayGuiScreen(new GuiSelectWorld(mc.currentScreen));
//            }
//
//            });
//        addElement(new CircleButton(width / 2+16, height / 2-50, 100){
//            @Override
//            public void onClick(int mouseX, int mouseY) {
//                mc.displayGuiScreen(new GuiMultiplayer(mc.currentScreen));
//            }
//        });

    }

    @Override
    public void end() {

    }

    @Override
    public void draw(int mouseX, int mouseY) {

    }

    @Override
    public void update() {
        if(Winter.instance.accountManager.pendingNewAccount){
            Winter.instance.accountManager.pendingAccount.createAvatar();
            Winter.instance.accountManager.accountList.add(Winter.instance.accountManager.pendingAccount);
            Winter.instance.accountManager.activeAccount=Winter.instance.accountManager.pendingAccount;
            Winter.instance.accountManager.pendingNewAccount=false;
            Minecraft.getMinecraft().displayGuiScreen(this);
        }
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