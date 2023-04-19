package com.winterclient.gui.elements;

import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.image.DefaultImage;

import java.awt.*;

public class Title extends WinterGuiElement {

    DefaultImage title;

    Animation fade;
    public Title(int x, int y, int width, int height, DefaultImage title) {
        super(x, y, width, height);
        this.title=title;
        fade=new Animation(0);
        start();
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        title.draw(x,y,width,height,new Color(1,1,1,fade.getValue()));
    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void onRelease(int mouseX, int mouseY) {

    }

    @Override
    public void onType(char key, int keyCode) {

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public boolean isCollided(int mouseX, int mouseY) {
        return false;
    }

    @Override
    public void start() {
        fade.goTo(1,0.4f,0.4f);
    }

    @Override
    public void stop() {
        fade.goTo(0,0.4f);

    }
}
