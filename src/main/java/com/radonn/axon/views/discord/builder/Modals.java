package com.radonn.axon.views.discord.builder;

import java.util.ArrayList;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.internal.interactions.component.TextInputImpl;
import net.dv8tion.jda.internal.interactions.modal.ModalImpl;

public class Modals {
    private static ArrayList<LayoutComponent> mod;

    private static ArrayList<LayoutComponent> model() {
        Modals.mod = new ArrayList<LayoutComponent>();
        return Modals.mod;
    }

    public static Modal modEntretien(User user) {
        TextInput nameInput = new TextInputImpl("player_name", TextInputStyle.SHORT ,"Quel est votre Pseudo en jeu ?", 3, 30, true, null, "Caractères spéciaux incluts \"éçâß\" (sensible à la case)");
        ArrayList<LayoutComponent> textInputComponent = model();
        textInputComponent.add(ActionRow.of(nameInput));
        Modal modal = new ModalImpl("lge_menu_entretien",user.getName() + " | Bienvenue !",  textInputComponent);
        return modal;
    }

    public static Modal modInvite(User user) {
        TextInput nameInput = new TextInputImpl("player_name_invite", TextInputStyle.SHORT ,"Quel est votre Pseudo en jeu ?", 3, 30, true, null, "Caractères spéciaux incluts \"éçâß\" (sensible à la case)");
        TextInput realmInput = new TextInputImpl("realm_name_invite", TextInputStyle.SHORT ,"Quel est votre Royaume en jeu ?", 3, 30, true, "Hyjal", "Caractères spéciaux incluts \"éçâß\" (sensible à la case)");
        ArrayList<LayoutComponent> textInputComponent = model();
        textInputComponent.add(ActionRow.of(nameInput));
        textInputComponent.add(ActionRow.of(realmInput));
        Modal modal = new ModalImpl("lge_menu_invite",user.getName() + " | Bienvenue !",  textInputComponent);
        return modal;
    }
}