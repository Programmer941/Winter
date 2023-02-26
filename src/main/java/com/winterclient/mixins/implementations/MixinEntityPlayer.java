package com.winterclient.mixins.implementations;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public class MixinEntityPlayer {

    @Inject(method = "damageEntity", at = @At("HEAD"), cancellable = true)
    protected void damageEntity(DamageSource damageSrc, float damageAmount, CallbackInfo callbackinfo){
        System.out.println("taking damage");
        if (!damageSrc.isUnblockable() && Minecraft.getMinecraft().thePlayer.isBlocking() && damageAmount > 0.0F)
        {
            System.out.println("BLOCKED ATTACK");
        }else{
            System.out.println("HIT REGULAR ATTACK");
        }
    }

}
