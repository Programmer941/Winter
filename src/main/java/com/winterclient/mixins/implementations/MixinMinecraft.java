package com.winterclient.mixins.implementations;

import com.winterclient.Winter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "createDisplay", at = @At("HEAD"))
    public void createDisplay(CallbackInfo callbackInfo) {
        Display.setTitle("Loading Winter Client");
    }

    @Inject(method = "shutdownMinecraftApplet", at = @At("HEAD"))
    public void shutdownMinecraftApplet(CallbackInfo callbackInfo) {
        Winter.instance.end();
    }

    @Inject(method = "startGame", at = @At("TAIL"))
    public void startGame(CallbackInfo callbackInfo) {
        Winter.instance.init();
    }

    @Inject(method = "toggleFullscreen", at = @At("TAIL"))
    public void toggleFullscreen(CallbackInfo callbackInfo) {
        //Game won't let resize because blind.
        Display.setResizable(false);
        Display.setResizable(true);
    }

    @Inject(method = "getSession", at = @At("HEAD"), cancellable = true)
    public void getSession(CallbackInfoReturnable<Session> cir) {
        if (Winter.instance.accountManager != null)
            if (Winter.instance.accountManager.activeAccount != null)
                cir.setReturnValue(Winter.instance.accountManager.activeAccount.getSession());
    }

}
