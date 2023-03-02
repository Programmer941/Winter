package com.winterclient.mod.implementations;

import com.winterclient.mod.Mod;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;

@Info(name = "Crosshair", description = "Custom Crosshair!", category = Category.Visual, enabled = true)
public class Crosshair extends Mod {
    public static boolean custom=false;
}
