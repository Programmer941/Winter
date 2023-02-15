package com.winterclient.gui.elements;

import com.winterclient.Winter;
import com.winterclient.account.Account;
import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Images;

import java.awt.*;

public class AccountButton extends WinterGuiElement {

    public Account account;

    public Animation underlineY;
    public Animation overlineY;

    public AccountButton(Account account, int x, int y) {
        super(x, y, 60, 60);
        this.account = account;
        this.underlineY = new Animation(0);
        this.overlineY=new Animation(0);
    }

    @Override
    public void draw(int mouseX, int mouseY) {

        if (mouseInBounds(mouseX, mouseY)) {
            if (underlineY.end != 20)
                underlineY.goTo(20, 0.2f);
        } else {
            if (underlineY.end != 0)
                underlineY.goTo(0, 0.2f);
        }
        RenderUtil.drawLine(x + 12, y + height + underlineY.getValue() - 20, x - 12 + width, y + height + underlineY.getValue() - 20, 2, Color.white);
        if(Winter.instance.accountManager.activeAccount!=null)
        if(Winter.instance.accountManager.activeAccount.uuid.equals(account.uuid)){
            if (overlineY.end != 20)
                overlineY.goTo(20, 0.2f);
        }else{
            if (overlineY.end != 0)
                overlineY.goTo(0, 0.2f);
        }
        RenderUtil.drawLine(x + 12, y - overlineY.getValue() +20, x - 12 + width, y - overlineY.getValue() + 20, 2, Color.white);

        Images.button.draw(x, y, width, height);
        account.avatarImage.draw(x + 10, y + 10, 40, 40);
    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {
        Winter.instance.accountManager.activeAccount = account;
        System.out.println("set active session to: " + account.name);
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
        return true;
    }
}
