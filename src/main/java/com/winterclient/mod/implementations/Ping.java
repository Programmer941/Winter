package com.winterclient.mod.implementations;

import com.winterclient.event.Subscribe;
import com.winterclient.event.implementations.OverlayEvent;
import com.winterclient.event.implementations.TickEvent;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.mod.HUDMod;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;
import com.winterclient.util.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.OldServerPinger;

import java.awt.*;
import java.net.UnknownHostException;

@Info(name = "Ping", description = "Ping to Server!", category = Category.Visual, enabled = true)
public class Ping extends HUDMod {

    int ping;
    OldServerPinger oldServerPinger;
    ServerData serverData;
    Timer timer;
    public Ping(int x, int y, int width, int height) {
        super(x, y, width, height, true);
        oldServerPinger=new OldServerPinger();
        timer=new Timer(5);
    }

    @Subscribe
    public void onUpdate(TickEvent e){
        if(!mc.isIntegratedServerRunning()){
            if(serverData ==null || serverData!=mc.getCurrentServerData()){
                if(mc.getCurrentServerData()!=null)
                    serverData=mc.getCurrentServerData();
            }
            if(timer.isFinished()) {
                try {
                    if(serverData!=null){
                        oldServerPinger.ping(serverData);
                    }
                } catch (UnknownHostException unknownHostException) {
                    unknownHostException.printStackTrace();
                }
                timer.reset();
            }
            if(serverData!=null){
                if(serverData.pingToServer>0)
                    ping= (int) serverData.pingToServer;
            }
        }else{
            ping=0;
        }
    }


    @Subscribe
    public void onRenderOverlay(OverlayEvent o) {
        Fonts.raleway.drawCenteredString(Integer.toString(ping), x+width/2, y+height/2-Fonts.raleway.FONT_HEIGHT/2);
    }

}
