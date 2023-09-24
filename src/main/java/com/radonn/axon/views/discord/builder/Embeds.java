package com.radonn.axon.views.discord.builder;

import java.awt.Color;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.radonn.axon.AxonApplication;
import com.radonn.axon.exceptions.CharacterMediaNotFoundException;
import com.radonn.axon.exceptions.CharacterNotFoundException;
import com.radonn.axon.exceptions.MythicKeystoneProfileNotFoundException;
import com.radonn.axon.models.userLge.GuildUser;
import com.radonn.axon.models.wow.Character;
import com.radonn.axon.models.wow.characterMedia.CharacterMedia;
import com.radonn.axon.models.wow.mythicKeystoneProfile.BestRun;
import com.radonn.axon.models.wow.mythicKeystoneProfile.MythicKeystoneProfile;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.modals.ModalInteraction;

public class Embeds {

  private static EmbedBuilder emb;

  private static EmbedBuilder model() {
    Embeds.emb = new EmbedBuilder();
    Embeds.emb.setColor(Color.decode("#1F1B4A").getRGB());
    Embeds.emb.setFooter("Les Gardiens \u00C9ternels - AxonBOT v2.0.0",
        "https://cdn.discordapp.com/avatars/1142941427807039588/ed16b6807c6439836df32ff924ea044b.webp");
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
    embed.setTitle(
        "<:lge_enter:1099955038408945694>\u0020\u0020- __Bienvenue chez les Gardiens Éternels !__");
    embed.setDescription(
        "> Avant d'accéder au discord, veuillez prendre connaissance des informations importantes suivantes :");
    embed.addField("<:lge_person:1099955042800373772>\u0020\u0020- Acc\u00E8s Recrutement :",
        "> Si vous souhaitez postuler pour rejoindre notre guilde ou si vous \u00EAtes en cours de recrutement, veuillez noter qu'un entretien est requis pour pr\u00E9senter votre candidature.\n> __Cliquez sur le bouton \"Entretien\" pour continuer.__\n\u0020",
        false);
    embed.addField("<:lge_livrable:1099955041089097849>\u0020\u0020- Acc\u00E8s Invit\u00E9 :",
        "> Si vous appartenez d\u00E9j\u00E0 \u00E0 une autre guilde et que vous ne souhaitez pas rejoindre la n\u00F4tre \u00E0 plein temps, veuillez noter que l'acc\u00E8s au Discord sera temporaire.\n> __Cliquez sur le bouton \"Invit\u00E9\" pour continuer.__\n\u0020",
        false);
    embed.addField("",
        "Nous vous remercions de votre attention et nous espérons que vous passerez un agréable moment parmi les Gardiens Éternels sur notre serveur Discord !",
        false);
    System.out.println(imageName);
    embed.setImage("attachment://" + imageName);
    embed.setThumbnail("attachment://" + thumbnailName);
    return embed;
  }

  public static EmbedBuilder lgeMenuEntretienGetInfos(ModalInteraction event, Character character,
      CharacterMedia media) throws CharacterMediaNotFoundException {
    EmbedBuilder embed = Embeds.model();
    embed.setTitle(event.getMember().getEffectiveName()
        + ", voici les informations collectés à votre sujet :");
    embed.setDescription(
        "### <:lge_person:1099955042800373772> - Pourquoi ? \n > Afin de faciliter le fonctionnement interne tel que les events et les différentes fonctionnalités, nous devons recueillir vos informations via l'Api de Blizzard.\n ### Est-ce bien vous ?");
    embed.addField("> Classe :", character.getCharacterClass().getName(), true);
    embed.addField("> Spécialisation :", character.getActiveSpec().getName() + " ("
        + character.getPlayableSpecialization().getRole().getName() + ")", true);
    embed.addField("> Race :", character.getRace().getName(), true);
    embed.addField("> Niveau d'objet :", "" + character.getEquippedItemLevel(), true);
    try {
      embed.addField("> RIO :",
          (character.getMythicKeystoneProfile().getCurrentMythicRating() != null)
              ? character.getMythicKeystoneProfile().getCurrentMythicRating().getRating().toString()
              : "Pas de cl\u00E9 RIO",
          true);
    } catch (MythicKeystoneProfileNotFoundException e) {
      embed.addField("> RIO :", "Pas de cl\u00E9 RIO", true);
    }
    embed.addField("> Armurie :",
        "[Mon Profile](https://worldofwarcraft.blizzard.com/fr-fr/character/eu/"
            + character.getRealm().getSlug().toLowerCase() + "/" + character.getName().toLowerCase()
            + ")",
        true);
    embed.addField("<:avertissement:1116643369507115078> - Attention",
        "> Il est important de noter que les informations présentent sont dynamiquement mise à jour lors de votre dernière déconexion et peuvent donc changer.",
        false);
    embed.setImage(media.getMainRawUrl());

    // embed.setThumbnail(media.getAvatarUrl());
    return embed;
  }

