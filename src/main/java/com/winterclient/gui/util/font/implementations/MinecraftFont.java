package com.winterclient.gui.util.font.implementations;

import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.font.WinterGuiFont;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class MinecraftFont {

    DefaultFont regularFont;
    DefaultFont boldFont;
    DefaultFont italicFont;
    DefaultFont boldItalicFont;
    EmojiFont emojiFont;

    DefaultFont activeFont;

    public MinecraftFont(Font font) {
        regularFont = new DefaultFont(font.deriveFont(Font.PLAIN));
        boldFont = new DefaultFont(font.deriveFont(Font.BOLD));
        italicFont = new DefaultFont(font.deriveFont(Font.ITALIC));
        boldItalicFont = new DefaultFont(font.deriveFont(Font.BOLD + Font.ITALIC));
        emojiFont = new EmojiFont();
        activeFont = regularFont;
        setupMinecraftColorCodes();
    }


    private final Color[] colorCode = new Color[32];
    private final String colorCodeIdentifiers = "0123456789abcdefklmnor";


    private void setupMinecraftColorCodes() {
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

            this.colorCode[index] = new Color(red, green, blue);
            System.out.println(colorCode[index]);
        }
    }

    public void drawString(String text, int x, int y, int color, boolean shadow) {
        boolean randomCase = false;
        boolean bold = false;
        boolean italic = false;
        boolean strikethrough = false;
        boolean underline = false;

        Color currentColor = Color.white;

        int size = text.length();
        for (int i = 0; i < size; i++) {
            char character = text.charAt(i);
            if ((character == '§') && (i < size)) {
                int colorIndex = 21;

                try {
                    colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(i + 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (colorIndex < 16) {
                    bold = false;
                    italic = false;
                    randomCase = false;
                    underline = false;
                    strikethrough = false;

                    if (colorIndex < 0 || colorIndex > 15)
                        colorIndex = 15;

                    if (shadow)
                        colorIndex += 16;


                    currentColor = colorCode[colorIndex];

                } else if (colorIndex == 16) {
                    randomCase = true;
                } else if (colorIndex == 17) {
                    bold = true;

                    if (italic) {
                        activeFont = boldItalicFont;
                    } else {
                        activeFont = boldFont;
                    }
                } else if (colorIndex == 18) {
                    strikethrough = true;
                } else if (colorIndex == 19) {
                    underline = true;
                } else if (colorIndex == 20) {
                    italic = true;

                    if (bold) {
                        activeFont = boldItalicFont;

                    } else {
                        activeFont = italicFont;

                    }
                } else if (colorIndex == 21) {
                    bold = false;
                    italic = false;
                    randomCase = false;
                    underline = false;
                    strikethrough = false;
                    activeFont = regularFont;
                    currentColor = Color.white;

                }
                i++;
            } else {
                if (character <= 256) {
                    int width = activeFont.drawChar(character, x, y, currentColor);
                    x += width;
                    if (strikethrough)
                        RenderUtil.drawLine(x, y + activeFont.FONT_HEIGHT / 2f, x - width, y + activeFont.FONT_HEIGHT / 2f, 2, currentColor);
                    if (underline)
                        RenderUtil.drawLine(x, y + activeFont.FONT_HEIGHT, x - width, y + activeFont.FONT_HEIGHT, 2, currentColor);
                } else if (emojiFont.canDrawChar(character)) {
                    int width = emojiFont.drawChar(character, x, y, currentColor);
                    x += width;
                    if (strikethrough)
                        RenderUtil.drawLine(x, y + emojiFont.FONT_HEIGHT / 2f, x - width, y + emojiFont.FONT_HEIGHT / 2f, 2, currentColor);
                    if (underline)
                        RenderUtil.drawLine(x, y + emojiFont.FONT_HEIGHT, x - width, y + emojiFont.FONT_HEIGHT, 2, currentColor);
                } else {
                    int scale = 3;
                    StringBuilder formattedString = new StringBuilder();
                    if (randomCase)
                        formattedString.append("§k");
                    if (bold)
                        formattedString.append("§l");
                    if (italic)
                        formattedString.append("§o");
                    formattedString.append(character);
                    System.out.println(character);
                    GL11.glPushMatrix();
                    GL11.glTranslatef(x, y + 6, 1);
                    GL11.glScalef(scale, scale, 1);
                    GL11.glTranslatef(-x, -y - 6, 1);

                    Minecraft.getMinecraft().fontRendererObj.drawString(formattedString.toString(), x, y + 6, currentColor.getRGB(), true);
                    GL11.glPopMatrix();
                    int width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(formattedString.toString());
                    x += width * 3;
                    if (strikethrough)
                        RenderUtil.drawLine(x, y + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * scale / 2f, x - width, y + emojiFont.FONT_HEIGHT * scale / 2f, 2, currentColor);
                    if (underline)
                        RenderUtil.drawLine(x, y + emojiFont.FONT_HEIGHT * scale, x - width, y + emojiFont.FONT_HEIGHT * scale, 2, currentColor);
                }
            }
        }
    }

    public int getStringSize(String s) {
        int totalSize=0;
        boolean bold = false;
        boolean italic = false;
        int size = s.length();
        for (int i = 0; i < size; i++) {
            char character = s.charAt(i);
            if ((character == '§') && (i < size)) {
                int colorIndex = 21;

                try {
                    colorIndex = "0123456789abcdefklmnor".indexOf(s.charAt(i + 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (colorIndex < 16) {
                    bold = false;
                    italic = false;

                    if (colorIndex < 0 || colorIndex > 15)
                        colorIndex = 15;


                }else if (colorIndex == 17) {
                    bold = true;

                }else if (colorIndex == 20) {
                    italic = true;
                } else if (colorIndex == 21) {
                    bold = false;
                    italic = false;
                }
                i++;
            } else {
                if (character <= 256) {
                    int width = activeFont.getStringWidth(String.valueOf(character));
                    totalSize+=width;
                } else if (emojiFont.canDrawChar(character)) {
                    int width = emojiFont.getCharWidth(character);
                    totalSize+=width;
                } else {
                    int scale = 3;
                    StringBuilder formattedString = new StringBuilder();
                    if (bold)
                        formattedString.append("§l");
                    if (italic)
                        formattedString.append("§o");
                    formattedString.append(character);

                    int width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(formattedString.toString());
                    totalSize += width * 3;

                }
            }
        }
        return totalSize;
    }

    public void drawWrappedString(){

    }
}
