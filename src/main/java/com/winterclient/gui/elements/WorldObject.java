package com.winterclient.gui.elements;

import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;
import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.SaveFormatComparator;

public class WorldObject extends WinterGuiElement {

    public SaveFormatComparator world;
    public WorldObject(SaveFormatComparator world, int x, int y) {
        super(x,y,600,120);
        this.world=world;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Images.drop.draw(x,y,width,height);
        Fonts.raleway.drawCenteredString(world.getDisplayName(),x+width/2,y+5);
    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {
        Minecraft.getMinecraft().launchIntegratedServer(world.getFileName(), world.getDisplayName(), null);

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
