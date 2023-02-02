package com.winterclient.mod;

import com.winterclient.Winter;
import com.winterclient.event.EventBus;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;
import com.winterclient.setting.Setting;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;


public class Mod {

    public Minecraft mc = Minecraft.getMinecraft();

    public String name;
    public String description;
    public Category category;
    public boolean enabled;

    public ArrayList<Setting> settings;

    public Mod(){
        Info info = this.getClass().getAnnotation(Info.class);
        name = info.name();
        description = info.description();
        category = info.category();
        setEnabled(info.enabled());

        settings=new ArrayList<>();
    }

    public void toggle(){
        if(enabled){
            setEnabled(false);
        }else{
            setEnabled(true);
        }
    }

    public void setEnabled(boolean toggled)  {
        EventBus bus = Winter.instance.eventBus;
        if (toggled) {
            bus.subscribe(this);
        } else {
            bus.unsubscribe(this);
        }
        this.enabled = toggled;
    }

    public void addSetting(Setting setting){
        settings.add(setting);
    }

}
