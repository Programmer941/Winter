package com.winterclient.gui.screens;

import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.elements.settingElements.BooleanButton;
import com.winterclient.gui.util.RenderUtil;
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
        System.out.println(mod.settings.size());
        mod.settings.forEach(setting -> {
            System.out.println(setting.name);
            if (setting instanceof BooleanSetting){
                addElement(new BooleanButton(setting.name,width/2-415+10,height/2-235+10,240,60, (BooleanSetting) setting));
                System.out.println("ADDED SETTING");
            }
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
        RenderUtil.drawRect(width/2-415,height/2-235,830,470,0x90000000);
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
