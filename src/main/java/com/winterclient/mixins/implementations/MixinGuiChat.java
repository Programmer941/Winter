package com.winterclient.mixins.implementations;

import com.winterclient.gui.screens.ChatEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GuiChat.class)
public class MixinGuiChat {

//    @Overwrite
//    public void initGui(){
//        Minecraft.getMinecraft().displayGuiScreen(new ChatEvents());
//    }


}
