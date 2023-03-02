package com.winterclient.mixins.implementations;


import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {

//    @Inject(method = "damageEntity", at = @At("HEAD"), cancellable = true)
//    public void damageEntity(DamageSource damageSrc, float damageAmount, CallbackInfo callbackinfo)
//    {
//        System.out.println("HI");
//    }
}
