package com.winterclient.mod;

import com.winterclient.Winter;
import com.winterclient.event.EventBus;
import com.winterclient.gui.core.SettingGuiElement;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;
import com.winterclient.setting.Setting;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;


public class Mod {

    public Minecraft mc = Minecraft.getMinecraft();

    public static Mod instance;

    public String name;
    public String description;
    public Category category;
    public boolean enabled;

    public ArrayList<Setting> settings;

    public ArrayList<SettingGuiElement> elements;
    //used to position new elements when added
    private SettingGuiElement lastElement;

    public Mod(){
        Info info = this.getClass().getAnnotation(Info.class);
        name = info.name();
        description = info.description();
        category = info.category();
        setEnabled(info.enabled());
        settings=new ArrayList<>();
        elements=new ArrayList<>();
        instance = this;
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



    public void addSettingElement(SettingGuiElement element){
        int regularX=mc.displayWidth/2-415+10;
        int regularY=mc.displayHeight/2-235;
        int offsetY=70;
        if(lastElement==null){
            element.x=regularX;
            element.y=regularY+offsetY;
            lastElement=element;
            elements.add(element);
            return;
        }
        //space on same line
        if(lastElement.x+lastElement.width+element.width+10<mc.displayWidth/2+405){
            System.out.println("old Line");
            element.x=lastElement.x+lastElement.width+10;
            element.y=lastElement.y;
        }else{
            System.out.println("new line");
            element.x=regularX;
            element.y=lastElement.y+offsetY;
        }

        elements.add(element);
    }

}
