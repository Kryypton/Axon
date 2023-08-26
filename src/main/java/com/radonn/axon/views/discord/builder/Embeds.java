package com.radonn.axon.views.discord.builder;

import java.awt.Color;

import java.time.Instant;

import com.radonn.axon.models.wow.Character;
import com.radonn.axon.models.wow.characterMedia.CharacterMedia;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.modals.ModalInteraction;

public class Embeds {

    private static EmbedBuilder emb;

    private static EmbedBuilder model() {
        Embeds.emb = new EmbedBuilder();
        Embeds.emb.setColor(Color.decode("#1F1B4A").getRGB());
        Embeds.emb.setFooter("Les Gardiens \u00C9ternels - AxonBOT v2.0.0", "https://cdn.discordapp.com/avatars/1142941427807039588/ed16b6807c6439836df32ff924ea044b.webp");
        Embeds.emb.setTimestamp(Instant.ofEpochMilli(1597874400000L));
        return Embeds.emb;
    }

    public static EmbedBuilder loading() {
        EmbedBuilder embed = Embeds.model();
        embed.setTitle("Chargement en cours...");
        embed.setDescription("Veuillez patienter pendant que nous récupérons les informations.");
        embed.setThumbnail("attachment://loading.gif");
        return embed;
    }

    public static EmbedBuilder lgeMenu(String imageName, String thumbnailName) {
        EmbedBuilder embed = Embeds.model();
        embed.setTitle("<:lge_enter:1099955038408945694>\u0020\u0020- __Bienvenue chez les Gardiens Éternels !__");
        embed.setDescription("> Avant d'accéder au discord, veuillez prendre connaissance des informations importantes suivantes :");
        embed.addField("<:lge_person:1099955042800373772>\u0020\u0020- Acc\u00E8s Recrutement :", "> Si vous souhaitez postuler pour rejoindre notre guilde ou si vous \u00EAtes en cours de recrutement, veuillez noter qu'un entretien est requis pour pr\u00E9senter votre candidature.\n> __Cliquez sur le bouton \"Entretien\" pour continuer.__\n\u0020", false);
        embed.addField("<:lge_livrable:1099955041089097849>\u0020\u0020- Acc\u00E8s Invit\u00E9 :", "> Si vous appartenez d\u00E9j\u00E0 \u00E0 une autre guilde et que vous ne souhaitez pas rejoindre la n\u00F4tre \u00E0 plein temps, veuillez noter que l'acc\u00E8s au Discord sera temporaire.\n> __Cliquez sur le bouton \"Invit\u00E9\" pour continuer.__\n\u0020",false);
        embed.addField("", "Nous vous remercions de votre attention et nous espérons que vous passerez un agréable moment parmi les Gardiens Éternels sur notre serveur Discord !", false);
        embed.setImage("attachment://" + imageName);
        embed.setThumbnail("attachment://" + thumbnailName);
        return embed;
    }

    public static EmbedBuilder lgeMenuEntretienGetInfos(ModalInteraction event, Character character) throws Exception {
        EmbedBuilder embed = Embeds.model();
        embed.setTitle(event.getMember().getEffectiveName() + ", voici les informations collectés à votre sujet :");
        embed.setDescription("### <:lge_person:1099955042800373772> - Pourquoi ? \n > Afin de faciliter le fonctionnement interne tel que les events et les différentes fonctionnalités, nous devons recueillir vos informations via l'Api de Blizzard.\n ### Est-ce bien vous ?");
        embed.addField("> Classe :", character.getCharacterClass().getName(), true);
        embed.addField("> Spécialisation :", character.getActiveSpec().getName() +" (" + character.getPlayableSpecialization().getRole().getName() + ")", true);
        embed.addField("> Race :", character.getRace().getName(), true);
        embed.addField("> Niveau d'objet :", "" +character.getEquippedItemLevel(), true);
        embed.addField("> RIO :", (character.getMythicKeystoneProfile().getCurrentMythicRating() != null) ? character.getMythicKeystoneProfile().getCurrentMythicRating().getRating().toString() : "Pas de cl\u00E9 RIO", true);
        embed.addField("> Armurie :", "[Mon Profile](https://worldofwarcraft.blizzard.com/fr-fr/character/eu/" + character.getRealm().getSlug().toLowerCase() + "/" + character.getName().toLowerCase() + ")", true);
        embed.addField("<:avertissement:1116643369507115078> - Attention", "> Il est important de noter que les informations présentent sont dynamiquement mise à jour lors de votre dernière déconexion et peuvent donc changer.", false);
        CharacterMedia media = character.getCharacterMedia();
        embed.setImage(media.getMainRawUrl());
        //embed.setThumbnail(media.getAvatarUrl());
        return embed;
    }

