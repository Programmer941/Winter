package com.winterclient.gui.elements;

import com.winterclient.Winter;
import com.winterclient.account.Account;
import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;

import java.awt.*;

public class AccountButton extends WinterGuiElement {

    public Account account;

    public AccountButton(Account account, int x, int y) {
        super(x, y, 60, 60);
        this.account = account;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        int widthS = Fonts.raleway.getStringWidth(account.name);
        RenderUtil.drawRect(x,y,80+widthS,60,new Color(0x59000000, true));
        Fonts.raleway.drawString(account.name,x+70,y+height/2-Fonts.raleway.FONT_HEIGHT/2);
        account.avatarImage.draw(x, y, 60, 60);
    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {
        Winter.instance.accountManager.setActiveAccount(account);
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
