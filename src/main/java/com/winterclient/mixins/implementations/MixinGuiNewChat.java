package com.winterclient.mixins.implementations;

import com.winterclient.Winter;
import com.winterclient.mod.implementations.Chat;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiNewChat.class)
public class MixinGuiNewChat {


    @Inject(method = "setChatLine", at = @At("TAIL"))
    private void setChatLine(IChatComponent chatComponent, int chatLineId, int updateCounter, boolean displayOnly, CallbackInfo callbackInfo){
        Chat c= (Chat) Winter.instance.modManager.getModule("Chat");
        c.addMessage(chatComponent.getFormattedText());
    }


}