  public static EmbedBuilder lgeMenuEntretienGetInfos(ModalInteraction event, Character character,
      String avatarUrl) {
    EmbedBuilder embed = Embeds.model();
    embed.setTitle(event.getMember().getEffectiveName()
        + ", voici les informations collectés à votre sujet :");
    embed.setDescription(
        "### <:lge_person:1099955042800373772> - Pourquoi ? \n > Afin de faciliter le fonctionnement interne tel que les events et les différentes fonctionnalités, nous devons recueillir vos informations via l'Api de Blizzard.\n ### Est-ce bien vous ?");
    embed.addField("> Classe :", character.getCharacterClass().getName(), true);
    embed.addField("> Spécialisation :", character.getActiveSpec().getName() + " ("
        + character.getPlayableSpecialization().getRole().getName() + ")", true);
    embed.addField("> Race :", character.getRace().getName(), true);
    embed.addField("> Niveau d'objet :", "" + character.getEquippedItemLevel(), true);
    try {
      embed.addField("> RIO :",
          (character.getMythicKeystoneProfile().getCurrentMythicRating() != null)
              ? character.getMythicKeystoneProfile().getCurrentMythicRating().getRating().toString()
              : "Pas de cl\u00E9 RIO",
          true);
    } catch (MythicKeystoneProfileNotFoundException e) {
      embed.addField("> RIO :", "Pas de cl\u00E9 RIO", true);
    }
    embed.addField("> Armurie :",
        "[Mon Profile](https://worldofwarcraft.blizzard.com/fr-fr/character/eu/"
            + character.getRealm().getSlug().toLowerCase() + "/" + character.getName().toLowerCase()
            + ")",
        true);
    embed.addField("<:avertissement:1116643369507115078> - Attention",
        "> Il est important de noter que les informations présentent sont dynamiquement mise à jour lors de votre dernière déconexion et peuvent donc changer.",
        false);
    embed.setImage("attachment://" + avatarUrl);
    // embed.setThumbnail(media.getAvatarUrl());
    return embed;
  }

  public static EmbedBuilder lgeMenuEntretienGetInfosError(ModalInteraction event) {
    EmbedBuilder embed = Embeds.model();
    embed.setTitle(
        event.getMember().getEffectiveName() + ", nous n'avons pas trouver votre personnage.");
    embed.setDescription(
        "### <:lge_person:1099955042800373772> - Pourquoi ? \n > Afin de faciliter le fonctionnement interne tel que les events et les différentes fonctionnalités, nous devons recueillir vos informations via l'Api de Blizzard.");
    embed.addField("> Nouveau personnage ? ", event.getValue("player_name").getAsString()
        + " viens d'être créé ? L'Api de blizzard n'est donc pas encore disponible, il faudra donc patienter.",
        false);
    embed.addField("> Vennez-vous de migrer ? ",
        "Si vous vennez de migrer, c'est sûrement que l'api n'est pas à jour.", false);
    embed.addField("> Êtes-vous sur Hyjal ? ",
        "La guilde est sur le royaume d'Hyjal, il est impossible pour nous de guilder des joueurs provenant d'autres royaume",
        false);
    embed.addField("> Vous essayer d'entrer avec un personnage déjà enregistrer sur discord ? ",
        "Il est possible que vous ayez perdu votre compte discord, c'est pourquoi vous devez vous redirigez vers un membre du Bloc Premier.",
        false);
    embed.addBlankField(false);
    embed.addField("> Solutions : ",
        "Vous pouvez contacter un Officier pour effectuer votre présentation de guilde autrement, ou accéder au discord en tant qu'invité.",
        false);
    return embed;
  }

  public static EmbedBuilder lgeMenuEntretienCharte(String thumbnailName) {
    EmbedBuilder embed = Embeds.model();
    embed.setTitle("Charte de la guilde");
    embed.setDescription(
        "> La charte pr\u00E9sente les objectifs de guilde et le contenu propos\u00E9.");
    embed.setThumbnail("attachment://" + thumbnailName);
    return embed;
  }

