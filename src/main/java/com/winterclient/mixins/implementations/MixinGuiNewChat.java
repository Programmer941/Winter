package com.winterclient.mixins.implementations;

import com.winterclient.Winter;
import com.winterclient.mod.implementations.Chat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
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

import java.util.List;

@Mixin(GuiNewChat.class)
public abstract class MixinGuiNewChat {

//    @Overwrite
//    public void drawChat(int updateCounter){
//
//    }

    @Shadow
    List<ChatLine> drawnChatLines;

    @Shadow
    public abstract void setChatLine(IChatComponent chatComponent, int chatLineId, int updateCounter, boolean displayOnly);
    @Overwrite
    public void printChatMessageWithOptionalDeletion(IChatComponent chatComponent, int chatLineId)
    {
        System.out.println(chatComponent.getChatStyle());
        setChatLine(chatComponent, chatLineId, Minecraft.getMinecraft().ingameGUI.getUpdateCounter(), false);
    }

    @Inject(method = "setChatLine", at = @At("HEAD"))
    private void setChatLine(IChatComponent chatComponent, int chatLineId, int updateCounter, boolean displayOnly, CallbackInfo callbackInfo){
//        if(chatComponent.getChatStyle().getChatHoverEvent()!=null)
//        System.out.println(chatComponent.getChatStyle().getChatHoverEvent().toString());
        Chat.instance.addMessage(chatComponent);

    }

    @Inject(method = "setChatLine", at = @At("TAIL"))
    private void setChatLineEnd(IChatComponent chatComponent, int chatLineId, int updateCounter, boolean displayOnly, CallbackInfo callbackInfo){
        int index = Math.max(0,drawnChatLines.size()-5);
        System.out.println(drawnChatLines.get(index).getChatComponent());
    }


}
