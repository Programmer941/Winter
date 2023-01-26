package com.winterclient.setting;

public abstract class Setting<T> {

    public String name;
    public T value;

    public Setting(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public abstract void readValue(String data);

    public abstract String writeValue();
}
