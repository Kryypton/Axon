package com.radonn.axon.controllers.discord;

import com.radonn.axon.views.discord.builder.Modals;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class ButtonCommand {

    public static void lgeMenu(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("lge_menu_entretien")) event.getJDA().getGatewayPool().submit((Runnable) () -> {
            event.replyModal(Modals.modEntretien(event.getUser())).queue();
        });
        if (event.getComponentId().equals("lge_menu_invite")) event.getJDA().getGatewayPool().submit((Runnable) () -> {
            System.out.println("test");
            event.replyModal(Modals.modInvite(event.getUser())).queue();
        });
    }
    
}
