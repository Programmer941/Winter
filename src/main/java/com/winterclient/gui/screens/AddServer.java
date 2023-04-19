package com.winterclient.gui.screens;

import com.winterclient.Winter;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.elements.MenuButton;
import com.winterclient.gui.elements.TextBox;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

public class AddServer extends WinterGuiScreen {

    WinterGuiElement selectedElement;
    ServerData returnServer;

    @Override
    public void init() {
        returnServer=new ServerData(I18n.format("selectServer.defaultName", new Object[0]), "", false);
        TextBox serverName = new TextBox("Server Name...",width/2-150,height/2-100,300,60);
        TextBox serverIP = new TextBox("Server IP...",width/2-150,height/2-30,300,60);

        addElement(serverName);
        addElement(serverIP);

        addElement(new MenuButton("Add Server",width/2-150,height/2+40,300,60){
            @Override
            public void onClick(int mouseX, int mouseY, int mouseButton) {
                returnServer.serverName=serverName.text;
                returnServer.serverIP=serverIP.text;
                ServerList servers = new ServerList(mc);
                servers.addServerData(returnServer);
                servers.saveServerList();
                displayGuiScreen(new Multiplayer());
            }
        });
        selectedElement=serverName;
    }

    @Override
    public void end() {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Winter.instance.background.draw();
        Winter.instance.blurShader.renderBlur(0, 0, width, height, 20f);
        Winter.instance.background.drawSnow();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(selectedElement instanceof TextBox){
            ((TextBox) selectedElement).selected=false;
        }
        selectedElement=null;
        for (WinterGuiElement element : guiElements)
            if (element.mouseInBounds(mouseX, mouseY))
                if (element.isCollided(mouseX, mouseY)) {
                    element.onClick(mouseX, mouseY,mouseButton);
                    selectedElement=element;
                    if(element instanceof TextBox){
                        ((TextBox) element).selected=true;
                    }
                    return;
                }
        click(mouseX, mouseY, mouseButton);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
                if(previousScreen!=null){
                    displayGuiScreen(previousScreen);
                }else{
                    displayGuiScreen(new MainMenu());
                }
            }

        for (WinterGuiElement element : guiElements){
            if(selectedElement==element)
            element.onType(typedChar,keyCode);
        }

        type(typedChar, keyCode);
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
