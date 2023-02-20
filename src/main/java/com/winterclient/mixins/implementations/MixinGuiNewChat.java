package com.winterclient.mixins.implementations;

import com.winterclient.Winter;
import com.winterclient.mod.implementations.Chat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiNewChat.class)
public abstract class MixinGuiNewChat {

//    @Overwrite
//    public void drawChat(int updateCounter){
//
//    }

    @Shadow
    public abstract void setChatLine(IChatComponent chatComponent, int chatLineId, int updateCounter, boolean displayOnly);
    @Overwrite
    public void printChatMessageWithOptionalDeletion(IChatComponent chatComponent, int chatLineId)
    {
        setChatLine(chatComponent, chatLineId, Minecraft.getMinecraft().ingameGUI.getUpdateCounter(), false);

    }

    @Inject(method = "setChatLine", at = @At("HEAD"))
    private void setChatLine(IChatComponent chatComponent, int chatLineId, int updateCounter, boolean displayOnly, CallbackInfo callbackInfo){
//        if(chatComponent.getChatStyle().getChatHoverEvent()!=null)
//        System.out.println(chatComponent.getChatStyle().getChatHoverEvent().toString());
        Chat.instance.addMessage(chatComponent);
    }


}
