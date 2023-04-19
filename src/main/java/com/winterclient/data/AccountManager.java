package com.winterclient.data;

import com.google.gson.*;
import com.winterclient.account.Account;
import com.winterclient.auth.AccountAuthenticator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountManager {

    public List<Account> accountList;

    public Account activeAccount = null;

    public boolean pendingNewAccounts = false;
    public List<Account> pendingAccounts;

    File dataFolder;
    File accountsFile;

    public AccountManager() {
        accountList = new ArrayList<Account>();
        pendingAccounts = new ArrayList<Account>();
        dataFolder = new File("Winter");
        if (!dataFolder.exists())
            dataFolder.mkdir();
        accountsFile = new File(dataFolder, "accounts.json");
        if (!accountsFile.exists()) {
            try {
                accountsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        loadAccounts();
        if(activeAccount==null && accountList.size()>0){
            activeAccount=accountList.get(0);
        }
    }

    public void loadAccounts() {
        try {
            FileReader reader = new FileReader(accountsFile);
            JsonElement mainElement = JsonParser.parseReader(reader);
            if(mainElement.isJsonNull())
                return;
            JsonObject main=mainElement.getAsJsonObject();
            reader.close();

            JsonElement accountsJsonElement = main.get("accounts");
            if (accountsJsonElement == null)
                return;
            JsonObject accountsJsonObject = accountsJsonElement.getAsJsonObject();


            for (Map.Entry<String, JsonElement> entry : accountsJsonObject.entrySet()) {
                JsonObject accountJsonObject = entry.getValue().getAsJsonObject();

                String name = accountJsonObject.get("name").getAsString();
                String uuid = accountJsonObject.get("uuid").getAsString();
                String accessToken = accountJsonObject.get("accessToken").getAsString();
                String refreshToken = accountJsonObject.get("refreshToken").getAsString();
                String expirationTime = accountJsonObject.get("expirationTime").getAsString();
                Long expirationLong = Long.parseLong(expirationTime);
                if(name==null||uuid==null||accessToken==null||refreshToken==null||expirationLong==null)
                    return;
                Account account = new Account(name, uuid, accessToken, refreshToken,expirationLong);
                account.createAvatar();
                accountList.add(account);

            }
            JsonElement activeAccountJsonElement = main.get("activeAccount");
            if (activeAccountJsonElement != null) {
                String activeAccountUUID = activeAccountJsonElement.getAsString();
                for (Account account : accountList) {
                    if (account.uuid.equals(activeAccountUUID)) {
                        setActiveAccount(account);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAccounts() {
        JsonObject main = new JsonObject();
        JsonObject accountsJsonObject = new JsonObject();
        if (accountList.size() > 0) {
            accountList.forEach(accountObject -> {
                JsonObject accountJsonObject = new JsonObject();
                accountJsonObject.addProperty("name", accountObject.name);
                accountJsonObject.addProperty("uuid", accountObject.uuid);
                accountJsonObject.addProperty("accessToken", accountObject.accessToken);
                accountJsonObject.addProperty("refreshToken", accountObject.refreshToken);
                accountJsonObject.addProperty("expirationTime", accountObject.expirationTime);
                accountsJsonObject.add(accountObject.uuid, accountJsonObject);
            });
            main.add("accounts", accountsJsonObject);
            if(activeAccount!=null)
            main.addProperty("activeAccount", activeAccount.uuid);
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                FileWriter fileWriter = null;
                fileWriter = new FileWriter(accountsFile);
                gson.toJson(main, fileWriter);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void addAccountThroughWebview() {
        //initialize webview
    }

    public void addAccountThroughMicrosoftToken(String token) {
        AccountAuthenticator a = new AccountAuthenticator();

        new Thread(() -> {

            a.getAccessToken(token);
            if(a.success){
                Account newAccount = new Account(a.saveName, a.saveUUID, a.saveMinecraftToken, a.saveRefreshToken, a.saveExpiration);
                pendingNewAccounts =true;
                pendingAccounts.add(newAccount);
            }
        }).start();

    }

    public void refreshAccount(Account account) {
        new Thread(() -> {

            AccountAuthenticator a = new AccountAuthenticator();
        a.getAccessTokenFromRefreshToken(account.refreshToken);
        account.accessToken = a.saveMinecraftToken;
        account.refreshToken = a.saveRefreshToken;
        }).start();
    }

    public void setActiveAccount(Account account) {
        refreshAccount(account);
        this.activeAccount = account;
    }

}
