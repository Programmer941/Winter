package com.winterclient.gui.core;

import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;

public class WinterGuiScreen extends GuiScreen {

    public List<WinterGuiElement> guiElements = new ArrayList<>();

    public void initGui() {
        guiElements.clear();
    }

    public void onGuiClosed() {

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        guiElements.forEach(element -> element.draw(mouseX,mouseY));
    }

    public void updateScreen() {

    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for(WinterGuiElement element : guiElements)
            if(mouseX>element.x && mouseX<element.x+element.width && mouseY>element.y && mouseY<element.y+element.height)
                if(element.isCollided(mouseX,mouseY))
                    element.onClick(mouseX,mouseY);
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {

    }

    public void addElement(WinterGuiElement e){
        guiElements.add(e);
    }

}
