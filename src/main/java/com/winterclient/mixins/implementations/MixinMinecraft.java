package com.winterclient.mixins.implementations;

import com.winterclient.Winter;
import com.winterclient.event.implementations.ClickEvent;
import com.winterclient.event.implementations.KeyEvent;
import com.winterclient.event.implementations.TickEvent;
import com.winterclient.gui.screens.OverlayMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.Session;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "createDisplay", at = @At("TAIL"))
    public void createDisplay(CallbackInfo callbackInfo) {
        Display.setTitle("Loading Winter Client");
        Winter.instance.preInit();
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
        Winter.instance.resize(Display.getWidth(),Display.getHeight());
        Display.setResizable(false);
        Display.setResizable(true);
    }

    @Overwrite
    public void drawSplashScreen(TextureManager textureManagerInstance){
        Winter.instance.loadingScreen.setProgress(0);
    }

    @Inject(method = "resize", at = @At("TAIL"))
    public void resize(int width, int height,CallbackInfo callbackInfo){
        Winter.instance.resize(width,height);
    }


    @Inject(method = "dispatchKeypresses", at = @At("HEAD"))
    public void dispatchKeypresses(CallbackInfo callbackInfo) {
        char character = Keyboard.getEventCharacter();
        int keyCode = Keyboard.getEventKey();
        boolean pressed = Keyboard.getEventKeyState();
        if(keyCode==Keyboard.KEY_RSHIFT){
            Minecraft.getMinecraft().displayGuiScreen(new OverlayMenu());
        }
        if(Minecraft.getMinecraft().theWorld!=null && !Minecraft.getMinecraft().isGamePaused()){
            Winter.instance.eventBus.fire(new KeyEvent(character,keyCode,pressed));
        }
    }

    @Inject(method = "clickMouse", at = @At("HEAD"))
    public void clickMouse(CallbackInfo callbackInfo){
        Winter.instance.eventBus.fire(new ClickEvent(true));
    }

    @Inject(method = "rightClickMouse", at = @At("HEAD"))
    public void rightClickMouse(CallbackInfo callbackInfo){
        Winter.instance.eventBus.fire(new ClickEvent(false));
    }


    @Inject(method = "runGameLoop", at = @At("TAIL"))
    public void runGameLoop(CallbackInfo callbackInfo) {
        if(Minecraft.getMinecraft().theWorld!=null && !Minecraft.getMinecraft().isGamePaused()){
            Winter.instance.eventBus.fire(new TickEvent());
        }
    }

    @Inject(method = "getSession", at = @At("HEAD"), cancellable = true)
    public void getSession(CallbackInfoReturnable<Session> cir) {
        if (Winter.instance.accountManager != null)
            if (Winter.instance.accountManager.activeAccount != null)
                cir.setReturnValue(Winter.instance.accountManager.activeAccount.getSession());
    }

    @Overwrite
    public int getLimitFramerate() {
        return Minecraft.getMinecraft().theWorld == null && Minecraft.getMinecraft().currentScreen != null ? 170 : Minecraft.getMinecraft().gameSettings.limitFramerate;
    }


}
