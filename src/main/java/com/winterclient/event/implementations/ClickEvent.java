package com.winterclient.event.implementations;

import com.winterclient.event.Event;

public class ClickEvent extends Event {

    public boolean left;

    public ClickEvent(boolean left) {
        this.left = left;
    }
}
