package com.winterclient.mixins.implementations;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ScaledResolution.class)
public class MixinScaledResolution {

    @Overwrite
    public int getScaledWidth(){
        return Minecraft.getMinecraft().displayWidth;
    }

    @Overwrite
    public int getScaledHeight(){
        return Minecraft.getMinecraft().displayHeight;
    }

    @Overwrite
    public double getScaledWidth_double(){
        return Minecraft.getMinecraft().displayWidth;
    }

    @Overwrite
    public double getScaledHeight_double(){
        return Minecraft.getMinecraft().displayHeight;
    }

    @Overwrite
    public int getScaleFactor(){
        return 1;
    }

}
