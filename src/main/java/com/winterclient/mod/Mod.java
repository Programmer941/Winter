package com.winterclient.mod;

import com.winterclient.Winter;
import com.winterclient.event.EventBus;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;
import net.minecraft.client.Minecraft;


public class Mod {

    public Minecraft mc = Minecraft.getMinecraft();

    public String name;
    public String description;
    public Category category;
    public boolean enabled;

    public Mod(){
        Info info = this.getClass().getAnnotation(Info.class);
        name = info.name();
        description = info.description();
        category = info.category();
        setEnabled(info.enabled());
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
}
