package com.winterclient.config;

import com.google.gson.*;
import com.winterclient.Winter;
import com.winterclient.account.Account;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class SettingManager {

    File dataFolder;
    File settingFile;

    public SettingManager() {
        dataFolder = new File("Winter");
        if (!dataFolder.exists())
            dataFolder.mkdir();
        settingFile = new File(dataFolder, "settings.json");
        if (!settingFile.exists()) {
            try {
                settingFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readSettings() {
        try {
            FileReader reader = new FileReader(settingFile);
            JsonElement mainElement = JsonParser.parseReader(reader);
            reader.close();
            if (mainElement.isJsonNull())
                return;
            JsonObject main = mainElement.getAsJsonObject();
            Winter.instance.modManager.getModules().forEach(mod -> {
                JsonObject modObject = main.get(mod.name).getAsJsonObject();
                mod.settings.forEach(setting -> {
                    String value = modObject.get(setting.name).getAsString();
                    setting.readValue(value);
                });
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeSettings() {
        JsonObject main = new JsonObject();
        Winter.instance.modManager.getModules().forEach(mod -> {
            JsonObject modObject = new JsonObject();
            mod.settings.forEach(setting -> {
                modObject.addProperty(setting.name, setting.writeValue());
            });
            main.add(mod.name, modObject);
        });
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter fileWriter = new FileWriter(settingFile);
            gson.toJson(main, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
