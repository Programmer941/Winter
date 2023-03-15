package com.winterclient.gui.elements.newSettingElements.blockElement;

import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;

import java.awt.*;

public class BlockElement extends WinterGuiElement {

    Animation hoverAnimation;
    boolean selected=false;

    public BlockElement(int x, int y) {
        super(x, y, 50, 50);
        hoverAnimation=new Animation(0);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        //RenderUtil.drawRect(x,y,50,50,new Color(0x90000000,true));
        if(selected){
            if(hoverAnimation.end!=0.4f)
                hoverAnimation.goTo(0.4f,0.2f);
        }else {
            if (this.mouseInBounds(mouseX, mouseY)) {
                if (hoverAnimation.end != 0.2f)
                    hoverAnimation.goTo(0.2f, 0.2f);
            } else {
                if (hoverAnimation.end != 0)
                    hoverAnimation.goTo(0, 0.2f);
            }
        }
        float color=hoverAnimation.getValue();
        RenderUtil.drawRect(x,y,50,50,new Color(color,color,color, .6f));
        drawOverlay();
    }

    public void drawOverlay(){

    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {
        if(mouseInBounds(mouseX,mouseY))
        selected=!selected;
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
}
