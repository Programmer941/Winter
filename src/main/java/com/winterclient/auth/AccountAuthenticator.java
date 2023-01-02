package com.winterclient.auth;

import com.google.common.collect.ImmutableMap;
import com.winterclient.Winter;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

import java.util.*;


public class AccountAuthenticator {

    String saveAccessToken;
    public String saveRefreshToken;
    String saveXboxToken;
    String saveXSTSToken;
    String saveUHS;
    public String saveMinecraftToken;
    public String saveUUID;
    public String saveName;

    //get MICROSOFT TOKEN

    public void getMicrosoftToken() {
        //webviewstuffinlauncher
    }

    //GET ACCESS TOKEM

    public void getAccessToken(String microsoftToken) {

        String link = "https://login.live.com/oauth20_token.srf";

        HttpResponse<JsonNode> response = Unirest.post(link)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("client_id", "00000000402b5328")
                .field("code", microsoftToken)
                .field("grant_type", "authorization_code")
                .field("redirect_uri", "https://login.live.com/oauth20_desktop.srf")
                .field("scope", "service::user.auth.xboxlive.com::MBI_SSL")
                .asJson();

        if (response.isSuccess()) {
            System.out.println("Success (AccessToken): " + response.getBody());
            JSONObject data = response.getBody().getObject();
            String accessToken = data.getString("access_token");
            String refresh_token = data.getString("refresh_token");
            saveRefreshToken=refresh_token;
            getXboxLiveToken(accessToken);
        } else {
            JSONObject data = response.getBody().getObject();
            String error = data.getString("error");
            String description = data.getString("error_description");
            System.out.println("Error: " + error + " Reason: " + description);
        }
    }

    public void getAccessTokenFromRefreshToken(String refreshToken) {

        String link = "https://login.live.com/oauth20_token.srf";

        HttpResponse<JsonNode> response = Unirest.post(link)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("client_id", "00000000402b5328")
                .field("refresh_token", refreshToken)
                .field("grant_type", "refresh_token")
                .field("redirect_uri", "https://login.live.com/oauth20_desktop.srf")
                .field("scope", "service::user.auth.xboxlive.com::MBI_SSL")
                .asJson();

        if (response.isSuccess()) {
            System.out.println("Success (AccessToken): " + response.getBody());
            JSONObject data = response.getBody().getObject();
            String accessToken = data.getString("access_token");
            String refresh_token = data.getString("refresh_token");
            saveRefreshToken=refresh_token;
            getXboxLiveToken(accessToken);
        } else {
            JSONObject data = response.getBody().getObject();
            String error = data.getString("error");
            String description = data.getString("error_description");
            System.out.println("Error: " + error + " Reason: " + description);
        }
    }

    //GET XBOX LIVE TOKEN

    public void getXboxLiveToken(String accessToken) {

        String link = "https://user.auth.xboxlive.com/user/authenticate";

        Map<Object, Object> info = ImmutableMap.of(
                "Properties", ImmutableMap.of(
                        "AuthMethod", "RPS",
                        "SiteName", "user.auth.xboxlive.com",
                        "RpsTicket", accessToken
                ),
                "RelyingParty", "http://auth.xboxlive.com",
                "TokenType", "JWT"
        );

        JSONObject json = new JSONObject(info);

        HttpResponse<JsonNode> response = Unirest.post(link)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(json)
                .asJson();

        if (response.isSuccess()) {
            System.out.println("Success (xboxLiveToken): " + response.getBody());
            JSONObject data = response.getBody().getObject();
            String token = data.getString("Token");
            JSONObject uhsJson = (JSONObject) data.getJSONObject("DisplayClaims").getJSONArray("xui").get(0);
            String uhs = uhsJson.getString("uhs");
            saveUHS = uhs;

            getXSTSToken(token);
        } else {
            System.out.println("Error: Couldn't get Xbox token!");
        }
    }

    //GET XSTS TOKEN
    public void getXSTSToken(String xboxLiveToken) {

        String link = "https://xsts.auth.xboxlive.com/xsts/authorize";

        String[] tokenArray = new String[1];
        tokenArray[0] = xboxLiveToken;

        Map<Object, Object> info = ImmutableMap.of(
                "Properties", ImmutableMap.of(
                        "SandboxId", "RETAIL",
                        "UserTokens", tokenArray
                ),
                "RelyingParty", "rp://api.minecraftservices.com/",
                "TokenType", "JWT"
        );

        System.out.println(info);

        JSONObject json = new JSONObject(info);

        HttpResponse<JsonNode> response = Unirest.post(link)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(json)
                .asJson();

        if (response.isSuccess()) {
            System.out.println("Success (XSTSToken): " + response.getBody());
            JSONObject data = response.getBody().getObject();
            String token = data.getString("Token");
            getMinecraftToken(saveUHS, token);
        } else {
            System.out.println("Error: Couldn't get XSTS token!");
        }
    }

    //Get Minecraft Token
    public void getMinecraftToken(String uhs, String XSTSToken) {

        String link = "https://api.minecraftservices.com/authentication/login_with_xbox";

        Map<Object, Object> info = ImmutableMap.of(
                "identityToken", "XBL3.0 x=" + uhs + ";" + XSTSToken
        );

        JSONObject json = new JSONObject(info);


        HttpResponse<JsonNode> response = Unirest.post(link)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(json)
                .asJson();

        if (response.isSuccess()) {
            System.out.println("Success (MinecraftToken): " + response.getBody());
            JSONObject data = response.getBody().getObject();
            String token = data.getString("access_token");
            saveMinecraftToken=token;
            checkAccount(token);
        } else {
            System.out.println("Error: Couldn't get Minecraft token!");
        }
    }

    //CHECK ACCOUNT MINECRAFT STORE
    public void checkAccount(String minecraftToken) {
        String link = "https://api.minecraftservices.com/entitlements/mcstore";

        HttpResponse<JsonNode> response = Unirest.get(link)
                .header("Authorization", "Bearer " + minecraftToken)
                .asJson();

        if (response.isSuccess()) {
            System.out.println("Success (checkAccount): " + response.getBody());
            getProfile(minecraftToken);
        } else {
            System.out.println("Error: Couldn't check Account!");
        }
    }

    //GET PROFILE INFORMATION
    public void getProfile(String minecraftToken){

        String link ="https://api.minecraftservices.com/minecraft/profile";

        HttpResponse<JsonNode> response = Unirest.get(link)
                .header("Authorization", "Bearer " + minecraftToken)
                .asJson();

        if (response.isSuccess()) {
            System.out.println("Success (Get Profile): " + response.getBody());
            JSONObject data = response.getBody().getObject();
            String uuid = data.getString("id");
            String name = data.getString("name");
            saveUUID=uuid;
            saveName=name;
            System.out.println(saveUUID+" "+saveName);
        } else {
            System.out.println("Error: Couldn't get profile!");
        }
    }

    public void setSession(){
//        if(saveUUID != null) {
//            Session newSession = new Session(saveName, saveUUID, saveMinecraftToken, "Xbox");
//            Winter.instance.currentSession = newSession;
//        }else{
//            System.out.println("LOGIN FAILED");
//        }
    }



}
