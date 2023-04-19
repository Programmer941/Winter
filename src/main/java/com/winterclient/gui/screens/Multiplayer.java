package com.winterclient.gui.screens;

import com.winterclient.Winter;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.elements.*;
import com.winterclient.gui.elements.Button;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Images;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreenAddServer;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.OldServerPinger;
import net.minecraft.client.resources.I18n;

import java.awt.*;
import java.net.UnknownHostException;

public class Multiplayer extends WinterGuiScreen {


    private final OldServerPinger oldServerPinger = new OldServerPinger();
    boolean removing=false;
    ServerList savedServerList;

    ScrollableList servers;
    @Override
    public void init() {
        previousScreen=new MainMenu();
        int yOffset=0;
        this.savedServerList = new ServerList(this.mc);
        this.savedServerList.loadServerList();

        servers = new ScrollableList(width/2-420,height/2- 260,840,height/2+260-80);

        for(int i=0;i< savedServerList.countServers();i++){
            ServerObject current=new ServerObject(savedServerList.getServerData(i),20,0+yOffset);
            servers.addElement(current);
            yOffset+=130;
        }

        addElement(servers);

        addElement(new MenuButton("Add Server",width/2+5,height-70,300,60){
            @Override
            public void onClick(int mouseX, int mouseY, int mouseButton) {
                //mc.displayGuiScreen(new GuiScreenAddServer(mc.currentScreen,new ServerData("Minecraft", "", false)));
                displayGuiScreen(new AddServer());
            }
        });
        addElement(new MenuButton("Remove Server",width/2-305,height-70,300,60){
            @Override
            public void onClick(int mouseX, int mouseY, int mouseButton) {
                if(removing==false){
                    removing=true;
                    this.text="Removing!";
                }else{
                    removing=false;
                    this.text="Remove Server";
                }
            }
        });


        new Thread(() -> {
            for(int i=0;i< savedServerList.countServers();i++){
                try {
                    oldServerPinger.ping(savedServerList.getServerData(i));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        addElement(new Title(width / 2 - 272, height / 4 - 50, 534, 118, Images.multiplayerTitle));
    }

    @Override
    public void end() {
        this.oldServerPinger.clearPendingNetworks();
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        RenderUtil.drawRect(0,0,3000,3000, Color.red);
        float amount = 1-(1.0F / (float) Minecraft.getMinecraft().getFramebuffer().framebufferHeight* 70);
        //Winter.instance.verticalFadeShader.load();
        //Winter.instance.verticalFadeShader.setupUniforms(amount);
        Winter.instance.background.draw();
        //Winter.instance.verticalFadeShader.unload();
        Winter.instance.blurShader.renderBlur(0, 0, width, height, 20f);
        Winter.instance.background.drawSnow();
    }

    public void removeServer(int index){
        this.savedServerList.removeServerData(index);
        this.savedServerList.saveServerList();

    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
    if(removing){
        int index = servers.getIndex(mouseX,mouseY);
        if(index>0){
            removeServer(index);
            servers.guiElements.remove(index);
            removing=false;
        }
    }
        for (WinterGuiElement element : guiElements)
            if (element.mouseInBounds(mouseX, mouseY))
                if (element.isCollided(mouseX, mouseY)) {
                    element.onClick(mouseX, mouseY,mouseButton);
                    return;
                }
        click(mouseX, mouseY, mouseButton);
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
