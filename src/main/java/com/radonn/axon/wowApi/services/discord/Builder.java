package com.radonn.axon.wowApi.services.discord;

import java.awt.Color;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import com.radonn.axon.wowApi.models.Character;
import com.radonn.axon.wowApi.models.characterMedia.CharacterMedia;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.interactions.modals.ModalInteraction;
import net.dv8tion.jda.internal.interactions.component.ButtonImpl;
import net.dv8tion.jda.internal.interactions.component.TextInputImpl;
import net.dv8tion.jda.internal.interactions.modal.ModalImpl;


public class Builder {

    private static EmbedBuilder emb;
    private static ArrayList<LayoutComponent> mod;
    public static ArrayList<ItemComponent> act;

    public static class Embeds {

        private static EmbedBuilder model() {
            Builder.emb = new EmbedBuilder();
            Builder.emb.setColor(Color.decode("#1F1B4A").getRGB());
            Builder.emb.setFooter("Les Gardiens \u00C9ternels - AxonBOT v2.0.0", "https://cdn.discordapp.com/avatars/1142941427807039588/ed16b6807c6439836df32ff924ea044b.webp");
            Builder.emb.setTimestamp(Instant.ofEpochMilli(1597874400000L));
            return Builder.emb;
        }

        public static EmbedBuilder lgeMenu(String imageName, String thumbnailName) {
            EmbedBuilder embed = Builder.Embeds.model();
            embed.setTitle("<:lge_enter:1099955038408945694>\u0020\u0020- __Bienvenue chez les Gardiens Éternels !__");
            embed.setDescription("> Avant d'accéder au discord, veuillez prendre connaissance des informations importantes suivantes :");
            embed.addField("<:lge_person:1099955042800373772>\u0020\u0020- Acc\u00E8s Recrutement :", "> Si vous souhaitez postuler pour rejoindre notre guilde ou si vous \u00EAtes en cours de recrutement, veuillez noter qu'un entretien est requis pour pr\u00E9senter votre candidature.\n> __Cliquez sur le bouton \"Entretien\" pour continuer.__\n\u0020", false);
            embed.addField("<:lge_livrable:1099955041089097849>\u0020\u0020- Acc\u00E8s Invit\u00E9 :", "> Si vous appartenez d\u00E9j\u00E0 \u00E0 une autre guilde et que vous ne souhaitez pas rejoindre la n\u00F4tre \u00E0 plein temps, veuillez noter que l'acc\u00E8s au Discord sera temporaire.\n> __Cliquez sur le bouton \"Invit\u00E9\" pour continuer.__\n\u0020",false);
            embed.addField("", "Nous vous remercions de votre attention et nous espérons que vous passerez un agréable moment parmi les Gardiens Éternels sur notre serveur Discord !", false);
            embed.setImage("attachment://" + imageName);
            embed.setThumbnail("attachment://" + thumbnailName);
            return embed;
        }

        public static EmbedBuilder lgeMenuEntretienGetInfos(ModalInteraction event, Character character) {
            EmbedBuilder embed = Builder.Embeds.model();
            embed.setTitle(event.getMember().getEffectiveName() + ", voici les informations collectés à votre sujet :");
            embed.setDescription("### <:lge_person:1099955042800373772> - Pourquoi ? \n > Afin de faciliter le fonctionnement interne tel que les events et les différentes fonctionnalités, nous devons recueillir vos informations via l'Api de Blizzard.\n ### Est-ce bien vous ?");
            embed.addField("> Classe :", character.getCharacterClass().getName(), true);
            embed.addField("> Spécialisation :", character.getActiveSpec().getName() +" (" + character.getPlayableSpecialization().getRole().getName() + ")", true);
            embed.addField("> Race :", character.getRace().getName(), true);
            embed.addField("> Niveau d'objet :", "" +character.getEquippedItemLevel(), true);
            embed.addField("> RIO :", "" + character.getMythicKeystoneProfile().getCurrentMythicRating().getRating(), true);
            embed.addField("> Armurie :", "[Mon Profile](https://worldofwarcraft.blizzard.com/fr-fr/character/eu/hyjal/" + character.getName().toLowerCase() + ")", true);
            embed.addField("<:avertissement:1116643369507115078> - Attention", "> Il est important de noter que les informations présentent sont dynamiquement mise à jour lors de votre dernière déconexion et peuvent donc changer.", false);
            CharacterMedia media = character.getCharacterMedia();
            embed.setImage(media.getMainRawUrl());
            //embed.setThumbnail(media.getAvatarUrl());
            return embed;

        }

        public static EmbedBuilder lgeMenuInviteGetInfos(ModalInteraction event, Character character) {
            EmbedBuilder embed = Builder.Embeds.model();
            embed.setTitle(event.getMember().getEffectiveName() + ", voici les informations collectés à votre sujet :");
            embed.setDescription("### <:lge_person:1099955042800373772> - Pourquoi ? \n > Afin de faciliter le fonctionnement interne, nous devons recueillir vos informations via l'Api de Blizzard.\n __Votre accès au discord sera temporaire !__ ### Est-ce bien vous ?");
            embed.addField("> Classe :", character.getCharacterClass().getName(), true);
            embed.addField("> Spécialisation :", character.getActiveSpec().getName() +" (" + character.getPlayableSpecialization().getRole().getName() + ")", true);
            embed.addField("> Race :", character.getRace().getName(), true);
            embed.addField("> Niveau d'objet :", "" +character.getEquippedItemLevel(), true);
            embed.addField("> RIO :", "" + character.getMythicKeystoneProfile().getCurrentMythicRating().getRating(), true);
            embed.addField("> Armurie :", "[Mon Profile](https://worldofwarcraft.blizzard.com/fr-fr/character/eu/hyjal/" + character.getName().toLowerCase() + ")", true);
            embed.addField("<:avertissement:1116643369507115078> - Attention", "> Il est important de noter que les informations présentent sont dynamiquement mise à jour lors de votre dernière déconexion et peuvent donc changer.", false);
            CharacterMedia media = character.getCharacterMedia();
            embed.setImage(media.getMainRawUrl());
            //embed.setThumbnail(media.getAvatarUrl());
            return embed;
        }

    }

    public static class ItemComponents {

        private static Collection<ItemComponent> model() {
            Builder.act = new ArrayList<ItemComponent>();
            return Builder.act;
        }

        public static Collection<? extends ItemComponent> lgeMenu() {
            Collection<ItemComponent> actionRow = Builder.ItemComponents.model();
            actionRow.add(new ButtonImpl("lge_menu_entretien", "Entretien", ButtonStyle.SUCCESS, false, null));
            actionRow.add(new ButtonImpl("lge_menu_invite", "Invité", ButtonStyle.SECONDARY, false, null));
            actionRow.add(new ButtonImpl("lge_menu_quit", "Quitter", ButtonStyle.DANGER, false, null));
            return actionRow;
        }
        
    }

    public static class Modals {

        private static ArrayList<LayoutComponent> model() {
            Builder.mod = new ArrayList<LayoutComponent>();
            return Builder.mod;
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
            //textInputComponent.add(ActionRow.of(realmInput));
            Modal modal = new ModalImpl("lge_menu_invite",user.getName() + " | Bienvenue !",  textInputComponent);
            return modal;
        }

    }
}
