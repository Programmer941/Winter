package com.winterclient.gui.screens;

import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.mixins.implementations.MixinShapelessRecipes;
import com.winterclient.util.Crafts;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class Inventory extends WinterGuiScreen {

    ArrayList<ItemStack> craftable = new ArrayList<>();

    @Override
    public void init() {

        drawBackground = false;
        CraftingManager.getInstance().getRecipeList().forEach(iRecipe -> {
            if (iRecipe instanceof ShapelessRecipes) {
                ArrayList<ItemStack> required = new ArrayList<>();
                ArrayList<Integer> has = new ArrayList<>();

                ShapelessRecipes shapelessRecipes = (ShapelessRecipes) iRecipe;
                ((MixinShapelessRecipes) shapelessRecipes).getRecipeItems().forEach(itemStack -> {
                    boolean addItem = true;
                    for (int e = 0; e < required.size(); e++) {
                        ItemStack cur = required.get(e);
                        if (itemStack.getItem().equals(cur.getItem()) && itemStack.getDisplayName().equals(cur.getDisplayName())) {
                            addItem = false;
                            cur.stackSize += 1;
                            required.set(e, cur);
                        }
                    }
                    if (addItem)
                        required.add(itemStack);

                });

                for (int a = 0; a < required.size(); a++) {
                    has.add(0);
                }
                for (int i = 0; i < mc.thePlayer.inventory.mainInventory.length; i++) {
                    ItemStack current = mc.thePlayer.inventory.mainInventory[i];
                    if (current != null)
                        for (int l = 0; l < required.size(); l++) {
                            if (current.getItem().equals(required.get(l).getItem()) && current.getDisplayName().equals(required.get(l).getDisplayName())) {
                                int totalPossible = (int) Math.floor(current.stackSize / required.get(l).stackSize);
                                has.set(l, has.get(l) + totalPossible);
                            }
                        }
                }
                int totalCraftable = 99;
                for (int b = 0; b < has.size(); b++) {
                    if (has.get(b) < totalCraftable)
                        totalCraftable = has.get(b);
                }
                if (totalCraftable > 0) {
                    craftable.add(shapelessRecipes.getRecipeOutput());
                    System.out.println(shapelessRecipes.getRecipeOutput().getDisplayName() + " TOTAL CRAFTABLE: " + totalCraftable);
                }

            }
        });
//        for (Crafts.Shaped shaped : Crafts.shapedArrayList) {
//            System.out.println("Shaped checked?");
//            ArrayList<ItemStack> required = new ArrayList<>();
//            ArrayList<Integer> has = new ArrayList<>();
//
//
//            for (ItemStack recipeItem : shaped.recipeItems) {
//                boolean addItem = true;
//                for (int e = 0; e < required.size(); e++) {
//                    ItemStack cur = required.get(e);
//                    if(recipeItem!=null)
//                    if (recipeItem.getItem().equals(cur.getItem())) {
//                        addItem = false;
//                        cur.stackSize += 1;
//                        required.set(e, cur);
//                    }
//                }
//                if (addItem)
//                    required.add(recipeItem);
//
//                for (int a = 0; a < required.size(); a++) {
//                    has.add(0);
//                }
//                for (int i = 0; i < mc.thePlayer.inventory.mainInventory.length; i++) {
//                    ItemStack current = mc.thePlayer.inventory.mainInventory[i];
//                    if (current != null)
//                        for (int l = 0; l < required.size(); l++) {
//                            if (current.getItem().equals(required.get(l).getItem()) && current.getDisplayName().equals(required.get(l).getDisplayName())) {
//                                int totalPossible = (int) Math.floor(current.stackSize / required.get(l).stackSize);
//                                has.set(l, has.get(l) + totalPossible);
//                            }
//                        }
//                }
//                int totalCraftable = 64;
//                for (int b = 0; b < has.size(); b++) {
//                    if (has.get(b) < totalCraftable)
//                        totalCraftable = has.get(b);
//                }
//                if (totalCraftable > 0) {
//                    craftable.add(shaped.output);
//                    System.out.println(shaped.output.getDisplayName() + " TOTAL CRAFTABLE: " + totalCraftable);
//                }
//            }
        //}


    }


    @Override
    public void end() {

    }

    public int slotHovered(int mouseX, int mouseY) {
        int xSlot = (mouseX - (width / 2 - 300 + 75) + 4) / 72;
        int ySlot = ((height / 2 + 128 + 12 - 8) - mouseY) / 72;
        int negX = mouseX - (width / 2 - 300 + 75) + 4;
        int negY = (height / 2 + 128 + 12 - 4) - mouseY;
        if (negX > -4 && negY > -4 && xSlot < 9 && ySlot < 4) {
            int index = xSlot + ySlot * 9;
            return index;
        }
        return -1;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        int hovered = slotHovered(mouseX, mouseY);
        int currentslot = mc.thePlayer.inventory.currentItem;
        int scale = 150;
        int playerX = width / 2 - 310;
        int playerY = (int) (height / 2 + scale * 0.535714286f) + 62;
        int inventoryWidth = 440;
        int inventoryHeight = 320;
        drawEntityOnScreen(playerX, playerY, scale, playerX - mouseX, playerY - mouseY - scale * 2 + scale / 3f + 6, mc.thePlayer);
        //RenderUtil.drawRect(width/2-inventoryWidth/2,height/2-inventoryHeight/2,inventoryWidth,inventoryHeight,new Color(0x90000000,true));
        int boxSize = 64;
        int offsetX = width / 2 - 300 + scale / 2;
        int offsetY = height / 2 + boxSize + 12;
        int offsetItem = (boxSize - 16 * 3) / 2;
        for (int i = 0; i < 36; i++) {
            int yIndex = (i + 1) % 9;
            ItemStack itemstack = Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i];
            if (i == hovered) {
                RenderUtil.drawRect(offsetX, offsetY, boxSize, boxSize, new Color(0x90FFFFFF, true));
            } else if (i == currentslot) {
                RenderUtil.drawRect(offsetX, offsetY, boxSize, boxSize, new Color(0xB2000000, true));
            } else {
                RenderUtil.drawRect(offsetX, offsetY, boxSize, boxSize, new Color(0x90000000, true));
            }
            GL11.glPushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glTranslatef(offsetX + offsetItem, offsetY + offsetItem, 1);
            GL11.glScalef(3, 3, 1);
            GL11.glTranslatef(-offsetX - offsetItem, -offsetY - offsetItem, 1);
            mc.getTextureManager().bindTexture(Gui.icons);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(itemstack, offsetX + offsetItem, offsetY + offsetItem);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glPopMatrix();
            if (itemstack != null && itemstack.stackSize > 1)
                Fonts.ralewaySmall.drawString(String.valueOf(itemstack.stackSize), offsetX + 64 - Fonts.ralewaySmall.getStringWidth(String.valueOf(itemstack.stackSize)) - 2, offsetY + 36);
            offsetX += boxSize + 8;
            if (yIndex == 0) {
                offsetY -= boxSize + 8;
                offsetX = width / 2 - 300 + scale / 2;
            }
        }
        int craftableX = 100;
        int craftableY = 100;
        for (int b = 0; b < craftable.size(); b++) {
            GL11.glPushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glTranslatef(craftableX, craftableY, 1);
            GL11.glScalef(3, 3, 1);
            GL11.glTranslatef(-craftableX, -craftableY, 1);
            Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(craftable.get(b), craftableX, craftableY);
            GL11.glPopMatrix();
            craftableX += 90;
        }
    }

    public void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent) {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GlStateManager.translate((float) posX, (float) posY, 50.0F);
        GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float) Math.atan((double) (mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.renderYawOffset = (float) Math.atan((double) (mouseX / 40.0F)) * 20.0F;
        ent.rotationYaw = (float) Math.atan((double) (mouseX / 40.0F)) * 40.0F;
        ent.rotationPitch = -((float) Math.atan((double) (mouseY / 40.0F))) * 20.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntityWithPosYaw(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    @Override
    public void click(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void release(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void drag(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void type(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_E)
            Minecraft.getMinecraft().displayGuiScreen(null);
    }
}
