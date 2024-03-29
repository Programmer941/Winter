package com.winterclient.mod;

import com.winterclient.mod.implementations.*;

import java.util.ArrayList;
import java.util.List;

public class ModManager {

    private final List<Mod> modList;

    public ModManager() {
        this.modList = new ArrayList<>();
        addModules();
    }

    public final void addModules(){
        addMod(new ToggleSprint());
        addMod(new FPS(20,20,100,50));
        addMod(new CPS(140,20,100,50));
        addMod(new KeyStrokes(20+24,100,172,172));
        addMod(new Ping(280,20,100,50));
        addMod(new PotionEffects(20,300,150,300));
        addMod(new Chat(100,100,800,500));
        addMod(new Scoreboard(700,400,320,480));
        addMod(new ClickDelay(500,30,100,50));
        addMod(new Crosshair());
        addMod(new TestMod());
        addMod(new TestMod());
        addMod(new TestMod());
        addMod(new TestMod());
        addMod(new TestMod());
        addMod(new TestMod());
        addMod(new TestMod());
        addMod(new TestMod());
    }

    public void addMod(Mod m){
        modList.add(m);
    }

    public List<Mod> getModules() {
        return modList;
    }

    public Mod getModule(String name) {
        for (Mod m : modList){
            if(m.name.equals(name))
                return m;
        }
        return null;
    }
}
