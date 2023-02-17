package com.winterclient.gui.util.font.implementations;

import com.winterclient.Winter;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.font.WinterGuiFont;
import com.winterclient.gui.util.image.DefaultImage;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.util.GeneralUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EmojiFont {
    protected ArrayList<Font> fonts;
    protected DefaultImage fontImage;
    public CharData[] charDatas = new CharData[Character.MAX_VALUE];
    public int FONT_HEIGHT;
    public int padding;
    int totalFonts;
    int currentFont = 0;

    public EmojiFont() {
        Font font1 = Fonts.getFont("monorale.ttf").deriveFont(30f);
        Font font2 = Fonts.getFont("regular.ttf").deriveFont(30f);
        Font font3 = Fonts.getFont("symbol.ttf").deriveFont(30f);
        Font font4 = Fonts.getFont("symbol2.ttf").deriveFont(30f);
        Font font5 = Fonts.getFont("math.ttf").deriveFont(30f);
        Font font7 = Fonts.getFont("emoji.ttf").deriveFont(30f);
        Font font6 = Fonts.getFont("music.ttf").deriveFont(30f);
        Font font9 = Fonts.getFont("tamil.ttf").deriveFont(30f);
        Font font10 = Fonts.getFont("thai.ttf").deriveFont(30f);
        Font font11 = Fonts.getFont("arabic.ttf").deriveFont(30f);
        Font font12 = Fonts.getFont("jp.ttf").deriveFont(30f);


        this.fonts = new ArrayList<>();
        fonts.add(font1);
        fonts.add(font2);
        fonts.add(font3);
        fonts.add(font4);
        fonts.add(font5);
        fonts.add(font6);
        fonts.add(font7);
        fonts.add(font10);
        fonts.add(font11);
        fonts.add(font9);
        fonts.add(font12);
        totalFonts = fonts.size();
        currentFont = 0;
        createTextureAtlas();
    }

    private void createTextureAtlas() {
        int width = 0, height = 0, margin = 15;
        FontRenderContext frc = new FontRenderContext(null, true, true);
        width = 1200;
        height = 1200;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.setColor(new Color(255, 255, 255, 0));
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setFont(fonts.get(1));
        FontMetrics fontMetrics = g.getFontMetrics();
        FONT_HEIGHT = fontMetrics.getHeight();
        int x = 50, y = 50;
        int total = 0;
        String valid = "✫━❤✰✳☠☮⚔☯❣✼Ω♤•♧✙❋♡♢✴♔♕♚♛★☆✮✯☾☽☼☻۞۩εїзƸ̵̡Ӝ̨̄➲ƷξЖЗж☏¢☚☛☜☞☟☢☣๑❀✿ψ♆☪♪♩♫♬✄✆✦✧♱♰∞♂♀☿❥❦❧™®©✖✗✘■□▢▲△▼▽◆◇○◎●◯Δ◕◔ʊϟツ回₪¿¡½⅓⅔¼¾⅛⅜⅝⅞℅№⇨❝❞#&℃∃∧∠∨∩⊂⊃∪⊥∀ΞΓɐəɘβɟɥɯɔи๏ɹʁяʌʍλчΣΠ➀➁➂➃➄➅➆➇➈➉{｡^‿()☭℘ℑℜℵηα◠◡╭╮╯╰⊙¤㊣▆▇█▓〓≡╝╚╔╗╬═╓╩┠┨┯┷┏┓┗┛┳﹃﹄┌┐└┘∟「」↑↓→←↘↙┇┅﹉﹊﹍﹎*_-︵∵∴‖︱︳︴﹏﹋﹌►◄▧▨◐◑↔↕▪▫▀▄▌▐░▒▬◊◦▣▤▥▦▩ぃ◘◙◈♭のあ￡✎✟ஐ≈.✲❈➹~【】┱┲✚✪✣✤✥❉❃❂❁✓✔✕㊚㊛:ﾟ‘･▁▂▃▅⊮⊯⊰⊱⊲⊳⊴⊵⊶⊷⊸⊹⊺⊻⊼⊽⊾⊿⋀⋁⋂⋃⋄⋅⋆⋇⋈⋉⋊⋋⋌⋍⋎⋏⋐⋑⋒⋓⋔⋕⋖⋗⋘⋙⋚⋛⋜⋝⋞⋟⋠⋡⋢⋣⋤⋥⋦⋧⋨⋩⋪⋫⋬⋭⋮⋯⋰⋱⋲⋳⋴⋵⋶⋷⋸⋹⋺⋻⋼⋽⋾⋿⌀⌁⌂⌃⌄⌅⌆⌇⌈⌉";
        int lineHeight = 0;
        for (int i = 0; i < Character.MAX_VALUE; i++) {
            char c = (char) i;

            if (x > 1150) {
                x = 0;
                y += lineHeight;
                lineHeight = 0;
            }
            if (valid.indexOf(c) >= 0 && c>256) {
                for (int e = 0; e < fonts.size(); e++) {
                    if (fonts.get(e) != null) {
                        if (fonts.get(e).canDisplay(c)) {
                            g.setFont(fonts.get(e));
                            fontMetrics = g.getFontMetrics();
                            Rectangle2D dimensions = fontMetrics.getStringBounds(String.valueOf(c), g);
                            CharData charData = new CharData();
                            charData.width = dimensions.getBounds().width;
                            charData.height = 37;
                            if (charData.height > lineHeight) {
                                lineHeight = charData.height+1;
                            }
                            charData.x = x;
                            charData.y = y;
                            this.charDatas[i] = charData;

                            x += charData.width;

                            g.drawString(String.valueOf(c), charData.x, charData.y);
                            total += 1;
                            char nu = 32;
                            valid = valid.replace(c, nu);
                            break;
                        }
                    }
                }

            }
        }

        int[] pixels = new int[bufferedImage.getWidth() * bufferedImage.getHeight()];
        bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), pixels, 0, bufferedImage.getWidth());
        fontImage = new DefaultImage(bufferedImage);
        try {
            ImageIO.write(bufferedImage, "png", new File("textureAtlas.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int drawChar(char c, int x, int y, Color color) {
        CharData ch = this.charDatas[c];
        fontImage.draw(x, y, ch.width, ch.height, ch.x, ch.y - 21 - 8, ch.width, ch.height, color);
        return ch.width;
    }

    public int getCharWidth(char c){
        if(charDatas[c]!=null)
            return charDatas[c].width;
        return 0;
    }

    public boolean canDrawChar(char c) {
        if (charDatas[c] != null)
            return true;
        return false;
    }

    public class CharData {
        public int x, y, width, height;
    }
}
