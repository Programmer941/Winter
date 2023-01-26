package com.winterclient.account;

import com.winterclient.gui.util.image.DefaultImage;
import com.winterclient.gui.util.image.RoundedImage;
import com.winterclient.gui.util.image.WinterGuiImage;
import net.minecraft.util.Session;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Account {

    public String name,uuid,accessToken,refreshToken;
    public Long expirationTime;
    public RoundedImage avatarImage;

    public Account(String name,String uuid,String accessToken,String refreshToken,Long expirationTime){
        this.name=name;
        this.uuid=uuid;
        this.accessToken=accessToken;
        this.refreshToken=refreshToken;
        this.expirationTime=expirationTime;
    }

    public void createAvatar(){
        String prefix="https://crafatar.com/avatars/";
        String suffix="?size=40&overlay";
        try {
            BufferedImage img = ImageIO.read(new URL(prefix+uuid+suffix));
            avatarImage=new RoundedImage(img,7);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Session getSession(){
        return new Session(name,uuid,accessToken,"Xbox");
    }
}
