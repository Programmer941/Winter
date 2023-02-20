package com.winterclient.gui.screens;

import com.winterclient.Winter;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.elements.Button;
import com.winterclient.gui.elements.ServerObject;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.OldServerPinger;

import java.net.UnknownHostException;

public class Multiplayer extends WinterGuiScreen {

    private final OldServerPinger oldServerPinger = new OldServerPinger();

    ServerList savedServerList;


    @Override
    public void init() {
        int yOffset=0;
        this.savedServerList = new ServerList(this.mc);
        this.savedServerList.loadServerList();
        new Thread(() -> {
            for(int i=0;i< savedServerList.countServers();i++){
                try {
                    oldServerPinger.ping(savedServerList.getServerData(i));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        for(int i=0;i< savedServerList.countServers();i++){
                ServerObject current=new ServerObject(savedServerList.getServerData(i),width/2-300,height/2- savedServerList.countServers()*(120+10)/2+yOffset);
                addElement(current);
                yOffset+=130;

        }

    }

    @Override
    public void end() {
        this.oldServerPinger.clearPendingNetworks();
    }

    @Override
    public void draw(int mouseX, int mouseY) {
    }

    @Override
    public void update() {
        super.update();
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
