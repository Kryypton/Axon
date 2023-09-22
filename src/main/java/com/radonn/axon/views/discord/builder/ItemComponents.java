package com.radonn.axon.views.discord.builder;

import java.util.ArrayList;
import java.util.Collection;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.modals.ModalInteraction;
import net.dv8tion.jda.internal.interactions.component.ButtonImpl;

public class ItemComponents {

    public static ArrayList<ItemComponent> act;

    private static Collection<ItemComponent> model() {
        ItemComponents.act = new ArrayList<ItemComponent>();
        return ItemComponents.act;
    }

    public static Collection<? extends ItemComponent> lgeMenu(SlashCommandInteractionEvent event) {
        Collection<ItemComponent> actionRow = ItemComponents.model();
        actionRow.add(new ButtonImpl("lge_menu_entretien", "Entretien", ButtonStyle.SUCCESS, false,
                event.getGuild().getEmojisByName("interview", true).get(0)));
        actionRow.add(new ButtonImpl("lge_menu_invite", "Invité", ButtonStyle.SECONDARY, false,
                event.getGuild().getEmojisByName("open", true).get(0)));
        actionRow.add(new ButtonImpl("lge_menu_quit", "Quitter", ButtonStyle.DANGER, false,
                event.getGuild().getEmojisByName("exit", true).get(0)));
        return actionRow;
    }

    public static Collection<? extends ItemComponent> lgeMenuError(ModalInteraction event) {
        Collection<ItemComponent> actionRow = ItemComponents.model();
        actionRow.add(new ButtonImpl("lge_menu_entretien_retry", "Réessayer", ButtonStyle.SUCCESS, false,
                event.getGuild().getEmojisByName("sync", true).get(0)));
        actionRow.add(new ButtonImpl("lge_menu_invite", "Invité", ButtonStyle.SECONDARY, false,
                event.getGuild().getEmojisByName("open", true).get(0)));
        actionRow.add(new ButtonImpl("lge_menu_quit", "Quitter", ButtonStyle.DANGER, false,
                event.getGuild().getEmojisByName("exit", true).get(0)));
        return actionRow;
    }

    // Entretien

    public static Collection<? extends ItemComponent> lgeMenuEntretienGetInfos(ModalInteraction event) {
        Collection<ItemComponent> actionRow = ItemComponents.model();
        actionRow.add(new ButtonImpl("lge_menu_entretien_charte", "Oui", ButtonStyle.SUCCESS, false,
                event.getGuild().getEmojisByName("accept", true).get(0)));
        actionRow.add(new ButtonImpl("lge_menu_entretien_retry", "Non, réessayer", ButtonStyle.DANGER, false,
                event.getGuild().getEmojisByName("sync", true).get(0)));
        return actionRow;
    }

    public static Collection<? extends ItemComponent> lgeMenuEntretienCharte(ButtonInteractionEvent event) {
        Collection<ItemComponent> actionRow = ItemComponents.model();
        actionRow.add(new ButtonImpl("lge_menu_entretien_charte_consult", "Consulter la charte", ButtonStyle.LINK,
                "https://drive.google.com/file/d/1CKQKRmiRqYuA6Y15qAgifg8gziwDSXSM/view", false,
                event.getGuild().getEmojisByName("ancientscroll", true).get(0)));
        actionRow.add(new ButtonImpl("lge_menu_entretien_rules", "Je suis d'accord", ButtonStyle.SUCCESS, false,
                event.getGuild().getEmojisByName("accept", true).get(0)));
        actionRow.add(new ButtonImpl("lge_menu_quit", "Je ne suis pas d'acccord, quitter", ButtonStyle.DANGER, false,
                event.getGuild().getEmojisByName("cancel", true).get(0)));
        return actionRow;
    }

