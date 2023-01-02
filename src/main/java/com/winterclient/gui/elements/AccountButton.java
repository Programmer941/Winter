package com.winterclient.gui.elements;

import com.winterclient.Winter;
import com.winterclient.account.Account;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.image.WinterGuiImage;
import com.winterclient.gui.util.resources.Images;

public class AccountButton extends WinterGuiElement {

    public Account account;

    public AccountButton(Account account,int x, int y) {
        super(x, y, 60,60);
        this.account=account;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Images.button.draw(x,y,width,height);
        account.avatarImage.draw(x+10,y+10,40,40);
        if(isCollided(mouseX,mouseY))
            RenderUtil.drawLine(x+5,y+height+5,x-5+width,y+height+5,0xffffffff,2);
    }

    @Override
    public void onClick(int mouseX, int mouseY) {
        Winter.instance.accountManager.activeAccount=account;
        System.out.println("set active session to: "+ account.name);
    }

    @Override
    public void onRelease(int mouseX, int mouseY) {

    }

    @Override
    public void onType(char key, int keyCode) {

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public boolean isCollided(int mouseX, int mouseY) {
        return mouseX>x && mouseX<x+width && mouseY>y && mouseY<y+height;
    }
}
