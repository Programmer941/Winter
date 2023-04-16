package com.winterclient.gui;

import com.winterclient.gui.util.resources.Images;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class Background {
    private List<Snow> snowList;
    int width,height;


    public Background(int width,int height) {
        this.width=width;
        this.height=height;
        int snowAmount=width/5;
        snowList=new ArrayList<Snow>();
        for (int i = 0; i < snowAmount; i++) {
            snowList.add(new Snow((float) Math.random() * width, (float) Math.random() * height, (float) random(-1, 1), (float) random(1, 2), (float) random(1, 3) * 2));
        }
    }

    private double random(int min, int max) {
        return min + Math.random() * (max - min + 1);
    }

    public void draw() {
        Images.background.draw(0, 0, width, height);
    }

    public void drawSnow(){
        snowList.forEach(snow -> {
            snow.move(width,height);
            snow.draw();
        });
    }

    public void onUpdate() {

    }

    private class Snow {
        private Snow(float x,float y,float speedX,float speedY,float diameter){
            this.x=x;
            this.y=y;
            this.speedX=speedX;
            this.speedY=speedY;
            this.diameter=diameter;
        }
        float x,y,speedX,speedY,diameter;

        public void move(int width,int height){
            this.x += this.speedX / 10f;
            this.y += this.speedY / 10f;

            if (this.y > height) {
                this.x = (float) (Math.random() * width);
                this.y = -40;
            }
        }

        public void draw(){
            Images.snow.draw(this.x, this.y, this.diameter, this.diameter);
        }
    }



}