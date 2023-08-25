package com.radonn.axon.controllers.discord;

import com.radonn.axon.views.discord.builder.Modals;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class ButtonCommand {

    public static void lgeMenu(ButtonInteractionEvent event) {
        try {
            switch (event.getComponentId()) {
                case "lge_menu_entretien":
                    event.getJDA().getGatewayPool().submit((Runnable) () -> {
                        event.replyModal(Modals.modEntretien(event.getUser())).queue();
                    });
                    break;
                case "lge_menu_invite":
                        event.getJDA().getGatewayPool().submit((Runnable) () -> {
                            event.replyModal(Modals.modInvite(event.getUser())).queue();
                        });
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
