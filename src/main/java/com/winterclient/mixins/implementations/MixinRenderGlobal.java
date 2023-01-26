package com.winterclient.mixins.implementations;

import com.winterclient.Winter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RenderGlobal.class)
public class MixinRenderGlobal {

    @Overwrite
    protected boolean isRenderEntityOutlines()
    {
        return Minecraft.getMinecraft().thePlayer != null && (Minecraft.getMinecraft().thePlayer.isSpectator() && Minecraft.getMinecraft().gameSettings.keyBindSpectatorOutlines.isKeyDown() || Winter.instance.test.isKeyDown()) ;
    }
}
