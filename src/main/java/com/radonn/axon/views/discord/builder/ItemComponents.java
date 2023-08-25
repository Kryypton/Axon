package com.radonn.axon.views.discord.builder;

import java.util.ArrayList;
import java.util.Collection;

import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.internal.interactions.component.ButtonImpl;

public class ItemComponents {

    public static ArrayList<ItemComponent> act;

    private static Collection<ItemComponent> model() {
        ItemComponents.act = new ArrayList<ItemComponent>();
        return ItemComponents.act;
    }

    public static Collection<? extends ItemComponent> lgeMenu() {
        Collection<ItemComponent> actionRow = ItemComponents.model();
        actionRow.add(new ButtonImpl("lge_menu_entretien", "Entretien", ButtonStyle.SUCCESS, false, null));
        actionRow.add(new ButtonImpl("lge_menu_invite", "Invité", ButtonStyle.SECONDARY, false, null));
        actionRow.add(new ButtonImpl("lge_menu_quit", "Quitter", ButtonStyle.DANGER, false, null));
        return actionRow;
    }
}
