package com.winterclient.gui.elements;

import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;
import com.winterclient.mod.Mod;

import java.awt.*;

public class ModButton extends WinterGuiElement {

    private Mod mod;

    public ModButton(Mod m, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.mod = m;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        RenderUtil.drawRect(x, y, width, height, 0x90000000);
        Fonts.raleway.drawCenteredString(mod.name, x + width / 2, y + height / 2 - Fonts.raleway.FONT_HEIGHT / 2);
        Color c = new Color(0x4DFF2B00, true);
        if (mod.enabled)
            c = new Color(0x4D26FF00, true);

        Images.selected.draw(x,y,20,20,c);
    }

    @Override
    public void onClick(int mouseX, int mouseY) {
        mod.toggle();
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
