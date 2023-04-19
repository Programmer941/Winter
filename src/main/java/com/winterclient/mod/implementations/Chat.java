package com.winterclient.mod.implementations;

import com.winterclient.event.Subscribe;
import com.winterclient.event.implementations.OverlayEvent;
import com.winterclient.gui.shader.implementations.BlurShader;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.mod.HUDMod;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.IChatComponent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

@Info(name = "Chat", description = "Better Chat", category = Category.Visual, enabled = true)
public class Chat extends HUDMod {

    public static Chat instance;

    public ArrayList<Message> messages;
    BlurShader blurShader;

    public Chat(int x, int y, int width, int height) {
        super(x, y, width, height, false);
        messages=new ArrayList<>();
        blurShader=new BlurShader();
        instance=this;
    }

    public void addMessage(IChatComponent chatComponent){
        ArrayList<String> strings = Fonts.mcFont.wrapString(chatComponent.getFormattedText(),790);

            for(int i=0;i<=strings.size()-1;i++){
                messages.add(new Message(strings.get(i),chatComponent));

            }
            if(messages.size()>500)
                messages.remove(0);
    }

    @Subscribe
    public void onRenderOverlay(OverlayEvent o) {
        blurShader.renderBlur(x,y,width,height,9);
        //RenderUtil.drawRect(x,y,width,height,new Color(0x90000000,true));
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(x, mc.displayHeight-y-height+40, width, height-40);
        int yy=height-40-Fonts.raleway.FONT_HEIGHT;
        int count=0;
        for(int e=messages.size()-1;e>=0;e--){
                if(count<15)
                Fonts.mcFont.drawString(messages.get(e).messageString,x+5,y+yy,Color.white,false);
                yy-=30;
                count+=1;
            //Fonts.mcFont.drawString(messages.get(i),x,y+yy,0xffffffff,false);
            //Fonts.mcFont.drawWrappedString(messages.get(i),x,y+yy,800);
            //yy-=30;
        }
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glPopMatrix();
    }

    public class Message{
        public String messageString;
        public IChatComponent iChatComponent;
        private Message(String s,IChatComponent c){
            this.messageString=s;
            this.iChatComponent=c;
        }
    }
}
