package com.winterclient.gui.util.image;

import java.awt.image.BufferedImage;

public class SplicedImage extends DefaultImage{
    int splice;
    public SplicedImage(BufferedImage image, int splice) {
        super(image);
        this.splice=splice;
    }

    public void draw(float x, float y, float width, float height) {
        //top left corner
        super.draw(x,y,splice,splice,0,0,splice,splice);
        //top right corner
        super.draw(x+width-splice,y,splice,splice,imageWidth-splice,0,splice,splice);
        //
        super.draw(x+width-splice,y+height-splice,splice,splice,imageWidth-splice,imageHeight-splice,splice,splice);
        super.draw(x,y+height-splice,splice,splice,0,imageHeight-splice,splice,splice);

        super.draw(x+splice,y,width-splice-splice,splice,splice,0,imageWidth-splice-splice,splice);
        super.draw(x+splice,y+height-splice,width-splice-splice,splice,splice,imageHeight-splice,imageWidth-splice-splice,splice);

        super.draw(x,y+splice,splice,height-splice-splice,0,splice,splice,imageHeight-splice-splice);
        super.draw(x+width-splice,y+splice,splice,height-splice-splice,imageWidth-splice,splice,splice,imageHeight-splice-splice);

        super.draw(x+splice,y+splice,width-splice-splice,height-splice-splice,splice,splice,imageWidth-splice-splice,imageHeight-splice-splice);

    }
}
