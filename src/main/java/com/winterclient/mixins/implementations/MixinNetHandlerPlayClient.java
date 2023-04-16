package com.winterclient.mixins.implementations;

import com.mojang.authlib.GameProfile;
import com.winterclient.Winter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.DataWatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {

    //patch some vulnerability
    @Inject(method = "handleResourcePack", at = @At("HEAD"), cancellable = true)
    public void handleResourcePack(S48PacketResourcePackSend packetIn, CallbackInfo callbackInfo){
        final String s = packetIn.getURL();
        final String s1 = packetIn.getHash();
        if (s.startsWith("level://")) {
            if(s.contains("..") || !s.endsWith("/resources.zip")){
                Winter.instance.logger.warning("SERVER: "+ Minecraft.getMinecraft().getCurrentServerData().serverName+" IS TRYING TO FIND FILES ON YOUR COMPUTER: "+s);
                if (Minecraft.getMinecraft().thePlayer != null) {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                            EnumChatFormatting.RED + EnumChatFormatting.BOLD.toString() +
                                    "[WARNING] The current server has attempted to be find files on your computer :| good server choices :)"));
                }
                Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C19PacketResourcePackStatus(s1, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
                callbackInfo.cancel();
            }
        }
    }

}
