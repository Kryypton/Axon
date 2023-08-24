package com.radonn.axon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Consts {

    @Value("${blizzard.api.clientId}")
    private String clientId;
    @Value("${blizzard.api.clientSecret}")
    private String clientSecret;

    @Value("${discord.api.bot-token}")
    private String discordBotToken;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getDiscordBotToken() {
        return discordBotToken;
    }
}