  public static EmbedBuilder lgeMenuEntretienRules(String thumbnailName) {
    EmbedBuilder embed = Embeds.model();
    embed.setTitle("R\u00E8glements raccourcis");
    embed.setDescription(
        "> Il est important de lire les r\u00E8gle de la guilde et de s'engager \u00E0 les respecter");
    embed.addField("> Pourquoi ce règlement n'est pas comme les autres ?",
        "> Il es important que ces règles soient connus. Les règles qui y sont implantés ne se limitent pas qu'à des normes de comportement.",
        false);
    embed.setThumbnail("attachment://" + thumbnailName);
    return embed;
  }

  public static EmbedBuilder lgeMenuEntretienCGU(String thumbnailName) {
    EmbedBuilder embed = Embeds.model();
    embed.setTitle("Condition générale d'utilisations et règlements structurés de guilde");
    embed.setDescription("> Dans chaque instances, il y a des manières de faire à respecter.");
    embed.addField("> Pourquoi des \"CGU\" ?",
        "> Elles présentes l'enssemble des conditions ainsi que les différentes procédures appliqués, elles peuvent ne pas être lu. Cependant elles sont appliqués quoi qu'il en coûte et pour tous.",
        false);
    embed.setThumbnail("attachment://" + thumbnailName);
    return embed;
  }

  public static EmbedBuilder lgeMenuEntretienFinish(String thumbnailName) {
    EmbedBuilder embed = Embeds.model();
    embed.setTitle("Terminé !");
    embed.setDescription(
        "> L'enssemble des informations ont été collectés. Votre accès au discord en tant que futur membre est en cours de traitement. \n > Vous recevrez une convocation pour qu'un recruteur effectue votre présentation de guilde !");
    embed.addField("> Attention",
        "> Merci de cliquer sur \"Terminé\" pour finaliser la procédure !", false);
    embed.setThumbnail("attachment://" + thumbnailName);
    return embed;
  }

  public static EmbedBuilder lgeMenuEntretienCallBack(JDA jda, GuildUser user) {
    EmbedBuilder embed = Embeds.model();
    embed.setTitle(":lge_livrable: - Proc\u00E9dure de requ\u00EAtes d'informations termin\u00E9e");
    embed.setDescription("> __Voici les information conernant cet utilisateur :__");
    try {
      Embeds.getInfos(jda, user).forEach(f -> embed.addField(f));
    } catch (CharacterNotFoundException e) {
      e.printStackTrace();
      return Embeds.lgeMenuEntretienCallBackError(jda, user, e);
    } catch (Exception e) {
      return Embeds.lgeMenuEntretienCallBackError(jda, user, e);
    }
    return embed;
  }

  private static EmbedBuilder lgeMenuEntretienCallBackError(JDA jda, GuildUser user, Exception e) {
    EmbedBuilder embed = Embeds.model();
    embed.setTitle("Erreur");
    embed.setDescription(
        "> Une erreur est survenue lors de la récupération des informations de l'utilisateur : "
            + user.getDiscordID() + "/ **`" + user.getPseudo() + "`**");
    embed.addField("Error Type", "" + "`" + e.getLocalizedMessage() + "`", false);
    embed.addField("Message", "" + "`" + e.getMessage() + "`", false);
    StringBuilder sb = new StringBuilder();
    for (StackTraceElement element : e.getStackTrace()) {
      sb.append(element.toString());
      sb.append("\n");
    }
    embed.addField("Stack Trace", "" + "```" + sb.toString().substring(0, 1000) + "...```", false);

    return embed;
  }

  public static EmbedBuilder lgeMenuEntretienAccept(MessageEmbed emb, String userID) {
    EmbedBuilder embed = model();
    embed.setTitle(emb.getTitle());
    embed.setDescription(emb.getDescription());
    for (int i = 0; i < emb.getFields().size() - 1; i++) {
      embed.addField(emb.getFields().get(i).getName(), emb.getFields().get(i).getValue(),
          emb.getFields().get(i).isInline());
    }
    embed.addField(emb.getFields().get(emb.getFields().size()).getName(),
        "> <:avertissement:1116643369507115078> Effectuée par <#" + userID + ">", false);
    return embed;
  }

  public static EmbedBuilder lgeMenuEntretienRefuse(MessageEmbed emb, String userID) {
    EmbedBuilder embed = model();
    embed.setTitle(emb.getTitle());
    embed.setDescription(emb.getDescription());
    for (int i = 0; i < emb.getFields().size() - 1; i++) {
      embed.addField(emb.getFields().get(i).getName(), emb.getFields().get(i).getValue(),
          emb.getFields().get(i).isInline());
    }
    embed.addField(emb.getFields().get(emb.getFields().size()).getName(),
        "> <:avertissement:1116643369507115078> Refusée par <#" + userID + ">", false);
    return embed;
  }

