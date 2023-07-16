package com.winterclient.gui.util.resources;

import com.winterclient.gui.util.font.implementations.DefaultFont;
import com.winterclient.gui.util.font.implementations.EmojiFont;
import com.winterclient.gui.util.font.implementations.MinecraftFont;
import com.winterclient.util.GeneralUtil;

import java.awt.*;
import java.io.IOException;

public class Fonts {

    public static DefaultFont raleway = new DefaultFont(getFont("raleway.ttf").deriveFont(30f));
    public static DefaultFont ralewaySmall = new DefaultFont(getFont("raleway.ttf").deriveFont(24f));
    public static DefaultFont ralewaySmallest = new DefaultFont(getFont("raleway.ttf").deriveFont(16f));
    public static MinecraftFont mcFont = new MinecraftFont(getFont("raleway.ttf").deriveFont(24f));
    public static Font getFont(String name) {
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, GeneralUtil.getResource("/assets/fonts/" + name));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return font;
    }
}
