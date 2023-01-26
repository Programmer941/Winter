package com.winterclient.setting.implementations;

import com.winterclient.setting.Setting;

public class IntSetting extends Setting<Integer> {
    public IntSetting(String name, Integer value) {
        super(name, value);
    }

    @Override
    public void readValue(String data) {
        value=Integer.parseInt(data);
    }

    @Override
    public String writeValue() {
        return value.toString();
    }
}
