package com.winterclient.util;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class Crafts {

    public static ArrayList<Shaped> shapedArrayList = new ArrayList<>();

    public static class Shaped{
        public ItemStack output;
        public ArrayList<ItemStack> recipeItems;
        int width,height;
        public Shaped(int width,int height,ItemStack[] recipeItemsIn,ItemStack output){
            this.width=width;
            this.height=height;
            this.recipeItems=new ArrayList<>();
            for(ItemStack recipe : recipeItemsIn){
                recipeItems.add(recipe);
            }
            this.output=output;
            shapedArrayList.add(this);
        }
    }
}
