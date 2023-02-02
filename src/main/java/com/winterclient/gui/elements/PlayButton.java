package com.winterclient.gui.elements;

import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class PlayButton extends WinterGuiElement {

    String text;

    Animation linePos;

    public PlayButton(String text, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.text = text;
        linePos=new Animation(0);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x + 150, y + 10, 0.0f);
        GL11.glScalef(-1, -1, 0.0f);
        GL11.glTranslatef(-x - 150, -y - 10, 0.0f);
        Images.loadingBar.draw(x, (int) (y - 40-linePos.getValue()), 300, 20, new Color(0xEAEEF0));
        GL11.glPopMatrix();
        Fonts.raleway.drawCenteredString(text, x + 150, y);
        if(mouseInBounds(mouseX,mouseY)){
            linePos.goTo(15,0.1f);
        }else {
            linePos.goTo(0,0.1f);
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
}
