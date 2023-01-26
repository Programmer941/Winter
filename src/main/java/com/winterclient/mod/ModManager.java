package com.winterclient.mod;

import com.winterclient.mod.implementations.FPS;
import com.winterclient.mod.implementations.TestMod;
import com.winterclient.mod.implementations.ToggleSprint;

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
        addMod(new FPS());
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
