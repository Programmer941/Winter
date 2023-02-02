package com.winterclient.gui.util.font.implementations;

import com.winterclient.gui.util.font.WinterGuiFont;

import java.awt.*;

public class MinecraftFont {

    WinterGuiFont regular;
    WinterGuiFont bold;
    WinterGuiFont italic;
    WinterGuiFont boldItalic;

    public MinecraftFont(Font font) {
        regular = new WinterGuiFont(font.deriveFont(Font.PLAIN));
        bold = new WinterGuiFont(font.deriveFont(Font.BOLD));
        italic = new WinterGuiFont(font.deriveFont(Font.ITALIC));
        boldItalic = new WinterGuiFont(font.deriveFont(Font.BOLD+Font.ITALIC));
    }



    private final int[] colorCode = new int[32];
    private final String colorCodeIdentifiers = "0123456789abcdefklmnor";


    private void setupMinecraftColorcodes() {
        for (int index = 0; index < 32; index++) {
            int noClue = (index >> 3 & 0x1) * 85;
            int red = (index >> 2 & 0x1) * 170 + noClue;
            int green = (index >> 1 & 0x1) * 170 + noClue;
            int blue = (index >> 0 & 0x1) * 170 + noClue;

            if (index == 6) {
                red += 85;
            }

            if (index >= 16) {
                red /= 4;
                green /= 4;
                blue /= 4;
            }

            this.colorCode[index] = ((red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF);
        }
    }


}
