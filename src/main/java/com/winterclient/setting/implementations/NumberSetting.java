package com.winterclient.setting.implementations;

import com.winterclient.setting.Setting;

public class NumberSetting extends Setting<Number> {
    public NumberSetting(String name, Number value) {
        super(name, value);
    }

    @Override
    public void readValue(String data) {
        value=Float.valueOf(data);
    }

    @Override
    public String writeValue() {
        return value.toString();
    }
}
