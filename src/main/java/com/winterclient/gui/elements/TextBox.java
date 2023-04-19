package com.winterclient.gui.elements;

import com.winterclient.gui.animation.Animation;
import com.winterclient.gui.core.WinterGuiElement;
import com.winterclient.gui.util.RenderUtil;
import com.winterclient.gui.util.resources.Fonts;
import com.winterclient.gui.util.resources.Images;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class TextBox extends WinterGuiElement {

    public boolean selected=false;
    private String placeHolder;
    public String text = "";
    private int textSize=0;
    private int cursorPosition;
    private int selectionPosition;
    int cursorShow=0;

    Animation hover;
    Animation fade;


    public TextBox(String placeHolder, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.placeHolder = placeHolder;
        hover=new Animation(0);
        fade=new Animation(0);
        start();
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        float value= hover.getValue();
        float fadeValue = fade.getValue();
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(x, Display.getHeight() -y-height, width, height);
        RenderUtil.drawRoundRect(x,y,width,height,new Color(0,0,0,(.35f-value)*fadeValue),30);
        if(text.equals("")){
            Fonts.raleway.drawString(placeHolder,x+10,y+height/2-Fonts.raleway.FONT_HEIGHT/2,new Color(0.5f, 0.5f, 0.5f, fadeValue));
        }
        int negativeOffset=Math.max(textSize-(width-15),0);
        Fonts.raleway.drawString(text, x+10-negativeOffset, y+height/2-Fonts.raleway.FONT_HEIGHT/2, new Color(1f, 1f, 1f, fadeValue));
        drawSelection(fadeValue);
        drawCursor(fadeValue);

        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glPopMatrix();
        if(this.mouseInBounds(mouseX,mouseY)){
            if(hover.end==0){
                hover.goTo(.2f,0.2f);
            }
        }else{
            if(hover.end!=0){
                hover.goTo(0,0.2f);
            }
        }
    }

    public void drawSelection(float fade) {
        if(selected)
        if(textSelected()) {
            int startPosition = this.x+10+ Fonts.raleway.getStringWidth(text.substring(0, this.selectionPosition1()));
            int width = Fonts.raleway.getStringWidth(text.substring(this.selectionPosition1(), this.selectionPosition2()));
            int offset=0;
            if(width>60 && startPosition==this.x+10){
                offset=10;
            }
            int offset2=0;
            if(width>this.width){
                width=this.width;
                if(offset==10)
                offset2=10;
            }
            RenderUtil.drawRoundRect(startPosition-offset, y, width+offset-offset2, height, new Color(1,1,1,.35f*fade),60);
        }
    }

    public void drawCursor(float fade) {
        if(selected) {
            int startPosition = this.x + 10 + Fonts.raleway.getStringWidth(text.substring(0, this.cursorPosition));
            startPosition = Math.min(this.x + this.width - 10, startPosition);
            if (cursorShow > 8)
                RenderUtil.drawRect(startPosition, y + height / 4, 2, height / 2, new Color(1,1,1,fade));
            if (cursorShow > 16)
                cursorShow = 0;
        }
    }

    public void writeText(String input)
    {
        input = ChatAllowedCharacters.filterAllowedCharacters(input);
        if(input=="") return;
        if(textSelected()) {
            if(!input.isEmpty())
            replaceText(input);
            return;
        }
        addText(input);
        textSize=Fonts.raleway.getStringWidth(text);
    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {
        int closest=100000;
        int closestChar=0;
        for(int i=0;i<=text.length();i++) {
            int distance=Math.abs(Fonts.raleway.getStringWidth(text.substring(0, i))+this.x-x);
            if(distance<closest) {
                closestChar=i;
                closest=distance;
            }
        }
        this.setCursorPosition(closestChar);
    }

    @Override
    public void onRelease(int mouseX, int mouseY) {
    }

    @Override
    public void onType(char key, int keyCode) {
        if(cursorPosition>text.length()) cursorPosition = text.length();
        if(selectionPosition>text.length()) selectionPosition = text.length();
        if(cursorPosition<0) cursorPosition = 0;
        if(selectionPosition<0) selectionPosition = 0;

        if(GuiScreen.isKeyComboCtrlA(keyCode)) {
            cursorPosition=text.length();
            this.selectionPosition=0;
            return;
        }
        if(GuiScreen.isKeyComboCtrlC(keyCode)) {
            GuiScreen.setClipboardString(this.getSelectedText());
            return;
        }
        if(GuiScreen.isKeyComboCtrlV(keyCode)) {
            this.writeText(GuiScreen.getClipboardString());
            return;
        }
        if(GuiScreen.isKeyComboCtrlX(keyCode)) {
            GuiScreen.setClipboardString(this.getSelectedText());
            this.deleteSelected();
            return;
        }

        switch(keyCode) {
            case Keyboard.KEY_BACK:
                if(textSelected()) {
                    deleteSelected();
                    return;
                }
                if (GuiScreen.isCtrlKeyDown()){
                    deleteWord();
                    return;
                }
                deleteCharacter();
                return;
            case Keyboard.KEY_LEFT:
                if (GuiScreen.isShiftKeyDown())
                {
                    if (GuiScreen.isCtrlKeyDown())
                    {
                        selectWord(-1);
                        return;
                    }
                    moveSelectionPosition(-1);
                    return;
                }
                if (GuiScreen.isCtrlKeyDown())
                {
                    moveCursorByWord(-1);
                    return;
                }
                addCursorPosition(-1);
                return;
            case Keyboard.KEY_RIGHT:
                if (GuiScreen.isShiftKeyDown())
                {
                    if (GuiScreen.isCtrlKeyDown())
                    {
                        selectWord(1);
                        return;
                    }
                    moveSelectionPosition(1);
                    return;
                }
                if (GuiScreen.isCtrlKeyDown())
                {
                    moveCursorByWord(1);
                    return;
                }
                addCursorPosition(1);
                return;
        }
        writeText(Character.toString(key));
    }

    @Override
    public void onUpdate() {
        cursorShow+=1;
    }

    @Override
    public boolean isCollided(int mouseX, int mouseY) {
        return true;
    }

    @Override
    public void start() {
        fade.goTo(1,0.4f);
    }

    @Override
    public void stop() {
        fade.goTo(0,0.4f);
    }

    public void moveCursorByWord(int direction) {
        if(direction<0) {
            int position = text.lastIndexOf(" ", cursorPosition-1);
            if(position<0) position = 0;
            setCursorPosition(position);
        }else {
            int position = text.indexOf(" ", cursorPosition+1);
            if(position>text.length() || position<0) position = text.length();
            setCursorPosition(position);
        }
    }

    public void moveSelectionPosition(int amount) {
        selectionPosition+=amount;
        if(selectionPosition<0) selectionPosition = 0;
        if(selectionPosition>text.length()) selectionPosition = text.length();

    }

    public void selectWord(int direction) {
        StringBuilder text = new StringBuilder(this.text);
        if(direction<0) {
            selectionPosition = text.lastIndexOf(" ", selectionPosition-1);
            if(selectionPosition<0) selectionPosition = 0;
        }else {
            selectionPosition = text.indexOf(" ", selectionPosition+1);
            if(selectionPosition>text.length() || selectionPosition<0) selectionPosition = text.length();
        }
    }

    public void addCursorPosition(int amount) {
        this.cursorPosition+=amount;
        if(cursorPosition<0) cursorPosition = 0;
        if(cursorPosition>text.length()) cursorPosition = text.length();
        this.selectionPosition=this.cursorPosition;
    }

    public void setCursorPosition(int position) {
        this.cursorPosition=position;
        this.selectionPosition=position;
    }

    public void addText(String input) {
        StringBuilder text = new StringBuilder(this.text);
        text.insert(cursorPosition, input);
        this.text=text.toString();
        addCursorPosition(input.length());
    }

    public void replaceText(String input) {
        StringBuilder text = new StringBuilder(this.text);
        text.replace(selectionPosition1(), selectionPosition2(), input);
        this.text=text.toString();
        addCursorPosition(-1*(selectionPosition2()-selectionPosition1())+input.length());
    }

    public void deleteSelected() {
        if(!textSelected()) return;


        StringBuilder text = new StringBuilder(this.text);
        text.delete(selectionPosition1(), selectionPosition2());
        this.text=text.toString();
        if(selectionPosition<cursorPosition) {
            addCursorPosition(-1*(selectionPosition2()-selectionPosition1()));
        }
        this.setCursorPosition(cursorPosition);
    }

    public void deleteWord() {
        String[] words = text.split(" ");
        StringBuilder text = new StringBuilder(this.text);
        int start = text.lastIndexOf(" ", cursorPosition);
        if(start<0) start =0;
        text.delete(start, cursorPosition);
        this.text=text.toString();
        addCursorPosition(cursorPosition-start);
    }

    public void deleteCharacter() {
        if(cursorPosition>0) {
            StringBuilder text = new StringBuilder(this.text);
            text.deleteCharAt(cursorPosition-1);
            this.text=text.toString();
            addCursorPosition(-1);
        }
    }

    public String getSelectedText()
    {
        int first = this.cursorPosition < this.selectionPosition ? this.cursorPosition : this.selectionPosition;
        int second = this.cursorPosition < this.selectionPosition ? this.selectionPosition : this.cursorPosition;
        return this.text.substring(first, second);
    }

    public int selectionPosition1() {
        int first = this.cursorPosition < this.selectionPosition ? this.cursorPosition : this.selectionPosition;
        return first;
    }
    public int selectionPosition2() {
        int second = this.cursorPosition < this.selectionPosition ? this.selectionPosition : this.cursorPosition;
        return second;
    }

    public boolean textSelected() {
        if(this.cursorPosition == this.selectionPosition) {
            return false;
        }
        return true;
    }
}
