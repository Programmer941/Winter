package com.winterclient.gui.screens;

import com.winterclient.Winter;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.mod.implementations.Chat;
import net.minecraft.util.IChatComponent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ChatEvents extends WinterGuiScreen {

    Chat chat;

    @Override
    public void init() {
        drawBackground = false;
        chat = Chat.instance;
    }

    @Override
    public void end() {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        int bottom = (chat.y + chat.height - 40);
        int offset = bottom - mouseY;
        if (mouseX >= chat.x && mouseX <= chat.x + chat.width) {
            if (mouseY > chat.y && mouseY < chat.y + chat.height) {
                if (offset > 0) {
                    int index = offset / 30;
                    RenderUtil.drawRect(chat.x, bottom - (index + 1) * 30, chat.width, 30, Color.red);
                    if (chat.messages.size() > index) {
                        IChatComponent chatComponent = chat.messages.get(chat.messages.size() - 1 - index).iChatComponent;
                        if (chatComponent.getChatStyle().getChatHoverEvent() != null) {
                            GL11.glPushMatrix();
                            GL11.glTranslatef(mouseX,mouseY,1);
                            GL11.glScalef(2,2,1);
                            GL11.glTranslatef(-mouseX,-mouseY,1);
                            this.handleComponentHover(chatComponent, mouseX, mouseY);
                            GL11.glPopMatrix();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void click(int mouseX, int mouseY, int mouseButton) {
        if (mouseX >= chat.x && mouseX <= chat.x + chat.width) {
            if (mouseY > chat.y && mouseY < chat.y + chat.height) {
                int offset = (chat.y + chat.height - 40) - mouseY;
                if (offset > 0) {
                    int index = offset / 30;
                    if (index > 14) index = 14;
                    System.out.println(index);
                    this.handleComponentClick(chat.messages.get(chat.messages.size() - 1 - index).iChatComponent);
                }
            }
        }
    }

    @Override
    public void release(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void drag(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void type(char typedChar, int keyCode) {

    }
}
