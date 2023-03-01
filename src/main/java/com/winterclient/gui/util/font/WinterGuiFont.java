package com.winterclient.gui.util.font;

import com.winterclient.Winter;
import com.winterclient.gui.util.image.DefaultImage;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.util.GeneralUtil;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class WinterGuiFont {
    protected Font font;
    protected DefaultImage fontImage;
    protected CharData[] charDatas = new CharData[256];
    public int FONT_HEIGHT;

    public WinterGuiFont(Font font){
        this.font=font;
        createTextureAtlas();
    }

    private void createTextureAtlas(){
        int width=0,height=0,margin=10;
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
        g.setColor(new Color(71, 71, 71, 0));
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

    }

    public class CharData{
        public int x,width,height;
    }
}
