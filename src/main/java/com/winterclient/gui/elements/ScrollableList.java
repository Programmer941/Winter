package com.winterclient.gui.elements;

import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScrollableList extends WinterGuiElement {

    public List<WinterGuiElement> guiElements = new ArrayList<>();

    Animation vertical;
    int translatedY;

    public ScrollableList(int x, int y, int width, int height) {
        super(x, y, width, height);
        vertical=new Animation(0);
        translatedY=0;
    }

    public int getIndex(int mouseX,int mouseY){
        float verticalAnimation = vertical.getValue();
        mouseY= (int) (mouseY-y-verticalAnimation);
        mouseX=mouseX-x;

        int index=-1;
        for (int i=0; i<guiElements.size();i++) {
            WinterGuiElement element = guiElements.get(i);
            if (element.mouseInBounds(mouseX, mouseY))
                if (element.isCollided(mouseX, mouseY)) {
                    index = i;
                }
        }

        return index;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        if(vertical.end!=translatedY)
        vertical.goTo(translatedY,0.4f);
        float verticalAnimation = vertical.getValue();

        startScissor();


        guiElements.forEach(element -> {
            element.draw(mouseX-x, (int) (mouseY-y-verticalAnimation));
        });

        endScissor();
    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {
        float verticalAnimation = vertical.getValue();
        mouseY= (int) (mouseY-y-verticalAnimation);
        mouseX=mouseX-x;

        for (WinterGuiElement element : guiElements)
            if (element.mouseInBounds(mouseX, mouseY))
                if (element.isCollided(mouseX, mouseY)) {
                    element.onClick(mouseX, mouseY,mouseButton);
                    return;
                }
    }

    @Override
    public void scroll(int amount){
        if(amount==0)
            return;

        amount/=120;
        amount*=130;
        translatedY+=amount;
    }

    @Override
    public void onRelease(int mouseX, int mouseY) {

    }

    @Override
    public void onType(char key, int keyCode) {

    }

    @Override
    public void onUpdate() {
        guiElements.forEach(element -> element.onUpdate());
    }

    @Override
    public boolean isCollided(int mouseX, int mouseY) {
        return true;
    }

    @Override
    public void start() {
        guiElements.forEach(element -> element.start());
    }

    @Override
    public void stop() {
        guiElements.forEach(element -> element.stop());

    }

    public void startScissor() {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glTranslatef(this.x, this.y, 0); // Scroll
        GL11.glTranslatef(0, vertical.getValue(), 0); // Scroll
        int translatedY = Minecraft.getMinecraft().displayHeight - y - height;
        GL11.glScissor(x, translatedY, width, height);

    }

    public void endScissor() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glPopMatrix();
    }

    public void addElement(WinterGuiElement e) {
        guiElements.add(e);
    }
}
