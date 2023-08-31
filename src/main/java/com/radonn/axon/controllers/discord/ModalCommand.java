package com.radonn.axon.controllers.discord;

import net.dv8tion.jda.api.interactions.modals.ModalInteraction;

import com.radonn.axon.AxonApplication;
import com.radonn.axon.exceptions.CharacterMediaNotFoundException;
import com.radonn.axon.exceptions.CharacterNotFoundException;
import com.radonn.axon.models.wow.Character;
import com.radonn.axon.utils.FileUploadBuilderByPath;
import com.radonn.axon.views.discord.builder.*;

public class ModalCommand {

    public static void lgeMenu(ModalInteraction event) {

        FileUploadBuilderByPath loading = new FileUploadBuilderByPath("loading.gif", "src/main/resources/images/guild/animation/loading.gif");
        event.deferReply().setEphemeral(true).setEmbeds(Embeds.loading().build()).setFiles(loading.getFileUpload()).queue();

        switch (event.getModalId()) {
            case "lge_menu_entretien":
                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                    Character character = null;
                    try {
                        character = AxonApplication.BnetCtrl.getCharacter("hyjal", event.getValue("player_name").getAsString());
                    } catch (CharacterNotFoundException e) {
                        event.getHook().editOriginalEmbeds(Embeds.lgeMenuEntretienGetInfosError(event).build()).setActionRow(ItemComponents.lgeMenuError()).setAttachments().queue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (character == null) return;
                    try {
                        event.getHook().editOriginalEmbeds(Embeds.lgeMenuEntretienGetInfos(event, character, character.getCharacterMedia()).build()).setAttachments().setActionRow(ItemComponents.lgeMenuEntretienGetInfos()).queue();
                    } catch (CharacterMediaNotFoundException e) {
                        FileUploadBuilderByPath noAvatar = new FileUploadBuilderByPath("no_avatar.png", "src/main/resources/images/guild/characterMedia/no_avatar.png");
                        event.getHook().editOriginalEmbeds(Embeds.lgeMenuEntretienGetInfos(event, character, noAvatar.getOutPutName()).build()).setAttachments(noAvatar.getFileUpload()).setActionRow(ItemComponents.lgeMenuEntretienGetInfos()).queue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                break;
            
            case "lge_menu_entretien_retry":
                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                    Character character = null;
                    try {
                        character = AxonApplication.BnetCtrl.getCharacter("hyjal", event.getValue("player_name").getAsString());
                    } catch (CharacterNotFoundException e) {
                        event.getHook().editOriginalEmbeds(Embeds.lgeMenuEntretienGetInfosError(event).build()).setActionRow(ItemComponents.lgeMenuError()).setAttachments().queue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (character == null) return;
                    try {
                        event.getHook().editOriginalEmbeds(Embeds.lgeMenuEntretienGetInfos(event, character, character.getCharacterMedia()).build()).setAttachments().setActionRow(ItemComponents.lgeMenuEntretienGetInfos()).queue();
                    } catch (CharacterMediaNotFoundException e) {
                        FileUploadBuilderByPath noAvatar = new FileUploadBuilderByPath("no_avatar.png", "src/main/resources/images/guild/characterMedia/no_avatar.png");
                        event.getHook().editOriginalEmbeds(Embeds.lgeMenuEntretienGetInfos(event, character, noAvatar.getOutPutName()).build()).setAttachments(noAvatar.getFileUpload()).setActionRow(ItemComponents.lgeMenuEntretienGetInfos()).queue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                break;

            case "lge_menu_invite": 
                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                    /*try {
                        Character character = AxonApplication.BnetCtrl.getCharacter(event.getValue("realm_name").getAsString(), event.getValue("player_name").getAsString());
                        event.getHook().editOriginalEmbeds(Embeds.lgeMenuEntretienGetInfos(event, character).build()).setAttachments().setActionRow(ItemComponents.lgeMenuInviteGetInfos()).queue();
                    } catch (CharacterNotFoundException e) {
                        event.getHook().editOriginalEmbeds(Embeds.lgeMenuEntretienGetInfosError(event).build()).setActionRow(ItemComponents.lgeMenuError()).setAttachments().queue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                });
                break;
            default:
                break;
        }
    }
    
}
