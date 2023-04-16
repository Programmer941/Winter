package com.winterclient.gui.elements;

import com.google.common.base.Charsets;
import com.winterclient.Winter;
import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.screens.Connecting;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.image.DefaultImage;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.texture.TextureUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class ServerObject extends WinterGuiElement {

    public ServerData serverData;
    ArrayList<String> description;
    String name;
    String ip;

    DefaultImage icon=null;

    Animation hover;


    public ServerObject(ServerData serverData,int x,int y) {
        super(x,y,800,120);
        this.serverData=serverData;
        this.name=serverData.serverName;
        this.ip=serverData.serverIP;
        generateIcon();
        hover=new Animation(0);
    }

    public void generateIcon(){
            ByteBuf bytebuf = Unpooled.copiedBuffer(serverData.getBase64EncodedIconData(), Charsets.UTF_8);
            ByteBuf bytebuf1 = Base64.decode(bytebuf);
        try {
            BufferedImage bufferedImage = TextureUtil.readBufferedImage(new ByteBufInputStream(bytebuf1));
            icon=new DefaultImage(Images.roundImage(bufferedImage,128));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytebuf.release();
        bytebuf1.release();
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        float value= hover.getValue();
        width= (int) (800+value*100f);
        RenderUtil.drawRoundRect(x,y,width,height,new Color(0,0,0,.35f-value),height);
        icon.draw(x,y,120,120);
        //RenderUtil.drawRect(x,y,width,height,new Color(0x90000000,true));
        Fonts.raleway.drawCenteredString(name,x+width/2,y+5);
        if(description!=null){
            String str1 = description.get(0).replaceAll("  +", "");
            Fonts.mcFont.drawCenteredString(str1,x+width/2,y+47);

            if(description.size()>1)
            Fonts.mcFont.drawCenteredString(description.get(1).replaceAll("  +", ""),x+width/2,y+81);

        }

        if(this.mouseInBounds(mouseX,mouseY)){
            if(hover.end==0){
                hover.goTo(.2f,0.2f);
            }
        }else{
            if(hover.end!=0){
                hover.goTo(0,0.2f);
            }
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
