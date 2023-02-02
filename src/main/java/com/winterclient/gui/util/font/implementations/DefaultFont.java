package com.winterclient.gui.util.font.implementations;

import com.winterclient.gui.util.font.WinterGuiFont;

import java.awt.*;

public class DefaultFont extends WinterGuiFont {
    public DefaultFont(Font font) {
        super(font);
    }

    private void drawChar(char c,int x,int y,Color color){
        CharData ch = this.charDatas[c];

        fontImage.draw(x,y,ch.width,ch.height,ch.x,0,ch.width,ch.height,color);
    }

    public void drawString(String text,int x,int y,Color color){
        int size = text.length();
        int xPos=0,yPos=0;
        for (int i = 0; i < size; i++) {
            char character = text.charAt(i);
            drawChar(character,x+xPos,y+yPos,color);
            xPos+=this.charDatas[character].width;
        }
    }
    public void drawString(String text,int x,int y){
        drawString(text,x,y,Color.white);
    }


        public void drawCenteredString(String text,int x,int y){
        int width = getStringWidth(text);
        drawString(text,x-width/2,y,Color.white);
    }

    public int getStringWidth(String text){
        int width = 0;
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            width+=this.charDatas[character].width;
        }
        return width;
    }
}
