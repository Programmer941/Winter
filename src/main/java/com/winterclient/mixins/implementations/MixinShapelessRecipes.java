package com.winterclient.mixins.implementations;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(ShapelessRecipes.class)
public interface MixinShapelessRecipes {
    @Accessor("recipeItems")
    List<ItemStack> getRecipeItems();
}
