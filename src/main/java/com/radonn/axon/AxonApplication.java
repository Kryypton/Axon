package com.radonn.axon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.radonn.axon.controllers.UserLge.UserLgeController;
import com.radonn.axon.controllers.wow.BlizzardApiController;
import com.radonn.axon.exceptions.GuildNotFoundException;
import com.radonn.axon.models.wow.guild.Guild;
import com.radonn.axon.services.battleNetOAuth.BattleNetOAuthClient;
import com.radonn.axon.services.discord.DiscordBotManager;

@SpringBootApplication
public class AxonApplication {

    public static BlizzardApiController BnetCtrl;
    public static Guild GuildCtrl;
    public static UserLgeController UserLgeCtrl;

    /*
     * public static void main(String[] args) {
     * ApplicationContext context = SpringApplication.run(AxonApplication.class,
     * args);
     * 
     * AxonApplication.BnetCtrl = context.getBean(BlizzardApiController.class);
     * 
     * try {
     * AxonApplication.GuildCtrl = BnetCtrl.getGuild("hyjal", "gardiens-éternels");
     * } catch (GuildNotFoundException e) {
     * e.printStackTrace();
     * }
     * 
     * context.getBean(DiscordBotManager.class).init();
     * System.out.println("Bot started !");
     * }
     */

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AxonApplication.class, args);
        UserLgeCtrl = context.getBean(UserLgeController.class);
        AxonApplication.BnetCtrl = context.getBean(BlizzardApiController.class);
        System.out.println(BattleNetOAuthClient.getToken());
        try {
            AxonApplication.GuildCtrl = BnetCtrl.getGuild("hyjal", "gardiens-éternels");
        } catch (GuildNotFoundException e) {
            e.printStackTrace();
        }
        DiscordBotManager discordApp = context.getBean(DiscordBotManager.class);
        discordApp.init();

        /*
         * try {
         * // Ajouter un utilisateur avec ID Discord et mainName
         * Users addedUser =
         * userLgeController.addUser(discordApp.getJda().retrieveUserById(
         * 360825447786872843L).complete());
         * System.out.println("Utilisateur ajouté avec l'ID Discord : " +
         * addedUser.getDiscordID());
         * System.out.println("Utilisateur ajouté avec le nom Discord : " +
         * addedUser.getPseudo());
         * System.out.println("Utilisateur ajouté avec la date d'arrivée : " +
         * addedUser.getCommingDate().toString());
         * 
         * // Récupérer un utilisateur par son ID Discord
         * Users retrievedUser = userLgeController.getUserById(360825447786872843L);
         * System.out.println("Utilisateur récupéré : " + retrievedUser);
         * 
         * userLgeController.detectConnexionUser(360825447786872843L);
         * // PROGRAMME en pause durant 10 secondes;
         * Thread.sleep(10000);
         * userLgeController.detectDeconnexionUser(360825447786872843L);
         * userLgeController.addCharacter(360825447786872843L,
         * blizzardApiController.getCharacter("hyjal", "xénonn"));
         * 
         * List<Characters> c =
         * userLgeController.getCharacterByDiscordId(360825447786872843L);
         * for (Characters c2 : c) {
         * System.out.println(c2.getName());
         * }
         * 
         * // Récupérer les informations d'un personnage WoW
         * Character character = blizzardApiController.getCharacter("hyjal", "xénonn");
         * System.out.println("Informations du personnage : " + character);
         * 
         * } catch (Exception e) {
         * System.out.println("Erreur inconnue");
         * e.printStackTrace();
         * }
         */
    }
}
