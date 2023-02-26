package com.winterclient.mixins.implementations;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Scoreboard.class)
public abstract class MixinScoreboard {
    @Shadow public abstract ScorePlayerTeam getTeam(String team);

    @Inject(method = "removeTeam", at = @At("HEAD"), cancellable = true)
    private void removeTeam(ScorePlayerTeam team, CallbackInfo ci) {
        if (team == null) ci.cancel();
    }

    @Inject(method = "removeObjective", at = @At("HEAD"), cancellable = true)
    private void removeObjective(ScoreObjective objective, CallbackInfo ci) {
        if (objective == null) ci.cancel();
    }

}
