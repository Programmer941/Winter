package com.winterclient.gui.util.resources;

import com.winterclient.gui.util.image.DefaultImage;
import com.winterclient.gui.util.image.SplicedImage;
import com.winterclient.gui.util.image.WinterGuiImage;
import com.winterclient.util.GeneralUtil;

public class Images {

    public static DefaultImage circle = new DefaultImage(GeneralUtil.getImage("/assets/circle.png"));
    public static DefaultImage background=new DefaultImage(GeneralUtil.getImage("/assets/background.png"));
    public static DefaultImage button=new DefaultImage(GeneralUtil.getImage("/assets/button.png"));
    public static SplicedImage uibackground=new SplicedImage(GeneralUtil.getImage("/assets/button.png"),13);
    public static DefaultImage add=new DefaultImage(GeneralUtil.getImage("/assets/add.png"));
    public static DefaultImage snow = new DefaultImage(GeneralUtil.getImage("/assets/snow.png"));
    public static DefaultImage loadingBar = new DefaultImage(GeneralUtil.getImage("/assets/loading.png"));
    public static DefaultImage selected = new DefaultImage(GeneralUtil.getImage("/assets/selected.png"));
    public static DefaultImage keyStrokes = new DefaultImage(GeneralUtil.getImage("/assets/keystrokes.png"));



}