    public static EmbedBuilder lgeMenuEntretienGetInfosError(ModalInteraction event) {
        EmbedBuilder embed = Embeds.model();
        embed.setTitle(event.getMember().getEffectiveName() + ", nous n'avons pas trouver votre personnage.");
        embed.setDescription("### <:lge_person:1099955042800373772> - Pourquoi ? \n > Afin de faciliter le fonctionnement interne tel que les events et les différentes fonctionnalités, nous devons recueillir vos informations via l'Api de Blizzard.");
        embed.addField("> Nouveau personnage ? ", event.getValue("player_name").getAsString() + " viens d'être créé ? L'Api de blizzard n'est donc pas encore disponible, il faudra donc patienter.", false);
        embed.addField("> Vennez-vous de migrer ? ", "Si vous vennez de migrer, c'est sûrement que l'api n'est pas à jour.", false);
        embed.addField("> Êtes-vous sur Hyjal ? ", "La guilde est sur le royaume d'Hyjal, il est impossible pour nous de guilder des joueurs provenant d'autres royaume", false);
        embed.addBlankField(false);
        embed.addField("> Solutions : ", "Vous pouvez contacter un Officier pour effectuer votre présentation de guilde autrement, ou accéder au discord en tant qu'invité.", false);
        return embed;
    }

    public static EmbedBuilder lgeMenuInviteGetInfos(ModalInteraction event, Character character) {
        EmbedBuilder embed = Embeds.model();
        embed.setTitle(event.getMember().getEffectiveName() + ", voici les informations collectés à votre sujet :");
        embed.setDescription("### <:lge_person:1099955042800373772> - Pourquoi ? \n > Afin de faciliter le fonctionnement interne, nous devons recueillir vos informations via l'Api de Blizzard.\n __Votre accès au discord sera temporaire !__ ### Est-ce bien vous ?");
        embed.addField("> Classe :", character.getCharacterClass().getName(), true);
        embed.addField("> Spécialisation :", character.getActiveSpec().getName() +" (" + character.getPlayableSpecialization().getRole().getName() + ")", true);
        embed.addField("> Race :", character.getRace().getName(), true);
        embed.addField("> Niveau d'objet :", "" + character.getEquippedItemLevel(), true);
        embed.addField("> RIO :", "" + character.getMythicKeystoneProfile().getCurrentMythicRating().getRating(), true);
        embed.addField("> Armurie :", "[Mon Profile](https://worldofwarcraft.blizzard.com/fr-fr/character/eu/" + character.getRealm().getSlug().toLowerCase() + "/" + character.getName().toLowerCase() + ")", true);
        embed.addField("<:avertissement:1116643369507115078> - Attention", "> Il est important de noter que les informations présentent sont dynamiquement mise à jour lors de votre dernière déconexion et peuvent donc changer.", false);
        CharacterMedia media = character.getCharacterMedia();
        embed.setImage(media.getMainRawUrl());
        return embed;
    }

    public static EmbedBuilder lgeMenuInviteGetInfosError(ModalInteraction event) {
        EmbedBuilder embed = Embeds.model();
        embed.setTitle(event.getMember().getEffectiveName() + ", nous n'avons pas trouver votre personnage.");
        embed.setDescription("### <:lge_person:1099955042800373772> - Pourquoi ? \n > Afin de faciliter le fonctionnement interne tel que les events et les différentes fonctionnalités, nous devons recueillir vos informations via l'Api de Blizzard.");
        embed.addField("> Nouveau personnage ? ", event.getValue("player_name").getAsString() +  "-" + event.getValue("realm_name") + " viens d'être créé ? L'Api de blizzard n'est donc pas encore disponible, il faudra donc patienter.", false);
        embed.addField("> Vennez-vous de migrer ? ", "Si vous vennez de migrer, c'est sûrement que l'api n'est pas à jour.", false);
        embed.addBlankField(false);
        embed.addField("> Solutions : ", "Vous pouvez contacter un Officier pour qu'il vous attribue manuellement le status d'invité pour accéder au discord.", false);
        return embed;
    }



}