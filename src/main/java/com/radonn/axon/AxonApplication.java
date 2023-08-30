package com.radonn.axon;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.radonn.axon.controllers.UserLge.UserLgeController;
import com.radonn.axon.controllers.wow.BlizzardApiController;
import com.radonn.axon.exceptions.CharacterNotFoundException;
import com.radonn.axon.models.userLge.users;
import com.radonn.axon.models.wow.Character;
import com.radonn.axon.models.wow.guild.Guild;
import com.radonn.axon.services.discord.DiscordBotManager;

@SpringBootApplication
public class AxonApplication {

    public static BlizzardApiController BnetCtrl;
    public static Guild GuildCtrl;

    /*public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AxonApplication.class, args);
        
        AxonApplication.BnetCtrl = context.getBean(BlizzardApiController.class);
        
        try {
            AxonApplication.GuildCtrl = BnetCtrl.getGuild("hyjal", "gardiens-éternels");
        } catch (GuildNotFoundException e) {
            e.printStackTrace();
        }
            
        context.getBean(DiscordBotManager.class).init();
        System.out.println("Bot started !");
    }*/

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AxonApplication.class, args);
        DiscordBotManager discordApp = context.getBean(DiscordBotManager.class);
        discordApp.init();
        AxonApplication.BnetCtrl = context.getBean(BlizzardApiController.class);

        // Récupérer le contrôleur UserLge
        UserLgeController userLgeController = context.getBean(UserLgeController.class);

        // Récupérer le contrôleur BlizzardApiController
        BlizzardApiController blizzardApiController = context.getBean(BlizzardApiController.class);


        try {
            // Ajouter un utilisateur avec ID Discord et mainName
            users addedUser = userLgeController.addUser(discordApp.getJda().retrieveUserById(360825447786872843L).complete());
            System.out.println("Utilisateur ajouté avec l'ID Discord : " + addedUser.getDiscordID());
            System.out.println("Utilisateur ajouté avec le nom Discord : " + addedUser.getPseudo());
            System.out.println("Utilisateur ajouté avec la date d'arrivée : " + addedUser.getCommingDate().toString());
            
            // Récupérer un utilisateur par son ID Discord
            users retrievedUser = userLgeController.getUserById(360825447786872843L);
            System.out.println("Utilisateur récupéré : " + retrievedUser);

            // Récupérer les informations d'un personnage WoW
            Character character = blizzardApiController.getCharacter("hyjal", "xénonn");
            System.out.println("Informations du personnage : " + character);
        } catch (CharacterNotFoundException e) {
            e.printStackTrace();
        }
    }

}
