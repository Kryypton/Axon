package com.radonn.axon;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.radonn.axon.config.Consts;
import com.radonn.axon.discord.DiscordBotManager;
import com.radonn.axon.wowApi.controllers.BlizzardApiController;
import com.radonn.axon.wowApi.models.guild.Guild;

import org.slf4j.Logger;

@SpringBootApplication
public class AxonApplication {

    private static final Logger logger = LoggerFactory.getLogger(AxonApplication.class);
    public static BlizzardApiController BnetCtrl;
    public static Guild GuildCtrl;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AxonApplication.class, args);
        
        AxonApplication.BnetCtrl = context.getBean(BlizzardApiController.class);
        AxonApplication.GuildCtrl = BnetCtrl.getGuild("hyjal", "gardiens-éternels");
            
        String tok = context.getBean(Consts.class).getDiscordBotToken();

        context.getBean(DiscordBotManager.class).init();
        System.out.println("Bot started !");
    }
}
