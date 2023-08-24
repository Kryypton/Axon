package com.radonn.axon.wowApi.services.discord;

import net.dv8tion.jda.api.interactions.modals.ModalInteraction;

import com.radonn.axon.AxonApplication;
import com.radonn.axon.wowApi.models.Character;

public class ModalCommand {

    public static void lgeMenu(ModalInteraction event) {
        
        if (event.getModalId().equals("lge_menu_entretien")) event.getJDA().getGatewayPool().submit((Runnable) () -> {
            Character character = AxonApplication.BnetCtrl.getCharacter("hyjal", event.getValue("player_name").getAsString());
            event.replyEmbeds(Builder.Embeds.lgeMenuEntretienGetInfos(event, character).build()).queue();
        });

        if (event.getModalId().equals("lge_menu_invite")) event.getJDA().getGatewayPool().submit((Runnable) () -> {
            System.out.println("test22");
            System.out.println(event.getValue("relam_name").getAsString());
            System.out.println(event.getValue("player_name").getAsString());
            Character character = AxonApplication.BnetCtrl.getCharacter(event.getValue("relam_name").getAsString(), event.getValue("player_name").getAsString());
            System.out.println(character.getAchievementPoints());
            event.replyEmbeds(Builder.Embeds.lgeMenuInviteGetInfos(event, character).build()).queue();
        });
    }
    
}