  public static EmbedBuilder lgeMenuEntretienRefresh(MessageEmbed emb, String userID) {
    EmbedBuilder embed = model();
    embed.setTitle(emb.getTitle());
    embed.setDescription(emb.getDescription());
    embed.addField(emb.getFields().get(emb.getFields().size()).getName(),
        emb.getFields().get(emb.getFields().size()).getValue(), false);
    return embed;
  }

  private static ArrayList<Field> getInfos(JDA jda, GuildUser user)
      throws CharacterNotFoundException {
    ArrayList<Field> fields = new ArrayList<>();
    Character character = AxonApplication.UserLgeCtrl
        .getCharacterMainByDiscordId(Long.valueOf(user.getDiscordID())).getCharacter();
    User usr = jda.retrieveUserById(user.getDiscordID()).complete();
    fields.add(new Field(null, null, false));
    fields.add(new Field("ID Discord :", "" + "`" + user.getDiscordID() + "`", true));
    fields.add(new Field("Nom discord :", "`" + usr.getName() + "`", true));
    fields.add(new Field("Date de cr\u00E9ation :",
        "`" + usr.getTimeCreated().toLocalDate().toString() + "`", true));
    fields
        .add(
            new Field("Pseudo en jeu :",
                "`" + AxonApplication.UserLgeCtrl.getCharacterByDiscordId(user.getDiscordID())
                    .get(0).getName() + "`" + " | **Visuel Discord :**" + usr.getAsMention(),
                false));
    fields.add(new Field("**Informations G\u00E9n\u00E9rals :**", "**Classe :**" + " " + jda
        .getEmojisByName(
            character.getCharacterClass().getName().toLowerCase().replace(" ", "").replace("é", "e")
                .replace("ï", "i").replace("ê", "e").replace("-", "").replace("\'", ""),
            true)
        .get(0).getAsMention() + " " + character.getCharacterClass().getName() + "\n" + "**Rôle :**"
        + " "
        + jda.getEmojisByName(character.getPlayableSpecialization().getRole().getName()
            .replace(" ", "").replace("é", "e").replace("â", "a"), true).get(0).getAsMention()
        + " " + character.getPlayableSpecialization().getRole().getName() + "\n" + "**Spec :**"
        + " " + character.getActiveSpec().getName() + "\n" + "**Race :**" + " "
        + jda.getEmojisByName(character.getRace().getName().replace(" ", ""), true).get(0)
            .getAsMention()
        + " " + character.getRace().getName() + "\n" + "**Faction :**" + " "
        + jda.getEmojisByName(character.getFaction().getName().replace(" ", ""), true).get(0)
            .getAsMention()
        + " " + character.getFaction().getName() + "\n" + "**ILVL :**" + " "
        + character.getEquippedItemLevel() + "\n", false));
    try {
      MythicKeystoneProfile mk =
          AxonApplication.UserLgeCtrl.getCharacterByDiscordId(user.getDiscordID()).get(0)
              .getCharacter().getMythicKeystoneProfile();
      StringBuilder sb = new StringBuilder();
      for (BestRun bestRun : mk.getLastSeason().getBestRun()) {
        System.out.println(bestRun.toString());
        sb.append(bestRun.toString() + "\n");
      }
      fields.add(new Field("> Informations MM+ :",
          "`" + mk.getCurrentMythicRating().getRating() + "` \n `" + sb.toString() + "`", false));
    } catch (Exception e) {
      e.printStackTrace();
      fields.add(new Field("> Informations MM+ :", "Pas de cl\u00E9 RIO", false));
    }

    try {
      StringBuilder sb = new StringBuilder();
      // TODO : !!!!
      AxonApplication.UserLgeCtrl.getCharacterByDiscordId(user.getDiscordID()).get(0).getCharacter()
          .getMythicRaidProfile().getLastExpensions().getInstances().forEach(instance -> {
            instance.getModes().forEach(mode -> {
              sb.append(instance.getInstance().getName() + " : " + mode.getDifficulty().getName()
                  + "(" + mode.getProgress().getCompletedCount() + "/"
                  + mode.getProgress().getTotalCount() + ") " + mode.getStatus().getName() + "\n");
            });
          });
      fields.add(new Field("> Informations RAID : ", "`" + sb.toString() + "`", false));
    } catch (Exception e) {
      e.printStackTrace();
      fields.add(new Field("> Informations RAID : ", "Pas de Raid effectués", false));
    }

    fields.add(new Field("> Statue de la présentation de Guilde :",
        "> <:avertissement:1116643369507115078> Incomplête", false));
    return fields;
  }

