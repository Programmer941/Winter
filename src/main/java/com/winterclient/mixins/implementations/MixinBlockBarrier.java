package com.winterclient.mixins.implementations;

import net.minecraft.block.BlockBarrier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockBarrier.class)
public class MixinBlockBarrier {

    @Overwrite
    public int getRenderType()
    {
        return 3;
    }

}
