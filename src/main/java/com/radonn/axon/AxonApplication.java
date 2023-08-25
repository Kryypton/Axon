package com.radonn.axon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.radonn.axon.controllers.wow.BlizzardApiController;
import com.radonn.axon.discord.DiscordBotManager;
import com.radonn.axon.exceptions.GuildNotFoundException;
import com.radonn.axon.models.wow.models.guild.Guild;

@SpringBootApplication
public class AxonApplication {

    public static BlizzardApiController BnetCtrl;
    public static Guild GuildCtrl;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AxonApplication.class, args);
        
        AxonApplication.BnetCtrl = context.getBean(BlizzardApiController.class);
        
        try {
            AxonApplication.GuildCtrl = BnetCtrl.getGuild("hyjal", "gardiens-éternels");
        } catch (GuildNotFoundException e) {
            e.printStackTrace();
        }
            
        context.getBean(DiscordBotManager.class).init();
        System.out.println("Bot started !");
    }
}
