package com.winterclient.gui.util.resources;

import com.winterclient.gui.util.image.DefaultImage;
import com.winterclient.gui.util.image.SplicedImage;
import com.winterclient.gui.util.image.WinterGuiImage;
import com.winterclient.util.GeneralUtil;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {

    public static DefaultImage background=new DefaultImage(getImage("background.png"));
    public static DefaultImage button=new DefaultImage(getImage("button.png"));
    public static DefaultImage snow = new DefaultImage(getImage("snow.png"));
    public static DefaultImage loadingBar = new DefaultImage(getImage("loading.png"));
    public static DefaultImage selected = new DefaultImage(getImage("selected.png"));
    public static DefaultImage keyStrokes = new DefaultImage(getImage("keystrokes.png"));
    public static DefaultImage drop = new DefaultImage(getImage("drop.png"));

    public static BufferedImage getImage(String name){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage= ImageIO.read(GeneralUtil.getResource("/assets/images/"+name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }

    public static BufferedImage roundImage(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
}
