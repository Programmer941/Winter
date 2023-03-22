package com.winterclient.gui.screens;

import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;
import com.winterclient.mod.Mod;
import com.winterclient.setting.implementations.BooleanSetting;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ModSettingMenu extends WinterGuiScreen {

    Mod mod;


    public ModSettingMenu(Mod mod){
        this.mod=mod;
    }
    @Override
    public void init() {
        drawBackground=false;
        mod.elements.forEach(element -> {
            addElement(element);
        });
    }

    @Override
    public void end() {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Images.loadingBar.draw(width/2-250,height/2-235-33-10,500,33,new Color(0xEAEEF0));
        GL11.glPushMatrix();
        GL11.glTranslatef(width/2-250, 0, 0.0f);
        GL11.glScalef(-1, -1, 0.0f);
        GL11.glTranslatef(-width/2-250, 0, 0.0f);
        Images.loadingBar.draw(width/2-250,-height/2-235-33-10, 500, 33, new Color(0xEAEEF0));
        GL11.glPopMatrix();
        RenderUtil.drawRect(width/2-415,height/2-235,830,470,new Color(0x90000000,true));

        Fonts.raleway.drawCenteredString(mod.name,width/2,height/2-235-Fonts.raleway.FONT_HEIGHT/2+20);
        Fonts.ralewaySmall.drawCenteredString(mod.description,width/2,height/2-235-Fonts.ralewaySmall.FONT_HEIGHT/2+50);
        RenderUtil.drawLine(width/2-200,height/2-235+70,width/2+200,height/2-235+70,2,Color.white);
    }

    @Override
    public void update() {

    }

    @Override
    public void click(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void release(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void drag(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void type(char typedChar, int keyCode) {

    }
}
