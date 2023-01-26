package com.winterclient.event.implementations;

import com.winterclient.event.Event;

public class KeyEvent extends Event {


    public char key;
    public int keycode;
    public boolean pressed;
    public KeyEvent(char key,int keycode, boolean pressed){
        this.key=key;
        this.keycode=keycode;
        this.pressed=pressed;
    }
}
