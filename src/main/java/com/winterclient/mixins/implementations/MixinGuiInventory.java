package com.winterclient.mixins.implementations;

import com.winterclient.gui.screens.Inventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GuiInventory.class)
public class MixinGuiInventory {

    @Overwrite
    public void initGui()
    {
        Minecraft.getMinecraft().displayGuiScreen(new Inventory());
    }
}
