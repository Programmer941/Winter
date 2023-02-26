package com.winterclient.mixins.implementations;

import com.winterclient.util.PatchUtil;
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemModelGenerator.class)
public class MixinItemModelGenerator {

    @Inject(method = "func_178394_a", at = @At(value = "HEAD"), cancellable = true)
    private void GenerateBlockPartsStart(int layerInt, String layerString, TextureAtlasSprite textureAtlasSprite, CallbackInfoReturnable<List<BlockPart>> cir) {
        cir.setReturnValue(PatchUtil.createOutlineLayerElements(layerInt, layerString, textureAtlasSprite));
    }

    @Inject(method = "func_178394_a", at = @At(value = "TAIL"), cancellable = true)
    private void GenerateBlockPartsEnd(int p_178394_1_, String p_178394_2_, TextureAtlasSprite p_178394_3_, CallbackInfoReturnable<List<BlockPart>> cir) {
        //cir.setReturnValue(PatchUtil.createOutlineLayerElements(p_178394_1_, p_178394_2_, p_178394_3_));
        //PatchUtil.unlerpElements(list, sprite.getUvShrinkRatio());
    }

}