    public static Collection<? extends ItemComponent> lgeMenuEntretienRules(ButtonInteractionEvent event) {
        Collection<ItemComponent> actionRow = ItemComponents.model();
        actionRow.add(new ButtonImpl("lge_menu_entretien_rules_consult", "Consulter les règlments", ButtonStyle.LINK,
                "https://drive.google.com/file/d/1f086755LtuFRSVPffH8Q4XESCs_9B97_/view", false, null));
        actionRow.add(new ButtonImpl("lge_menu_entretien_cgu", "J'accepte", ButtonStyle.SUCCESS, false,
                event.getGuild().getEmojisByName("accept", true).get(0)));
        actionRow.add(new ButtonImpl("lge_menu_quit", "Je n'accepte pas, quitter", ButtonStyle.DANGER, false,
                event.getGuild().getEmojisByName("cancel", true).get(0)));
        return actionRow;
    }

    public static Collection<? extends ItemComponent> lgeMenuEntretienCGU(ButtonInteractionEvent event) {
        Collection<ItemComponent> actionRow = ItemComponents.model();
        actionRow.add(new ButtonImpl("lge_menu_entretien_cgu_consult", "Consulter les CGU", ButtonStyle.LINK,
                "https://docs.google.com/document/u/1/d/e/2PACX-1vTpFFd-7ui30EoHI61I4AYy2ylvrnJxP_pkCGk_WvIDJVZ5qDlKM8e4eXmF4x4Xur1CkGGCJVqDqmia/pub",
                false, null));
        actionRow.add(new ButtonImpl("lge_menu_entretien_global_finish", "J'accepte", ButtonStyle.SUCCESS, false,
                event.getGuild().getEmojisByName("accept", true).get(0)));
        actionRow.add(new ButtonImpl("lge_menu_quit", "Je n'accepte pas, quitter", ButtonStyle.DANGER, false,
                event.getGuild().getEmojisByName("cancel", true).get(0)));
        return actionRow;
    }

    public static Collection<? extends ItemComponent> lgeMenuEntretienFinish(ButtonInteractionEvent event) {
        Collection<ItemComponent> actionRow = ItemComponents.model();
        actionRow.add(new ButtonImpl("lge_menu_entretien_complete", "Terminé", ButtonStyle.SUCCESS, false,
                event.getGuild().getEmojisByName("accept", true).get(0)));
        actionRow.add(new ButtonImpl("lge_menu_quit", "Annuler, quitter", ButtonStyle.DANGER, false,
                event.getGuild().getEmojisByName("cancel", true).get(0)));
        return actionRow;
    }

    public static Collection<? extends ItemComponent> lgeMenuEntretienValid(ButtonInteractionEvent event) {
        Collection<ItemComponent> actionRow = ItemComponents.model();
        actionRow.add(new ButtonImpl("lge_menu_entretien_valid", "Accepter", ButtonStyle.SUCCESS, false,
                event.getGuild().getEmojisByName("accept", true).get(0)));
        actionRow.add(new ButtonImpl("lge_menu_entretien_refuse", "Refuser, kick", ButtonStyle.DANGER, false,
                event.getGuild().getEmojisByName("cancel", true).get(0)));
        actionRow.add(new ButtonImpl("lge_menu_entretien_actualise", "Actualiser", ButtonStyle.SECONDARY, false,
                event.getGuild().getEmojisByName("sync", true).get(0)));
        return actionRow;
    }

    // Invite

    public static Collection<? extends ItemComponent> lgeMenuInviteGetInfos(ButtonInteractionEvent event) {
        Collection<ItemComponent> actionRow = ItemComponents.model();
        actionRow.add(new ButtonImpl("lge_menu_invite_get_infos", "Oui", ButtonStyle.SUCCESS, false,
                event.getGuild().getEmojisByName("accept", true).get(0)));
        actionRow.add(new ButtonImpl("lge_menu_invite_retry", "Non", ButtonStyle.DANGER, false,
                event.getGuild().getEmojisByName("sync", true).get(0)));
        return actionRow;
    }
}
