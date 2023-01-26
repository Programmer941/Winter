package com.winterclient.mixins.implementations;

import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

    @Overwrite
    private void hurtCameraEffect(float partialTicks){

    }
}
