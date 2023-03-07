package com.winterclient.gui.screens;

import com.winterclient.Winter;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.util.resources.Images;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.util.ChatComponentTranslation;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

public class Connecting extends WinterGuiScreen {

    private static final AtomicInteger CONNECTION_ID = new AtomicInteger(0);

    Minecraft mc;
    ServerData serverData;
    boolean cancel;
    private NetworkManager networkManager;

    public Connecting(ServerData serverData)
    {
        this.serverData=serverData;
        this.mc=Minecraft.getMinecraft();
        ServerAddress serveraddress = ServerAddress.fromString(serverData.serverIP);
        mc.setServerData(serverData);
        this.connect(serveraddress.getIP(), serveraddress.getPort());
    }

    private void connect(final String ip, final int port)
    {
        Winter.instance.logger.info("Connecting to " + ip + ", " + port);
        (new Thread("Server Connector #" + CONNECTION_ID.incrementAndGet())
        {
            public void run()
            {
                InetAddress inetaddress = null;

                try
                {
                    if (cancel)
                    {
                        return;
                    }

                    inetaddress = InetAddress.getByName(ip);
                    networkManager = NetworkManager.createNetworkManagerAndConnect(inetaddress, port, mc.gameSettings.isUsingNativeTransport());
                    networkManager.setNetHandler(new NetHandlerLoginClient(networkManager, mc, null));
                    networkManager.sendPacket(new C00Handshake(47, ip, port, EnumConnectionState.LOGIN));
                    networkManager.sendPacket(new C00PacketLoginStart(mc.getSession().getProfile()));
                }
                catch (UnknownHostException unknownhostexception)
                {
                    if (cancel)
                    {
                        return;
                    }

                    Winter.instance.logger.warning("Couldn\'t connect to server"+ unknownhostexception.toString());
                    mc.displayGuiScreen(new GuiDisconnected(null, "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[] {"Unknown host"})));
                }
                catch (Exception exception)
                {
                    if (cancel)
                    {
                        return;
                    }

                    Winter.instance.logger.warning("Couldn\'t connect to server"+ (Throwable)exception);
                    String s = exception.toString();

                    if (inetaddress != null)
                    {
                        String s1 = inetaddress.toString() + ":" + port;
                        s = s.replaceAll(s1, "");
                    }

                    mc.displayGuiScreen(new GuiDisconnected(null, "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[] {s})));
                }
            }
        }).start();
    }

    @Override
    public void update(){
        super.update();
        if (this.networkManager != null)
        {
            if (this.networkManager.isChannelOpen())
            {
                this.networkManager.processReceivedPackets();
            }
            else
            {
                this.networkManager.checkDisconnected();
            }
        }
    }


        @Override
    public void init() {
        
    }

    @Override
    public void end() {

    }

    @Override
    public void draw(int mouseX, int mouseY) {

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
