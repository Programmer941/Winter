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

    Animation hover;
    Animation selected;
    public AccountButton(Account account, int x, int y) {
        super(x, y, 60, 60);
        this.account = account;
        int widthS = Fonts.raleway.getStringWidth(account.name);
        width+=20+widthS;
        hover=new Animation(0);
        selected=new Animation(0);

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        float value= hover.getValue();
        float selectValue=selected.getValue();
        RenderUtil.drawRect(x,y,width,60,new Color(0,0,selectValue,.35f-value));
        Fonts.raleway.drawString(account.name,x+70,y+height/2-Fonts.raleway.FONT_HEIGHT/2);
        account.avatarImage.draw(x, y, 60, 60);

        if(this.mouseInBounds(mouseX,mouseY)){
            if(hover.end==0){
                hover.goTo(.2f,0.2f);
            }
        }else{
            if(hover.end!=0){
                hover.goTo(0,0.2f);
            }
        }
        if(Winter.instance.accountManager.activeAccount!=null)
            if(Winter.instance.accountManager.activeAccount.uuid.equals(account.uuid)){
            if(selected.end==0){
                selected.goTo(.2f,0.2f);
            }
        }else{
            if(selected.end!=0){
                selected.goTo(0,0.2f);
            }
        }
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

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
