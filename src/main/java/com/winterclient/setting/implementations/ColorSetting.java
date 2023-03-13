package com.winterclient.setting.implementations;

import com.winterclient.setting.Setting;

import java.awt.*;

public class ColorSetting extends Setting<Color> {
    public ColorSetting(String name, Color value) {
        super(name, value);
    }

    @Override
    public void readValue(String data) {
        value=new Color(Integer.parseInt(data),true);
    }

    @Override
    public String writeValue() {
        return String.valueOf(value.getRGB());
    }
}
