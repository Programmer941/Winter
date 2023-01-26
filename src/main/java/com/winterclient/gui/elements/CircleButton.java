package com.winterclient.gui.elements;

import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.resources.Images;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class CircleButton extends WinterGuiElement {

    Animation scaleAnimation;

    public CircleButton(int x, int y, int radius) {
        super(x, y, radius, radius);
        scaleAnimation = new Animation(1);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        if (isCollided(mouseX, mouseY)) {
            if (scaleAnimation.end != 1.2f)
                scaleAnimation.goTo(1.2f, 0.3f);
        } else {
            if (scaleAnimation.end != 1f)
                scaleAnimation.goTo(1, 0.3f);
        }
        float scale = scaleAnimation.getValue();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + width / 2, y + height / 2, 1);
        GlStateManager.scale(scale, scale, 1);
        GlStateManager.translate(-(x + width / 2), -(y + height / 2), 1);
        Images.circle.draw(x, y, width, height);
        GlStateManager.popMatrix();

    }

    @Override
    public void onClick(int mouseX, int mouseY) {

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
        float a = (x + width / 2) - mouseX;
        float b = y + height / 2 - mouseY;
        double distance = Math.sqrt(a * a + b * b);
        return distance < (width/2 * scaleAnimation.getValue());
    }
}
