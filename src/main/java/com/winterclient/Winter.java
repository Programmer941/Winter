package com.winterclient;

import com.winterclient.data.AccountManager;
import com.winterclient.data.BorrowLunarAccounts;
import com.winterclient.discord.Discord;
import com.winterclient.event.EventBus;
import com.winterclient.gui.Background;

import com.winterclient.gui.LoadingScreen;
import com.winterclient.mod.Mod;
import com.winterclient.mod.ModManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.util.logging.Logger;

public enum Winter {

    instance;

    public AccountManager accountManager;
    public Background background;
    public LoadingScreen loadingScreen;

    public EventBus eventBus;
    public ModManager modManager;

    Logger logger = Logger.getGlobal();

    public final void preInit(){
        accountManager = new AccountManager();
        background=new Background(Display.getWidth(),Display.getHeight());
        loadingScreen=new LoadingScreen();
    }

    public final void init() {
        BorrowLunarAccounts.readAccounts();
        Display.setTitle("Winter Client");
        Discord.start();
        eventBus = new EventBus();
        modManager=new ModManager();
    }

    public final void end() {
        System.out.println("Saving all data!");
        Winter.instance.accountManager.saveAccounts();
    }

    public final void resize(int width, int height){
        background=new Background(width,height);
        System.out.println("Client Resizing");
    }


}
