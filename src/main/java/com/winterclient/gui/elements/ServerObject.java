package com.winterclient.gui.elements;

import com.winterclient.Winter;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.screens.Connecting;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;

import java.awt.*;
import java.util.ArrayList;

public class ServerObject extends WinterGuiElement {

    public ServerData serverData;
    ArrayList<String> description;
    String name;
    String ip;

    public ServerObject(ServerData serverData,int x,int y) {
        super(x,y,600,120);
        this.serverData=serverData;
        this.name=serverData.serverName;
        this.ip=serverData.serverIP;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
//        if(this.mouseInBounds(mouseX,mouseY))
//            RenderUtil.drawRect(x+5,y+5,width-10,height-10,new Color(0x90000000,true));
        RenderUtil.drawRect(x,y,width,height,new Color(0x90000000,true));
        //RenderUtil.drawRect(x,y,width,height,new Color(0x90000000,true));
        Fonts.raleway.drawCenteredString(name,x+width/2,y+5);
        if(description!=null){
            String str1 = description.get(0).replaceAll("  +", "");
            Fonts.mcFont.drawCenteredString(str1,x+width/2,y+47);

            if(description.size()>1)
            Fonts.mcFont.drawCenteredString(description.get(1).replaceAll("  +", ""),x+width/2,y+81);

        }else{
            //Fonts.mcFont.drawString(serverData.serverMOTD,x+5,y+45,-1,false);
        }
    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {
        connectToServer(this.serverData);
    }

    @Override
    public void onRelease(int mouseX, int mouseY) {

    }

    @Override
    public void onType(char key, int keyCode) {

    }

    @Override
    public void onUpdate() {
        if(description==null && serverData.serverMOTD!=null && !serverData.serverMOTD.equals("Pinging...")){
           description = Fonts.mcFont.wrapString(serverData.serverMOTD,600);
        }
    }

    @Override
    public boolean isCollided(int mouseX, int mouseY) {
        return true;
    }

    private void connectToServer(ServerData server)
    {
        //Minecraft.getMinecraft().displayGuiScreen(new GuiConnecting(Minecraft.getMinecraft().currentScreen, Minecraft.getMinecraft(), server));
        Minecraft.getMinecraft().displayGuiScreen(new Connecting(server));
    }
}
