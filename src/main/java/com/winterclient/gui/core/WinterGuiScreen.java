package com.winterclient.gui.core;

import com.winterclient.Winter;
import com.winterclient.gui.Background;
import com.winterclient.gui.screens.MainMenu;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.util.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class WinterGuiScreen extends GuiScreen {

    public WinterGuiScreen previousScreen;
    public PreviousElements previousElements;

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
        draw(mouseX, mouseY);

        if (previousElements != null)
            previousElements.draw(mouseX, mouseY, partialTicks);

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
            System.out.println(previousScreen);
            if (!(this instanceof MainMenu)) {
                if(previousScreen!=null){
                    displayGuiScreen(previousScreen);
                }else{
                    displayGuiScreen(new MainMenu());
                }
            }

            if (this.mc.currentScreen == null) {
                this.mc.setIngameFocus();
            }
        }

        for (WinterGuiElement element : guiElements)
            element.onType(typedChar,keyCode);

        type(typedChar, keyCode);
    }

    public void handleMouseInput() throws IOException
    {
        int scrollWheel = Mouse.getDWheel();
        super.handleMouseInput();
        if(scrollWheel != 0) {
            int mouseX = Mouse.getEventX() * this.width / this.mc.displayWidth;
            int mouseY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
            this.scroll(scrollWheel, mouseX, mouseY);
        }
    }

    public void scroll(int amount, int mouseX, int mouseY) {
        for (WinterGuiElement element : guiElements)
            if (element.mouseInBounds(mouseX, mouseY))
                if (element.isCollided(mouseX, mouseY)) {
                    element.scroll(amount);
                    return;
                }
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

    public void displayGuiScreen(WinterGuiScreen screen) {
        if(screen.previousScreen==null)
        screen.previousScreen = this;
        screen.previousElements=new PreviousElements(this.guiElements);
        Minecraft.getMinecraft().displayGuiScreen(screen);
    }

    private class PreviousElements {

        public List<WinterGuiElement> elements;
        Timer endTimer;

        public PreviousElements(List<WinterGuiElement> elements){
            this.elements=elements;
            elements.forEach(element -> {
                element.stop();
            });
            this.endTimer=new Timer(1);
        }

        public void draw(int mouseX, int mouseY, float partialTicks){
            if(elements==null)
                return;
            if(endTimer.isFinished()){
                elements=null;
                return;
            }
            elements.forEach(element -> {
                //RenderUtil.drawRect(element.x,element.y,element.width,element.height, Color.red);
                element.draw(mouseX,mouseY);
            });
        }
    }

}
