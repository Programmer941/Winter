package com.winterclient.mixins.implementations;

import com.winterclient.gui.util.resources.Images;
import net.minecraft.client.LoadingScreenRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(LoadingScreenRenderer.class)
public class MixinLoadingScreenRenderer {

    @Overwrite
    public void setLoadingProgress(int progress){
        //Images.background.draw();
    }

}
