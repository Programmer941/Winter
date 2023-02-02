package com.winterclient.mod.implementations;

import com.winterclient.event.Subscribe;
import com.winterclient.event.implementations.KeyEvent;
import com.winterclient.event.implementations.TickEvent;
import com.winterclient.mod.Mod;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;
import net.minecraft.potion.Potion;
import org.lwjgl.input.Keyboard;

import static net.minecraft.client.renderer.GlStateManager.disableLighting;

@Info(name = "Toggle Sprint", description = "Toggle Sprint!", category = Category.Mechanical, enabled = true)
public class ToggleSprint extends Mod {

    @Subscribe
    public void onKeyEvent(KeyEvent e) {

    }

    @Subscribe
    public void onUpdate(TickEvent e){
        boolean flag = mc.thePlayer.getFoodStats().getFoodLevel() > 6 || mc.thePlayer.capabilities.allowFlying;
        if(mc.thePlayer.moveForward>0 && !mc.thePlayer.isCollidedHorizontally && !mc.thePlayer.isUsingItem() && !mc.thePlayer.isSneaking() && flag && !mc.thePlayer.isPotionActive(Potion.blindness)){
            mc.thePlayer.setSprinting(true);
        }
    }
}
