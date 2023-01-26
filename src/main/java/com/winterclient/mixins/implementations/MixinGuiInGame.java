package com.winterclient.mixins.implementations;

import com.winterclient.Winter;
import com.winterclient.event.implementations.OverlayEvent;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public class MixinGuiInGame {

    @Inject(method = "renderGameOverlay", at = @At("TAIL"))
    public void renderGameOverlay(float partialTicks,CallbackInfo callbackInfo) {
        Winter.instance.eventBus.fire(new OverlayEvent());
    }

}
