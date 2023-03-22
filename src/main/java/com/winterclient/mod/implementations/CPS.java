package com.winterclient.mod.implementations;

import com.winterclient.Winter;
import com.winterclient.event.Subscribe;
import com.winterclient.event.implementations.ClickEvent;
import com.winterclient.event.implementations.OverlayEvent;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.mod.HUDMod;
import com.winterclient.mod.properties.Category;
import com.winterclient.mod.properties.Info;
import com.winterclient.setting.implementations.BooleanSetting;

import java.awt.*;
import java.util.ArrayList;

@Info(name = "CPS", description = "Clicks Per Second!", category = Category.Visual, enabled = true)
public class CPS extends HUDMod {
    public ArrayList<Long> leftCps;
    public ArrayList<Long> rightCps;

    public int savedMilliseconds;

    BooleanSetting title = new BooleanSetting("Has Title",false);

    public CPS(int x,int y,int width,int height){
        super(x,y,width,height,true);
        leftCps=new ArrayList<>();
        rightCps=new ArrayList<>();
        savedMilliseconds=0;

       //addSetting(title);
        addSetting(blurAmount);
        addSetting(colorOpacity);
        addSettingElement(blurElement);
        addSettingElement(opacityElement);
    }

    @Subscribe
    public void clickEvent(ClickEvent c) {
        long milliseconds = System.currentTimeMillis();
        ArrayList<Long> removal = new ArrayList();

        if(c.left) {
            leftCps.add(milliseconds);
        }else {
            rightCps.add(milliseconds);
        }
    }

    @Subscribe
    public void onRenderOverlay(OverlayEvent o) {
        super.drawBackground();
        long milliseconds = System.currentTimeMillis();

        ArrayList<Long> removal = new ArrayList();
        leftCps.forEach(cps -> {
            if (cps < milliseconds - 1000)
                removal.add(cps);
        });
        leftCps.removeAll(removal);

        removal.clear();

        rightCps.forEach(cps -> {
            if (cps < milliseconds - 1000)
                removal.add(cps);
        });
        rightCps.removeAll(removal);

        if(title.getValue()){
            Fonts.raleway.drawCenteredString("CPS", x+width/2, y+4);
            RenderUtil.drawLine(x+width/2,y+40,x+width/2,y+63,2,Color.white);
            Fonts.raleway.drawCenteredString(leftCps.size()+"", x+width/4, y+34);
            Fonts.raleway.drawCenteredString(rightCps.size()+"", x+3*width/4, y+34);
        }else{
            Fonts.raleway.drawCenteredString(leftCps.size()+" | "+rightCps.size(), x+width/2, y+height/2-Fonts.raleway.FONT_HEIGHT/2);
        }
    }

}
