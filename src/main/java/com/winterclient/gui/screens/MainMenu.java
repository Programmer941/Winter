package com.winterclient.gui.screens;

import com.winterclient.Winter;
import com.winterclient.gui.Background;
import com.winterclient.gui.core.WinterGuiScreen;
import com.winterclient.gui.elements.AccountButton;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Images;

public class MainMenu extends WinterGuiScreen {

    public void initGui() {
        guiElements.clear();
        addElement(new Background(width,height));

        int x=10;
        for(int i=0;i<Winter.instance.accountManager.accountList.size();i++){
            addElement(new AccountButton(Winter.instance.accountManager.accountList.get(i),x,10));
            x+=70;
        }

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX,mouseY,partialTicks);

        Images.button.draw(width/2-30,height-80,60,60);
        Images.button.draw(width-70,10,60,60);
        Images.add.draw(width-55,25,30,30);

    }

    public int formulaY(int x){
        float s = (width/2-(height/-3.73205080757f)/2f);
        double y = -3.73205080757f*(x-s);
        return (int) y;
    }
}