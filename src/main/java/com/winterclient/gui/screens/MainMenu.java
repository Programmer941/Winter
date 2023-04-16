package com.winterclient.gui.screens;

import com.winterclient.Winter;
import com.winterclient.account.Account;
import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.elements.*;
import com.winterclient.gui.elements.Button;

import com.winterclient.gui.shader.implementations.BlurShader;
import com.winterclient.gui.util.image.DefaultImage;
import com.winterclient.gui.util.image.WinterGuiImage;
import com.winterclient.gui.util.resources.Images;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MainMenu extends WinterGuiScreen {

    BlurShader blurShader;
    Animation e;

    BufferedImage accountImage = null;
    DefaultImage playerImage=null;

    @Override
    public void init() {
        blurShader = new BlurShader();
        e = new Animation(0);
        e.goTo(100, 15f);
        int y = 10;
        for (int i = 0; i < Winter.instance.accountManager.accountList.size(); i++) {
            addElement(new AccountButton(Winter.instance.accountManager.accountList.get(i), 10, y));
            y += 70;
        }
        addElement(new MenuButton("Options", width / 2 - 100, height - 60 - 10, 200, 60) {
            @Override
            public void onClick(int mouseX, int mouseY, int mouseButton) {
                mc.displayGuiScreen(new GuiOptions(mc.currentScreen, Minecraft.getMinecraft().gameSettings));
            }
        });

        addElement(new MenuButton("Add Account", width - 210, 10, 200, 60) {
            @Override
            public void onClick(int mouseX, int mouseY, int mouseButton) {
                if (GuiScreen.getClipboardString().length() > 40) {
                    Winter.instance.accountManager.addAccountThroughMicrosoftToken(GuiScreen.getClipboardString());
                }
            }
        });

//        addElement(new Button(width / 2 - 30, height - 80, 60, 60) {
//            @Override
//            public void onClick(int mouseX, int mouseY, int mouseButton) {
//                mc.displayGuiScreen(new GuiOptions(mc.currentScreen, Minecraft.getMinecraft().gameSettings));
//            }
//        });
//        addElement(new Button(width - 70, 10, 60, 60) {
//            @Override
//            public void onClick(int mouseX, int mouseY, int mouseButton) {
//                if (GuiScreen.getClipboardString().length() > 40) {
//                    Winter.instance.accountManager.addAccountThroughMicrosoftToken(GuiScreen.getClipboardString());
//                }
//            }
//        });

        addElement(new MenuButton("Singleplayer", width / 2 - 150, height / 2 - 60 - 5, 300, 60) {
            @Override
            public void onClick(int mouseX, int mouseY, int mouseButton) {
                mc.displayGuiScreen(new Singleplayer());
            }
        });
        addElement(new MenuButton("Multiplayer", width / 2 - 150, height / 2 + 5, 300, 60) {
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
//        numberSetting = new NumberSetting("lols",3);
//        addElement(numberElement = new NumberElement(numberSetting,22,64,6));

        getAccountDisplayImage();
    }

    @Override
    public void end() {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Winter.instance.background.draw();
        Winter.instance.blurShader.renderBlur(0, 0, width, height, 20f);
        Winter.instance.background.drawSnow();
        Images.title.draw(width / 2 - 202, height / 4 - 50, 404, 118);
        if(accountImage!=null && playerImage==null){

            playerImage=new DefaultImage(accountImage);

        }
        if(playerImage!=null)
            playerImage.draw(10,height/2,160,360);
    }

    @Override
    public void update() {
        if (Winter.instance.accountManager.pendingNewAccounts) {
            for (Account pendingAccount : Winter.instance.accountManager.pendingAccounts) {
                pendingAccount.createAvatar();
                Winter.instance.accountManager.accountList.add(pendingAccount);
                Winter.instance.accountManager.setActiveAccount(pendingAccount);
            }

            Winter.instance.accountManager.pendingNewAccounts = false;
            Minecraft.getMinecraft().displayGuiScreen(this);
        }
    }

    public void getAccountDisplayImage() {
        new Thread(() -> {
            String prefix = "https://crafatar.com/renders/body/";
            String suffix = "?scale=8&overlay";
            try {
                BufferedImage img = ImageIO.read(new URL(prefix + Winter.instance.accountManager.activeAccount.uuid + suffix));
                accountImage = img;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
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