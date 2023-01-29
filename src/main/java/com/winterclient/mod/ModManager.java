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
        addMod(new TestMod());
        addMod(new TestMod());
        addMod(new TestMod());
        addMod(new TestMod());
        addMod(new TestMod());
        addMod(new TestMod());
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
