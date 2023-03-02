package com.winterclient.mixins.implementations;

import com.winterclient.gui.core.WinterGuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.Session;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ScaledResolution.class)
public class MixinScaledResolution {

    @Inject(method = "getScaledWidth", at = @At("HEAD"), cancellable = true)
    public void getScaledWidth(CallbackInfoReturnable<Integer> cir){
        if(Minecraft.getMinecraft().currentScreen instanceof WinterGuiScreen)
            cir.setReturnValue(Minecraft.getMinecraft().displayWidth);
    }

    @Inject(method = "getScaledHeight", at = @At("HEAD"), cancellable = true)
    public void getScaledHeight(CallbackInfoReturnable<Integer> cir){
        if(Minecraft.getMinecraft().currentScreen instanceof WinterGuiScreen)
            cir.setReturnValue(Minecraft.getMinecraft().displayHeight);
    }

    @Inject(method = "getScaledWidth_double", at = @At("HEAD"), cancellable = true)
    public void getScaledWidth_double(CallbackInfoReturnable<Double> cir){
        if(Minecraft.getMinecraft().currentScreen instanceof WinterGuiScreen)
            cir.setReturnValue((double) Minecraft.getMinecraft().displayWidth);
    }

    @Inject(method = "getScaledHeight_double", at = @At("HEAD"), cancellable = true)
    public void getScaledHeight_double(CallbackInfoReturnable<Double> cir){
        if(Minecraft.getMinecraft().currentScreen instanceof WinterGuiScreen)
            cir.setReturnValue((double) Minecraft.getMinecraft().displayHeight);
    }

    @Inject(method = "getScaleFactor", at = @At("HEAD"), cancellable = true)
    public void getScaleFactor(CallbackInfoReturnable<Integer> cir){
        if(Minecraft.getMinecraft().currentScreen instanceof WinterGuiScreen)
            cir.setReturnValue(1);
    }

//    @Overwrite
//    public int getScaledWidth()
//    {
//        return Display.getWidth();
//    }
//    @Overwrite
//    public int getScaledHeight()
//    {
//        return Display.getHeight();
//    }
//    @Overwrite
//    public double getScaledWidth_double()
//    {
//        return Display.getWidth();
//    }
//    @Overwrite
//    public double getScaledHeight_double()
//    {
//        return Display.getHeight();
//    }
//    @Overwrite
//    public int getScaleFactor()
//    {
//        return 1;
//    }
}
