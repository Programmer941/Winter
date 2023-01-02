package com.winterclient;

import com.winterclient.auth.AccountAuthenticator;
import com.winterclient.data.AccountManager;
import net.minecraft.util.Session;
import org.lwjgl.opengl.Display;

public enum Winter {

    instance;

    public AccountManager accountManager = new AccountManager();

    public final void init() {
        Display.setTitle("Winter Client");
    }

    public final void end() {
        System.out.println("Saving all data!");
        Winter.instance.accountManager.saveAccounts();
    }


}
