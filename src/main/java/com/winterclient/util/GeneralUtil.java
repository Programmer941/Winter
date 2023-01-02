package com.winterclient.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GeneralUtil {

    public static InputStream getResource(String location) {
        return GeneralUtil.class.getResourceAsStream(location);
    }

    public static BufferedImage getImage(String location) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(getResource(location));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
