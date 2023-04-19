package com.winterclient.gui.elements;

import com.winterclient.Winter;
import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class MenuButton extends WinterGuiElement {

    public String text;

    Animation hover;
    Animation fade;

    public MenuButton(String text, int x, int y,int width,int height) {
        super(x, y, width, height);
        this.text = text;
        hover=new Animation(0);
        fade=new Animation(0);
        start();
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        float value= hover.getValue();
        float fadeValue = fade.getValue();
        RenderUtil.drawRoundRect(x,y,width,height,new Color(0,0,0,(.35f-value)*fadeValue),30);
        Fonts.raleway.drawCenteredString(text, x + width/2, y+height/2-Fonts.raleway.FONT_HEIGHT/2,new Color(1,1,1,fadeValue));
        if(this.mouseInBounds(mouseX,mouseY)){
            if(hover.end==0){
                hover.goTo(.2f,0.2f);
            }
        }else{
            if(hover.end!=0){
                hover.goTo(0,0.2f);
            }
        }
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
        return true;
    }

    @Override
    public void start() {
        fade.goTo(1,0.4f);
    }

    @Override
    public void stop() {
        fade.goTo(0,0.4f);
    }
}
