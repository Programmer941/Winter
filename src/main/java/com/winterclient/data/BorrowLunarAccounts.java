package com.winterclient.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.winterclient.Winter;
import com.winterclient.account.Account;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

public class BorrowLunarAccounts {

    public static void readAccounts() {
        if(Winter.instance.accountManager.accountList.size()>0)
            return;

        String home = System.getProperty("user.home");
        File homeFile = new File(home);
        String lunarHome = ".lunarclient/settings/game/accounts.json";
        File accountsFile = new File(homeFile, lunarHome);
        if (!accountsFile.exists())
            return;

        try {
            FileReader reader = new FileReader(accountsFile);
            JsonObject main = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();

            JsonElement accountsJsonElement = main.get("accounts");
            if (accountsJsonElement == null)
                return;
            JsonObject accountsJsonObject = accountsJsonElement.getAsJsonObject();


            for (Map.Entry<String, JsonElement> entry : accountsJsonObject.entrySet()) {
                JsonObject accountJsonObject = entry.getValue().getAsJsonObject();
                JsonObject profile = accountJsonObject.get("minecraftProfile").getAsJsonObject();
                String name = profile.get("name").getAsString();
                String id = profile.get("id").getAsString();
                String accessToken=accountJsonObject.get("accessToken").getAsString();
                String refreshToken=accountJsonObject.get("refreshToken").getAsString();
                System.out.println(id + "USER NAME  AS DSA "+accessToken);
                Account a = new Account(name,id,accessToken,refreshToken,9999l);
                a.createAvatar();
                Winter.instance.accountManager.accountList.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
