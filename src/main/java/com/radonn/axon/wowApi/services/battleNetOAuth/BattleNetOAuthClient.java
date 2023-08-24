package com.radonn.axon.wowApi.services.battleNetOAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radonn.axon.config.Consts;

@Component
public class BattleNetOAuthClient {

    private String tokenEndpoint;
    private String access_token;
    private String credentials;

    private static Consts consts;
    private static BattleNetOAuthClient instance;

    @Autowired
    public BattleNetOAuthClient(Consts consts) {
        BattleNetOAuthClient.consts = consts;
        this.tokenEndpoint = "https://oauth.battle.net/token";
        this.credentials = consts.getClientId() + ":" + consts.getClientSecret();
        this.fetchAccessToken();
        BattleNetOAuthClient.instance = this;
    }

    public static String getToken() {
        if (BattleNetOAuthClient.instance == null) {
            BattleNetOAuthClient.instance = new BattleNetOAuthClient(consts);
        }
        return BattleNetOAuthClient.instance.retrieveAccessToken();
    }
    
    private Boolean isValid() {
        String url = "https://oauth.battle.net/oauth/check_token?token=" + this.access_token;

        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);   

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String jsonResponse = response.toString();
                int start = jsonResponse.indexOf("\"exp\":") + 6;
                int end = jsonResponse.indexOf(",", start);
                String expiration = jsonResponse.substring(start, end);
                long expirationLong = Long.parseLong(expiration);
                long now = System.currentTimeMillis() / 1000L;
                return expirationLong > now;
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    private String getAccessToken() {
        if (this.isValid()) {
            return this.access_token;
        } else {
            this.fetchAccessToken();
            return this.access_token;
        }
    }

    private void fetchAccessToken() {
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        try { 
            URL url = new URL(this.tokenEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            String requestBody = "grant_type=client_credentials";
            byte[] requestBodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);
            connection.getOutputStream().write(requestBodyBytes);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String jsonResponse = response.toString();
                int start = jsonResponse.indexOf("\"access_token\":\"") + 16;
                int end = jsonResponse.indexOf("\"", start);
                this.access_token = jsonResponse.substring(start, end);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public String retrieveAccessToken() {
        if (this.getAccessToken() == null) {
            throw new BattleNetOAuthClientNullException("Échec de la récupération de l'access token.");
        }
        return this.getAccessToken();
    }
}
