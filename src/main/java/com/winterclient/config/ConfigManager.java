package com.winterclient.config;

import net.minecraft.client.settings.GameSettings;

import java.io.File;

public class ConfigManager {

    protected File configFolder;

    public ConfigManager(){
        configFolder=new File("Winter_Client");
        if (!configFolder.exists())
            configFolder.mkdir();
    }

    public void readConfig(){

    }

    public void saveConfig(){

    }
}