  /// INVITE

  public static EmbedBuilder lgeMenuInviteGetInfos(ModalInteraction event, Character character) {
    EmbedBuilder embed = Embeds.model();
    embed.setTitle(event.getMember().getEffectiveName()
        + ", voici les informations collectés à votre sujet :");
    embed.setDescription(
        "### <:lge_person:1099955042800373772> - Pourquoi ? \n > Afin de faciliter le fonctionnement interne, nous devons recueillir vos informations via l'Api de Blizzard.\n __Votre accès au discord sera temporaire !__ ### Est-ce bien vous ?");
    embed.addField("> Classe :", character.getCharacterClass().getName(), true);
    embed.addField("> Spécialisation :", character.getActiveSpec().getName() + " ("
        + character.getPlayableSpecialization().getRole().getName() + ")", true);
    embed.addField("> Race :", character.getRace().getName(), true);
    embed.addField("> Niveau d'objet :", "" + character.getEquippedItemLevel(), true);
    try {
      embed.addField("> RIO :",
          "" + character.getMythicKeystoneProfile().getCurrentMythicRating().getRating(), true);
    } catch (MythicKeystoneProfileNotFoundException e) {
      embed.addField("> RIO :", "Pas de cl\u00E9 RIO", true);
    }
    embed.addField("> Armurie :",
        "[Mon Profile](https://worldofwarcraft.blizzard.com/fr-fr/character/eu/"
            + character.getRealm().getSlug().toLowerCase() + "/" + character.getName().toLowerCase()
            + ")",
        true);
    embed.addField("<:avertissement:1116643369507115078> - Attention",
        "> Il est important de noter que les informations présentent sont dynamiquement mise à jour lors de votre dernière déconexion et peuvent donc changer.",
        false);
    try {
      CharacterMedia media = character.getCharacterMedia();
      embed.setImage(media.getMainRawUrl());
    } catch (CharacterMediaNotFoundException e) {
      embed.setImage("no_avatar.png");
    }

    return embed;
  }

  public static EmbedBuilder lgeMenuInviteGetInfosError(ModalInteraction event) {
    EmbedBuilder embed = Embeds.model();
    embed.setTitle(
        event.getMember().getEffectiveName() + ", nous n'avons pas trouver votre personnage.");
    embed.setDescription(
        "### <:lge_person:1099955042800373772> - Pourquoi ? \n > Afin de faciliter le fonctionnement interne tel que les events et les différentes fonctionnalités, nous devons recueillir vos informations via l'Api de Blizzard.");
    embed.addField("> Nouveau personnage ? ", event.getValue("player_name").getAsString() + "-"
        + event.getValue("realm_name")
        + " viens d'être créé ? L'Api de blizzard n'est donc pas encore disponible, il faudra donc patienter.",
        false);
    embed.addField("> Vennez-vous de migrer ? ",
        "Si vous vennez de migrer, c'est sûrement que l'api n'est pas à jour.", false);
    embed.addBlankField(false);
    embed.addField("> Solutions : ",
        "Vous pouvez contacter un Officier pour qu'il vous attribue manuellement le status d'invité pour accéder au discord.",
        false);
    return embed;
  }

  public static EmbedBuilder massMoovePlayerCommand(SlashCommandInteractionEvent event,
      String thumbnailName, List<Member> memberList) {
    EmbedBuilder embed = Embeds.model();
    embed.setTitle("Mass Moove Player");
    embed.setDescription(
        "Vous êtes sur le point de déplacer tous les joueurs de la guilde vers un autre channel vocal.");
    embed.addField("> Channel actuel :",
        event.getOption("channel_actual").getAsChannel().getAsMention(), true);
    embed.addField("> Channel de redirection :",
        event.getOption("channel_redirection").getAsChannel().getAsMention(), true);
    try {
      embed.addField("> Rôle :", event.getOption("role").getAsRole().getName(), true);
    } catch (Exception e) {
      embed.addField("> Rôle :", "Aucun", true);
    }
    try {
      StringBuilder str = new StringBuilder();
      memberList.forEach(m -> str.append(m.getAsMention() + ","));
      embed.addField("> Utilisateurs concernés :", str.toString(), false);
    } catch (Exception e) {
      embed.addField("> Utilisateurs concernés :", "Aucuns", false);
    }
    embed.setThumbnail("attachment://" + thumbnailName);
    return embed;
  }

}
