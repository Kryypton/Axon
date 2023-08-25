package com.radonn.axon.controllers.discord;

import net.dv8tion.jda.api.interactions.modals.ModalInteraction;

import com.radonn.axon.AxonApplication;
import com.radonn.axon.models.wow.models.Character;
import com.radonn.axon.views.discord.builder.*;

public class ModalCommand {

    public static void lgeMenu(ModalInteraction event) {  
            if (event.getModalId().equals("lge_menu_entretien")) event.getJDA().getGatewayPool().submit((Runnable) () -> {
                try {
                    Character character = AxonApplication.BnetCtrl.getCharacter("hyjal", event.getValue("player_name").getAsString());
                    event.replyEmbeds(Embeds.lgeMenuEntretienGetInfos(event, character).build()).queue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        
        if (event.getModalId().equals("lge_menu_invite")) event.getJDA().getGatewayPool().submit((Runnable) () -> {
            System.out.println(event.getValue("relam_name").getAsString());
            System.out.println(event.getValue("player_name").getAsString());
            Character character = AxonApplication.BnetCtrl.getCharacter(event.getValue("relam_name").getAsString(), event.getValue("player_name").getAsString());
            event.replyEmbeds(Embeds.lgeMenuInviteGetInfos(event, character).build()).queue();
        });
    }
    
}
