package com.winterclient.mod;

public class HUDMod extends Mod{

    public int x,y,width,height;
    public boolean lockedScale;

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

}
