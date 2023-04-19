package com.winterclient;

import com.winterclient.config.SettingManager;
import com.winterclient.data.AccountManager;
import com.winterclient.data.BorrowLunarAccounts;
import com.winterclient.discord.Discord;
import com.winterclient.event.EventBus;
import com.winterclient.gui.Background;

import com.winterclient.gui.LoadingScreen;
import com.winterclient.gui.shader.implementations.BlurShader;
import com.winterclient.gui.shader.implementations.FadeShader;
import com.winterclient.gui.shader.implementations.RoundShader;
import com.winterclient.gui.shader.implementations.VerticalFadeShader;
import com.winterclient.gui.util.resources.Fonts;
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
    public BlurShader blurShader;
    public FadeShader fadeShader;
    public VerticalFadeShader verticalFadeShader;
    public RoundShader roundShader;

    public EventBus eventBus;
    public ModManager modManager;

    public Logger logger = Logger.getGlobal();

    public final void preInit(){
        accountManager = new AccountManager();
        BorrowLunarAccounts.readAccounts();
        background=new Background(Display.getWidth(),Display.getHeight());
        loadingScreen=new LoadingScreen();
        blurShader=new BlurShader();
        fadeShader=new FadeShader();
        verticalFadeShader=new VerticalFadeShader();
        //roundShader=new RoundShader();
    }

    public final void init() {
        Display.setTitle("Winter Client");
        Discord.start();
        eventBus = new EventBus();
        modManager=new ModManager();
        new SettingManager().readSettings();
    }

    public final void end() {
        new SettingManager().writeSettings();
        System.out.println("Saving all data!");
        Winter.instance.accountManager.saveAccounts();
    }

    public final void resize(int width, int height){
        background=new Background(width,height);
        System.out.println("Client Resizing");
    }


}
