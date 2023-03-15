package com.winterclient.gui.core;

import com.winterclient.Winter;
import com.winterclient.gui.Background;
import com.winterclient.gui.screens.MainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class WinterGuiScreen extends GuiScreen {

    public List<WinterGuiElement> guiElements = new ArrayList<>();

    public boolean drawBackground = true;

    @Override
    public void initGui() {
        guiElements.clear();
        init();
    }

    @Override
    public void updateScreen() {
        update();
        for (WinterGuiElement element : guiElements)
            element.onUpdate();
    }

    @Override
    public void onGuiClosed() {
        end();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if(drawBackground)
        Winter.instance.background.draw();
        draw(mouseX, mouseY);
        guiElements.forEach(element -> element.draw(mouseX, mouseY));

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (WinterGuiElement element : guiElements)
            if (element.mouseInBounds(mouseX, mouseY))
                if (element.isCollided(mouseX, mouseY)) {
                    element.onClick(mouseX, mouseY,mouseButton);
                    return;
                }
        click(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        release(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int mouseButton, long timeSinceLastClick) {
        drag(mouseX, mouseY, mouseButton);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            if (!(this instanceof MainMenu)) {
                this.mc.displayGuiScreen((GuiScreen) null);
            }

            if (this.mc.currentScreen == null) {
                this.mc.setIngameFocus();
            }
        }

        for (WinterGuiElement element : guiElements)
            element.onType(typedChar,keyCode);

        type(typedChar, keyCode);
    }

    public abstract void init();

    public abstract void end();

    public abstract void draw(int mouseX, int mouseY);

    public void update(){

    };

    public abstract void click(int mouseX, int mouseY, int mouseButton);

    public abstract void release(int mouseX, int mouseY, int mouseButton);

    public abstract void drag(int mouseX, int mouseY, int mouseButton);

    public abstract void type(char typedChar, int keyCode);

    public void addElement(WinterGuiElement e) {
        guiElements.add(e);
    }

}
