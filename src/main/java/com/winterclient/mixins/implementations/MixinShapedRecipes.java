package com.winterclient.mixins.implementations;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ShapedRecipes.class)
public interface MixinShapedRecipes {
//
//    @Accessor("recipeItems")
//    ItemStack[] getRecipeItems();
}