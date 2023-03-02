package com.winterclient.mixins.implementations;

import net.minecraft.profiler.PlayerUsageSnooper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PlayerUsageSnooper.class)
public class MixinPlayerUsageSnooper {

    @Overwrite
    public void startSnooper(){

    }

}
