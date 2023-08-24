package com.radonn.axon.wowApi.services.discord;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class ButtonCommand {

    public static void lgeMenu(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("lge_menu_entretien")) event.getJDA().getGatewayPool().submit((Runnable) () -> {
            event.replyModal(Builder.Modals.modEntretien(event.getUser())).queue();
        });
        if (event.getComponentId().equals("lge_menu_invite")) event.getJDA().getGatewayPool().submit((Runnable) () -> {
            System.out.println("test");
            event.replyModal(Builder.Modals.modInvite(event.getUser())).queue();
        });
    }
    
}
