package com.radonn.axon.controllers.discord;

import java.util.ArrayList;
import com.radonn.axon.AxonApplication;
import com.radonn.axon.exceptions.CharacterNotFoundException;
import com.radonn.axon.models.userLge.GuildCharacter;
import com.radonn.axon.models.wow.Character;
import com.radonn.axon.utils.FileUploadBuilder;
import com.radonn.axon.views.discord.builder.Embeds;
import com.radonn.axon.views.discord.builder.ItemComponents;
import com.radonn.axon.views.discord.builder.Modals;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class ButtonCommand {

        public static void lgeMenu(ButtonInteractionEvent event) {
                switch (event.getComponentId()) {
                        /// Entretien
                        case "lge_menu_entretien":
                                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                                        AxonApplication.UserLgeCtrl.deleteCharacterByDiscordID(
                                                        event.getUser().getIdLong());
                                        event.replyModal(Modals.modEntretien(event.getUser()))
                                                        .queue();
                                });
                                break;

                        case "lge_menu_entretien_retry":
                                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                                        AxonApplication.UserLgeCtrl.deleteCharacterByDiscordID(
                                                        event.getUser().getIdLong());
                                        event.getHook().deleteOriginal().queue();
                                        event.replyModal(Modals.modEntretienRetry(event.getUser()))
                                                        .queue();
                                });
                                break;

                        case "lge_menu_entretien_charte":
                                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                                        FileUploadBuilder charte = FileUploadBuilder.fromPath(
                                                        "charte.png",
                                                        "src/main/resources/images/guild/icons/charte.png");
                                        event.editMessageEmbeds(Embeds
                                                        .lgeMenuEntretienCharte(
                                                                        charte.getOutPutName())
                                                        .build())
                                                        .setAttachments(charte.getFileUpload())
                                                        .setActionRow(ItemComponents
                                                                        .lgeMenuEntretienCharte(
                                                                                        event))
                                                        .queue();
                                });
                                break;

                        case "lge_menu_entretien_rules":
                                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                                        FileUploadBuilder rules = FileUploadBuilder.fromPath(
                                                        "rules.png",
                                                        "src/main/resources/images/guild/icons/rules.png");
                                        event.editMessageEmbeds(Embeds
                                                        .lgeMenuEntretienRules(
                                                                        rules.getOutPutName())
                                                        .build())
                                                        .setAttachments(rules.getFileUpload())
                                                        .setActionRow(ItemComponents
                                                                        .lgeMenuEntretienRules(
                                                                                        event))
                                                        .queue();
                                });
                                break;

                        case "lge_menu_entretien_cgu":
                                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                                        try {
                                                FileUploadBuilder cgu = FileUploadBuilder.fromPath(
                                                                "cgu.png",
                                                                "src/main/resources/images/guild/icons/cgu.png");
                                                event.editMessageEmbeds(Embeds
                                                                .lgeMenuEntretienCGU(
                                                                                cgu.getOutPutName())
                                                                .build())
                                                                .setAttachments(cgu.getFileUpload())
                                                                .setActionRow(ItemComponents
                                                                                .lgeMenuEntretienCGU(
                                                                                                event))
                                                                .queue();
                                        } catch (Exception e) {
                                                e.printStackTrace();
                                        }
                                });
                                break;

                        case "lge_menu_entretien_global_finish":
                                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                                        FileUploadBuilder cgu = FileUploadBuilder.fromPath(
                                                        "valid.png",
                                                        "src/main/resources/images/guild/icons/valid.png");
                                        event.editMessageEmbeds(Embeds
                                                        .lgeMenuEntretienFinish(cgu.getOutPutName())
                                                        .build())
                                                        .setAttachments(cgu.getFileUpload())
                                                        .setActionRow(ItemComponents
                                                                        .lgeMenuEntretienFinish(
                                                                                        event))
                                                        .setAttachments().queue();
                                });
                                break;

                        case "lge_menu_entretien_complete":
                                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                                        try {
                                                FileUploadBuilder loading = FileUploadBuilder
                                                                .fromPath("loading.gif",
                                                                                "src/main/resources/images/guild/animation/loading.gif");
                                                event.editMessageEmbeds()
                                                                .setEmbeds(Embeds.loading().build())
                                                                .setFiles(loading.getFileUpload())
                                                                .queue();

                                                GuildCharacter user = AxonApplication.UserLgeCtrl
                                                                .getCharacterMainByDiscordId(event
                                                                                .getUser()
                                                                                .getIdLong());
                                                Character character = AxonApplication.BnetCtrl
                                                                .getCharacter("hyjal", user
                                                                                .getName()
                                                                                .toLowerCase());
                                                // event.getMember().modifyNickname(character.getName()).queue();
                                                ArrayList<Role> roles = new ArrayList<Role>();
                                                // roles.add(event.getGuild().getRolesByName("Novice",
                                                // true).size() != 0
                                                // ?
                                                // event.getGuild().getRolesByName("Novice",
                                                // true).get(0) :
                                                // event.getGuild().createRole().setName("Novice").complete().getJDA().getRolesByName("Novice",
                                                // true).get(0));
                                                // roles.add(event.getGuild().getRolesByName("----
                                                // Bloc Principal ----",
                                                // true).size() != 0 ?
                                                // event.getGuild().getRolesByName("---- Bloc
                                                // Principal
                                                // ----", true).get(0) :
                                                // event.getGuild().createRole().setName("----
                                                // Bloc
                                                // Principal
                                                // ----").complete().getJDA().getRolesByName("----
                                                // Bloc
                                                // Principal
                                                // ----", true).get(0));
                                                roles.add(event.getGuild()
                                                                .getRolesByName(character.getRace()
                                                                                .getName(), true)
                                                                .size() != 0 ? event
                                                                                .getGuild()
                                                                                .getRolesByName(character
                                                                                                .getRace()
                                                                                                .getName(),
                                                                                                true)
                                                                                .get(0)
                                                                                : event.getGuild()
                                                                                                .createRole()
                                                                                                .setName(character
                                                                                                                .getRace()
                                                                                                                .getName())
                                                                                                .complete()
                                                                                                .getJDA()
                                                                                                .getRolesByName(character
                                                                                                                .getRace()
                                                                                                                .getName(),
                                                                                                                true)
                                                                                                .get(0));
                                                roles.add(event.getGuild()
                                                                .getRolesByName(character
                                                                                .getCharacterClass()
                                                                                .getName(), true)
                                                                .size() != 0 ? event
                                                                                .getGuild()
                                                                                .getRolesByName(character
                                                                                                .getCharacterClass()
                                                                                                .getName(),
                                                                                                true)
                                                                                .get(0)
                                                                                : event.getGuild()
                                                                                                .createRole()
                                                                                                .setName(character
                                                                                                                .getCharacterClass()
                                                                                                                .getName())
                                                                                                .complete()
                                                                                                .getJDA()
                                                                                                .getRolesByName(character
                                                                                                                .getCharacterClass()
                                                                                                                .getName(),
                                                                                                                true)
                                                                                                .get(0));
                                                roles.add(event.getGuild().getRolesByName(
                                                                character.getActiveSpec().getName(),
                                                                true).size() != 0 ? event
                                                                                .getGuild()
                                                                                .getRolesByName(character
                                                                                                .getActiveSpec()
                                                                                                .getName(),
                                                                                                true)
                                                                                .get(0)
                                                                                : event.getGuild()
                                                                                                .createRole()
                                                                                                .setName(character
                                                                                                                .getActiveSpec()
                                                                                                                .getName())
                                                                                                .complete()
                                                                                                .getJDA()
                                                                                                .getRolesByName(character
                                                                                                                .getActiveSpec()
                                                                                                                .getName(),
                                                                                                                true)
                                                                                                .get(0));
                                                roles.add(event.getGuild().getRolesByName(character
                                                                .getPlayableSpecialization()
                                                                .getRole().getName(), true)
                                                                .size() != 0 ? event
                                                                                .getGuild()
                                                                                .getRolesByName(character
                                                                                                .getPlayableSpecialization()
                                                                                                .getRole()
                                                                                                .getName(),
                                                                                                true)
                                                                                .get(0)
                                                                                : event.getGuild()
                                                                                                .createRole()
                                                                                                .setName(character
                                                                                                                .getPlayableSpecialization()
                                                                                                                .getRole()
                                                                                                                .getName())
                                                                                                .complete()
                                                                                                .getJDA()
                                                                                                .getRolesByName(character
                                                                                                                .getPlayableSpecialization()
                                                                                                                .getRole()
                                                                                                                .getName(),
                                                                                                                true)
                                                                                                .get(0));
                                                roles.add(event.getGuild().getRolesByName(
                                                                character.getFaction().getName(),
                                                                true).size() != 0 ? event
                                                                                .getGuild()
                                                                                .getRolesByName(character
                                                                                                .getFaction()
                                                                                                .getName(),
                                                                                                true)
                                                                                .get(0)
                                                                                : event.getGuild()
                                                                                                .createRole()
                                                                                                .setName(character
                                                                                                                .getFaction()
                                                                                                                .getName())
                                                                                                .complete()
                                                                                                .getJDA()
                                                                                                .getRolesByName(character
                                                                                                                .getFaction()
                                                                                                                .getName(),
                                                                                                                true)
                                                                                                .get(0));
                                                roles.forEach(role -> {
                                                        event.getGuild().addRoleToMember(
                                                                        event.getMember(), role)
                                                                        .queue();
                                                });
                                                event.getHook().deleteOriginal().queue();
                                                event.getGuild().removeRoleFromMember(
                                                                event.getMember(),
                                                                event.getGuild().getRolesByName(
                                                                                "0 - arrivé", true)
                                                                                .get(0))
                                                                .queue();
                                                event.getGuild().getTextChannelById(
                                                                "1057477730830143589")
                                                                .sendMessageEmbeds(Embeds
                                                                                .lgeMenuEntretienCallBack(
                                                                                                event.getJDA(),
                                                                                                AxonApplication.UserLgeCtrl
                                                                                                                .getUserById(event
                                                                                                                                .getUser()
                                                                                                                                .getIdLong()))
                                                                                .build())
                                                                .addActionRow(ItemComponents
                                                                                .lgeMenuEntretienValid(
                                                                                                event))
                                                                .queue();
                                        } catch (CharacterNotFoundException e) {
                                                e.printStackTrace();
                                        } catch (Exception e) {
                                                e.printStackTrace();
                                        }
                                });
                                break;

                        case "lge_menu_entretien_complete_integration":
                                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                                        try {

                                                GuildCharacter user = AxonApplication.UserLgeCtrl
                                                                .getCharacterMainByDiscordId(
                                                                                event.getIdLong());
                                                Character character = AxonApplication.BnetCtrl
                                                                .getCharacter("hyjal", user
                                                                                .getName()
                                                                                .toLowerCase());
                                                event.getMember()
                                                                .modifyNickname(character.getName())
                                                                .queue();
                                                ArrayList<Role> roles = new ArrayList<Role>();
                                                // roles.add(event.getGuild().getRolesByName("Novice",
                                                // true).size() != 0
                                                // ?
                                                // event.getGuild().getRolesByName("Novice",
                                                // true).get(0) :
                                                // event.getGuild().createRole().setName("Novice").complete().getJDA().getRolesByName("Novice",
                                                // true).get(0));
                                                // roles.add(event.getGuild().getRolesByName("----
                                                // Bloc Principal ----",
                                                // true).size() != 0 ?
                                                // event.getGuild().getRolesByName("---- Bloc
                                                // Principal
                                                // ----", true).get(0) :
                                                // event.getGuild().createRole().setName("----
                                                // Bloc
                                                // Principal
                                                // ----").complete().getJDA().getRolesByName("----
                                                // Bloc
                                                // Principal
                                                // ----", true).get(0));
                                                roles.add(event.getGuild()
                                                                .getRolesByName(character.getRace()
                                                                                .getName(), true)
                                                                .size() != 0 ? event
                                                                                .getGuild()
                                                                                .getRolesByName(character
                                                                                                .getRace()
                                                                                                .getName(),
                                                                                                true)
                                                                                .get(0)
                                                                                : event.getGuild()
                                                                                                .createRole()
                                                                                                .setName(character
                                                                                                                .getRace()
                                                                                                                .getName())
                                                                                                .complete()
                                                                                                .getJDA()
                                                                                                .getRolesByName(character
                                                                                                                .getRace()
                                                                                                                .getName(),
                                                                                                                true)
                                                                                                .get(0));
                                                roles.add(event.getGuild()
                                                                .getRolesByName(character
                                                                                .getCharacterClass()
                                                                                .getName(), true)
                                                                .size() != 0 ? event
                                                                                .getGuild()
                                                                                .getRolesByName(character
                                                                                                .getCharacterClass()
                                                                                                .getName(),
                                                                                                true)
                                                                                .get(0)
                                                                                : event.getGuild()
                                                                                                .createRole()
                                                                                                .setName(character
                                                                                                                .getCharacterClass()
                                                                                                                .getName())
                                                                                                .complete()
                                                                                                .getJDA()
                                                                                                .getRolesByName(character
                                                                                                                .getCharacterClass()
                                                                                                                .getName(),
                                                                                                                true)
                                                                                                .get(0));
                                                roles.add(event.getGuild().getRolesByName(
                                                                character.getActiveSpec().getName(),
                                                                true).size() != 0 ? event
                                                                                .getGuild()
                                                                                .getRolesByName(character
                                                                                                .getActiveSpec()
                                                                                                .getName(),
                                                                                                true)
                                                                                .get(0)
                                                                                : event.getGuild()
                                                                                                .createRole()
                                                                                                .setName(character
                                                                                                                .getActiveSpec()
                                                                                                                .getName())
                                                                                                .complete()
                                                                                                .getJDA()
                                                                                                .getRolesByName(character
                                                                                                                .getActiveSpec()
                                                                                                                .getName(),
                                                                                                                true)
                                                                                                .get(0));
                                                roles.add(event.getGuild().getRolesByName(character
                                                                .getPlayableSpecialization()
                                                                .getRole().getName(), true)
                                                                .size() != 0 ? event
                                                                                .getGuild()
                                                                                .getRolesByName(character
                                                                                                .getPlayableSpecialization()
                                                                                                .getRole()
                                                                                                .getName(),
                                                                                                true)
                                                                                .get(0)
                                                                                : event.getGuild()
                                                                                                .createRole()
                                                                                                .setName(character
                                                                                                                .getPlayableSpecialization()
                                                                                                                .getRole()
                                                                                                                .getName())
                                                                                                .complete()
                                                                                                .getJDA()
                                                                                                .getRolesByName(character
                                                                                                                .getPlayableSpecialization()
                                                                                                                .getRole()
                                                                                                                .getName(),
                                                                                                                true)
                                                                                                .get(0));
                                                roles.add(event.getGuild().getRolesByName(
                                                                character.getFaction().getName(),
                                                                true).size() != 0 ? event
                                                                                .getGuild()
                                                                                .getRolesByName(character
                                                                                                .getFaction()
                                                                                                .getName(),
                                                                                                true)
                                                                                .get(0)
                                                                                : event.getGuild()
                                                                                                .createRole()
                                                                                                .setName(character
                                                                                                                .getFaction()
                                                                                                                .getName())
                                                                                                .complete()
                                                                                                .getJDA()
                                                                                                .getRolesByName(character
                                                                                                                .getFaction()
                                                                                                                .getName(),
                                                                                                                true)
                                                                                                .get(0));
                                                roles.forEach(role -> {
                                                        event.getGuild().addRoleToMember(
                                                                        event.getMember(), role)
                                                                        .queue();
                                                });
                                                event.getHook().deleteOriginal().queue();
                                                event.getGuild().removeRoleFromMember(
                                                                event.getMember(),
                                                                event.getGuild().getRolesByName(
                                                                                "0 - arrivé", true)
                                                                                .get(0))
                                                                .queue();

                                        } catch (CharacterNotFoundException e) {
                                                e.printStackTrace();
                                        } catch (Exception e) {
                                                e.printStackTrace();
                                        }
                                });
                                break;

                        case "lge_menu_entretien_valid":
                                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                                        event.getHook().editOriginalEmbeds(Embeds
                                                        .lgeMenuEntretienAccept(event.getMessage()
                                                                        .getEmbeds().get(0),
                                                                        event.getUser().getId())
                                                        .build()).queue();
                                });


                                /// Invite
                        case "lge_menu_invite":
                                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                                        event.replyModal(Modals.modInvite(event.getUser())).queue();
                                });
                                break;
                        default:
                                break;
                }
        }

}
