package com.winterclient.mod;

import com.winterclient.Winter;
import com.winterclient.event.Subscribe;
import com.winterclient.gui.elements.settingElements.ColorElement;
import com.winterclient.gui.elements.settingElements.NumberElement;
import com.winterclient.gui.shader.implementations.BlurShader;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.setting.implementations.ColorSetting;
import com.winterclient.setting.implementations.NumberSetting;

import java.awt.*;

public class HUDMod extends Mod{

    public int x,y,width,height;
    public boolean lockedScale;

    public NumberSetting xSetting = new NumberSetting("x",0);
    public NumberSetting ySetting = new NumberSetting("y",0);

    public NumberSetting blurAmount = new NumberSetting("blur",0);
    public NumberSetting colorOpacity = new NumberSetting("opacity",143);
    public ColorSetting colorType = new ColorSetting("color",Color.black);

    public NumberElement blurElement = new NumberElement(blurAmount,0,30,6);
    public NumberElement opacityElement = new NumberElement(colorOpacity,0,255,6);
    public ColorElement colorElement = new ColorElement(colorType);

    public HUDMod(int x,int y,int width,int height,boolean lockedScale){
        super();
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.lockedScale=lockedScale;
    }

    public void updatePosition(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void updateSize(int width,int height){
        this.width=width;
        this.height=height;
    }

    public void drawBackground(){
        if(blurAmount.getValue().intValue()>0)
            Winter.instance.blurShader.renderBlur(x,y,width,height,blurAmount.getValue().floatValue());
        if(colorOpacity.getValue().intValue()>0)
        RenderUtil.drawRect(x,y,width,height, new Color(colorType.getValue().getRed(),colorType.getValue().getGreen(),colorType.getValue().getBlue(),colorOpacity.getValue().intValue()));
    }

}
