package com.winterclient.gui.core;

public abstract class WinterGuiElement {

    public int x, y, width, height;

    public WinterGuiElement(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(int mouseX, int mouseY);

    public abstract void onClick(int mouseX, int mouseY);

    public abstract void onRelease(int mouseX, int mouseY);

    public abstract void onType(char key, int keyCode);

    public abstract void onUpdate();

    public abstract boolean isCollided(int mouseX, int mouseY);

    public boolean mouseInBounds(int mouseX, int mouseY){
        return mouseX>this.x && mouseX<this.x+this.width && mouseY>this.y && mouseY<this.y+this.height;
    }
}
