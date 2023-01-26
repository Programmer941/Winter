package com.winterclient.gui.util.font;

import com.winterclient.gui.util.image.DefaultImage;
import com.winterclient.util.GeneralUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WinterGuiFont {

    private Font font;
    private DefaultImage fontImage;
    private CharData[] charDatas = new CharData[256];
    private int margin;

    public int FONT_HEIGHT;

    public WinterGuiFont(String location, int size){
        getFont(location,size);
        createTextureAtlas();
    }

    private void getFont(String location, float size){
        try {
            Font f = Font.createFont(Font.PLAIN, GeneralUtil.getResource(location));
            this.font = f.deriveFont(size);
            this.margin=10;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createTextureAtlas(){
        int width=0,height=0;
        FontRenderContext frc = new FontRenderContext(null, true, true);
        for (int i = 0; i < 256; i++) {
            char c = (char) i;
            Rectangle2D dimensions = font.getStringBounds(String.valueOf(c), frc);
            width+=dimensions.getBounds().width+margin;
            if(dimensions.getBounds().height>height)
                height=dimensions.getBounds().height;
        }
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.setFont(font);
        g.setColor(new Color(255, 255, 255, 0));
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        FontMetrics fontMetrics = g.getFontMetrics();
        FONT_HEIGHT = fontMetrics.getHeight();
        int x=0,y=0;
        for (int i = 0; i < 256; i++) {
            char c = (char) i;
            Rectangle2D dimensions = fontMetrics.getStringBounds(String.valueOf(c), g);
            CharData charData = new CharData();
            charData.width=dimensions.getBounds().width;
            charData.height=dimensions.getBounds().height;
            charData.x=x;
            this.charDatas[i]=charData;

            x+=charData.width+margin;
            g.drawString(String.valueOf(c), charData.x, 0 + fontMetrics.getAscent());
        }
        int[] pixels = new int[bufferedImage.getWidth() * bufferedImage.getHeight()];
        bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), pixels, 0, bufferedImage.getWidth());
        fontImage = new DefaultImage(bufferedImage);
        try {
            //ImageIO.write(bufferedImage, "png", new File("textureAtlas.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void drawChar(char c,int x,int y,Color color){
        CharData ch = this.charDatas[c];
        fontImage.draw(x,y,ch.width,ch.height,ch.x,0,ch.width,ch.height,color);
    }

    private void drawChar(char c,int x,int y){
        drawChar(c,x,y,Color.white);
    }

    public void drawStringWithMaxLength(String text,int x,int y,int maxSize, Color c){
        int size = text.length();
        int stringWidth=0;
        int xPos=0,yPos=0;
        int elipseSize = charDatas['.'].width;
        for (int i = 0; i < size; i++) {

            char character = text.charAt(i);
            stringWidth+=this.charDatas[character].width;
            if(stringWidth<maxSize-elipseSize*3){
                drawChar(character,x+xPos,y+yPos,c);
                xPos+=this.charDatas[character].width;
            }else{
                drawChar('.',x+xPos,y+yPos,c);
                xPos+=elipseSize;
                drawChar('.',x+xPos,y+yPos,c);
                xPos+=elipseSize;
                drawChar('.',x+xPos,y+yPos,c);
                return;
            }

        }    }


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

    public void drawCenteredString(String text,int x,int y,Color color){
        int width = getStringWidth(text);
        drawString(text,x-width/2,y,color);
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

    public int getStringHeight(){
        return FONT_HEIGHT;
    }

    private class CharData{
        public int x,width,height;
    }
}
