package com.winterclient.setting.implementations;

import com.winterclient.setting.Setting;

public class BooleanSetting extends Setting<Boolean> {
    public BooleanSetting(String name, Boolean value) {
        super(name, value);
    }

    @Override
    public void readValue(String data) {
        value=Boolean.parseBoolean(data);
    }

    @Override
    public String writeValue() {
        return value.toString();
    }
}
