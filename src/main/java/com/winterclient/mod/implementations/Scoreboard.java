package com.winterclient.mod.implementations;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.winterclient.Winter;
import com.winterclient.event.Subscribe;
import com.winterclient.event.implementations.OverlayEvent;
import com.winterclient.gui.screens.Multiplayer;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;
import com.winterclient.mod.HUDMod;
import com.winterclient.mod.Mod;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.util.Collection;
import java.util.List;

@Info(name = "Scoreboard", description = "Better Scoreboard!", category = Category.Visual, enabled = true)
public class Scoreboard extends HUDMod {

    public static Scoreboard instance;

    public ScoreObjective objective;

    public Scoreboard(int x, int y, int width, int height) {
        super(x, y, width, height, true);
        instance=this;
    }

    @Subscribe
    public void onRenderOverlay(OverlayEvent o) {
        int offset=580;
        Winter.instance.blurShader.renderBlur(x,y,width,height,9);

        net.minecraft.scoreboard.Scoreboard scoreboard = objective.getScoreboard();
        Collection<Score> collection = scoreboard.getSortedScores(objective);
        List<Score> list = Lists.newArrayList(Iterables.filter(collection, new Predicate<Score>()
        {
            public boolean apply(Score p_apply_1_)
            {
                return p_apply_1_.getPlayerName() != null && !p_apply_1_.getPlayerName().startsWith("#");
            }
        }));

        if (list.size() > 15)
        {
            collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
        }
        else
        {
            collection = list;
        }

        int i = Fonts.mcFont.getStringSize(objective.getDisplayName());

        for (Score score : collection)
        {
            ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName()) + ": " + EnumChatFormatting.RED + score.getScorePoints();
            i = Math.max(i, Fonts.mcFont.getStringSize(s));
        }

        int i1 = collection.size() * Fonts.mcFont.FONT_HEIGHT;
        int j1 = y;
        int k1 = 3;
        int l1 = x;
        int j = 0;
        int difference = offset-(i1+40);
        offset-=difference/2;
        for (Score score1 : collection)
        {

            ++j;
            ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
            String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName());
            String s2 = EnumChatFormatting.RED + "" + score1.getScorePoints();
            int k = j1 - j * Fonts.mcFont.FONT_HEIGHT;
            int l = x+width;
            //Gui.drawRect(l1, k+offset, l, k + Fonts.mcFont.FONT_HEIGHT+offset, 1342177280);
            Fonts.mcFont.drawString(s1, l1, k+offset, -1,false);
            //Fonts.mcFont.drawString(s2, l - Fonts.mcFont.getStringSize(s2), k, -1,false);

            if (j == collection.size())
            {
                String s3 = objective.getDisplayName();
                if(s3.contains("SKYBLOCK")){
//                    mc.thePlayer.sendChatMessage("/l");
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    this.mc.theWorld.sendQuittingDisconnectingPacket();
//                    this.mc.loadWorld(null);
//                    this.mc.displayGuiScreen(new Multiplayer());
                }else{
                    Fonts.mcFont.drawString(s3, l1 +150 - Fonts.mcFont.getStringSize(s3) / 2, k - Fonts.mcFont.FONT_HEIGHT+offset, -1,false);
                }
                //Gui.drawRect(l1, k - Fonts.mcFont.FONT_HEIGHT - 1+offset, l, k - 1+offset, 1610612736);
                //Gui.drawRect(l1, k - 1+offset, l, k+offset, 1342177280);
            }
        }
    }
}
