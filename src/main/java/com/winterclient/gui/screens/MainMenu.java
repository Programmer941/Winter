package com.winterclient.gui.screens;

import com.winterclient.Winter;
import com.winterclient.account.Account;
import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.elements.*;
import com.winterclient.gui.elements.Button;
import com.winterclient.gui.elements.newSettingElements.BooleanElement;
import com.winterclient.gui.elements.newSettingElements.ColorElement;
import com.winterclient.gui.elements.newSettingElements.NumberElement;
import com.winterclient.gui.shader.implementations.BlurShader;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.setting.implementations.BooleanSetting;
import com.winterclient.setting.implementations.ColorSetting;
import com.winterclient.setting.implementations.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;

public class MainMenu extends WinterGuiScreen {

    BlurShader blurShader;
    Animation e;
    @Override
    public void init() {
        blurShader=new BlurShader();
        e=new Animation(0);
        e.goTo(100,15f);
        int x = 10;
        for (int i = 0; i < Winter.instance.accountManager.accountList.size(); i++) {
            addElement(new AccountButton(Winter.instance.accountManager.accountList.get(i), x, 10));
            x += 65;
        }

        addElement(new Button(width / 2 - 30, height - 80, 60, 60) {
            @Override
            public void onClick(int mouseX, int mouseY, int mouseButton) {
                mc.displayGuiScreen(new GuiOptions(mc.currentScreen, Minecraft.getMinecraft().gameSettings));
            }
        });
        addElement(new Button(width - 70, 10, 60, 60) {
            @Override
            public void onClick(int mouseX, int mouseY, int mouseButton) {
                if(GuiScreen.getClipboardString().length()>40){
                    Winter.instance.accountManager.addAccountThroughMicrosoftToken(GuiScreen.getClipboardString());
                }
            }
        });

        addElement(new PlayButton("Singleplayer",60,height/2-60-25,300,60){
            @Override
            public void onClick(int mouseX, int mouseY, int mouseButton) {
                mc.displayGuiScreen(new Singleplayer());
            }
        });
        addElement(new PlayButton("Multiplayer",60,height/2+25,300,60){
            @Override
            public void onClick(int mouseX, int mouseY, int mouseButton) {
                mc.displayGuiScreen(new Multiplayer());
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
        BooleanElement test = new BooleanElement("booleanTest",new BooleanSetting("booleanSetting",false));
        NumberElement test2 = new NumberElement("numberTest", new NumberSetting("hiii",3),0,21,10);
        ColorElement test3 = new ColorElement("color", new ColorSetting("hi", Color.RED), Color.green,Color.white,Color.yellow);
        test.setPositionandSize(100,200);
        test2.setPositionandSize(100,260);
        test3.setPositionandSize(100,320);
        addElement(test);
        addElement(test2);
        addElement(test3);


    }

    @Override
    public void end() {

    }

    @Override
    public void draw(int mouseX, int mouseY) {

    }

    @Override
    public void update() {
        if(Winter.instance.accountManager.pendingNewAccounts){
            for(Account pendingAccount : Winter.instance.accountManager.pendingAccounts){
                pendingAccount.createAvatar();
                Winter.instance.accountManager.accountList.add(pendingAccount);
                Winter.instance.accountManager.setActiveAccount(pendingAccount);
            }

            Winter.instance.accountManager.pendingNewAccounts =false;
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