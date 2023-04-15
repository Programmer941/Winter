package com.winterclient.mod.implementations;

import com.winterclient.event.EventBus;
import com.winterclient.event.Subscribe;
import com.winterclient.event.implementations.ClickEvent;
import com.winterclient.event.implementations.KeyEvent;
import com.winterclient.event.implementations.OverlayEvent;
import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;
import com.winterclient.mod.HUDMod;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

@Info(name = "Key Strokes", description = "Keys Clicked!", category = Category.Visual, enabled = true)
public class KeyStrokes extends HUDMod {

    int baseKeySize=48;
    int center = 62;
    int outerShadow=11;
    int innerShadow=3;

    Animation forwardAnimation;
    Animation backwardAnimation;
    Animation leftAnimation;
    Animation rightAnimation;
    ArrayList<Animation> leftClickAnimations;
    ArrayList<Animation> rightClickAnimations;

    public KeyStrokes(int x, int y, int width, int height) {
        super(x, y, width, height, true);
        forwardAnimation=new Animation(0);
        backwardAnimation=new Animation(0);
        leftAnimation=new Animation(0);
        rightAnimation=new Animation(0);
        leftClickAnimations=new ArrayList<>();
        rightClickAnimations=new ArrayList<>();
    }

    @Subscribe
    public void onKeyEvent(KeyEvent e) {
        int forwards = mc.gameSettings.keyBindForward.getKeyCode();
        int backwards = mc.gameSettings.keyBindBack.getKeyCode();
        int left = mc.gameSettings.keyBindLeft.getKeyCode();
        int right = mc.gameSettings.keyBindRight.getKeyCode();

        if(e.pressed){
            if(e.keycode==forwards)
            forwardAnimation.goTo(1,0.2f);
            if(e.keycode==backwards)
                backwardAnimation.goTo(1,0.2f);
            if(e.keycode==left)
                leftAnimation.goTo(1,0.2f);
            if(e.keycode==right)
                rightAnimation.goTo(1,0.2f);
        }else {
            if(e.keycode==forwards)
                forwardAnimation.goTo(0,0.2f);
            if(e.keycode==backwards)
                backwardAnimation.goTo(0,0.2f);
            if(e.keycode==left)
                leftAnimation.goTo(0,0.2f);
            if(e.keycode==right)
                rightAnimation.goTo(0,0.2f);
        }

    }

    @Subscribe
    public void clickEvent(ClickEvent c) {
        if(c.left){
            Animation a = new Animation(0);
            a.goTo(1,0.2f);
            leftClickAnimations.add(a);

        }else{
            Animation a = new Animation(0);
            a.goTo(1,0.2f);
            rightClickAnimations.add(a);
        }
    }

    @Subscribe
    public void onRenderOverlay(OverlayEvent o) {
        Color defaultColor=new Color(0x90000000,true);
        //Images.keyStrokes.draw(x,y,width,height);
        renderPressAnimation(forwardAnimation,x+center,y+outerShadow,baseKeySize,baseKeySize);
        renderPressAnimation(backwardAnimation,x+center,y+outerShadow+baseKeySize+innerShadow,baseKeySize,baseKeySize);
        renderPressAnimation(leftAnimation,x+outerShadow,y+outerShadow+baseKeySize+innerShadow,baseKeySize,baseKeySize);
        renderPressAnimation(rightAnimation,x+width-outerShadow-baseKeySize,y+outerShadow+baseKeySize+innerShadow,baseKeySize,baseKeySize);

        RenderUtil.drawRect(x+62,y+11,48,48,defaultColor);
        RenderUtil.drawRect(x+62,y+62,48,48,defaultColor);
        RenderUtil.drawRect(x+11,y+62,48,48,defaultColor);
        RenderUtil.drawRect(x+113,y+62,48,48,defaultColor);
        RenderUtil.drawRect(x+11,y+113,74,48,defaultColor);
        RenderUtil.drawRect(x+88,y+113,74,48,defaultColor);

        leftClickAnimations.forEach(animation -> {
            renderPressAnimation(animation,x+outerShadow,y+113,74,baseKeySize);

        });

        rightClickAnimations.forEach(animation -> {
            renderPressAnimation(animation,x+88,y+113,73,baseKeySize);

        });
        ArrayList<Animation> remove= new ArrayList<>();
        leftClickAnimations.forEach(animation -> {
            if(animation.finished()){
                remove.add(animation);
            }
        });
        leftClickAnimations.removeAll(remove);

        remove.clear();
        rightClickAnimations.forEach(animation -> {
            if(animation.finished()){
                remove.add(animation);
            }
        });
        rightClickAnimations.removeAll(remove);

    }

    public void renderPressAnimation(Animation a,int xPos,int yPos,int xSize,int ySize){
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(xPos, mc.displayHeight-yPos-48, xSize, ySize);
        RenderUtil.drawCircle(xPos+xSize/2,yPos+ySize/2,48*a.getValue(),new Color(0x50ffffff,true));
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glPopMatrix();
    }

}
