package com.radonn.axon.controllers.discord;

import net.dv8tion.jda.api.interactions.modals.ModalInteraction;

import com.radonn.axon.AxonApplication;
import com.radonn.axon.exceptions.CharacterNotFoundException;
import com.radonn.axon.models.wow.models.Character;
import com.radonn.axon.views.discord.builder.*;

public class ModalCommand {

    public static void lgeMenu(ModalInteraction event) {
        switch (event.getModalId()) {
            case "lge_menu_entretien":
                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                    try  {
                        Character character = AxonApplication.BnetCtrl.getCharacter("hyjal", event.getValue("player_name").getAsString());
                        event.replyEmbeds(Embeds.lgeMenuEntretienGetInfos(event, character).build()).queue();
                    } catch (CharacterNotFoundException e) {
                        event.replyEmbeds(Embeds.lgeMenuEntretienGetInfosError(event).build()).addActionRow(ItemComponents.lgeMenuError()).queue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                break;
            case "lge_menu_invite":
                event.getJDA().getGatewayPool().submit((Runnable) () -> {
                    try {
                        Character character = AxonApplication.BnetCtrl.getCharacter(event.getValue("realm_name").getAsString(), event.getValue("player_name").getAsString());
                        event.replyEmbeds(Embeds.lgeMenuEntretienGetInfos(event, character).build()).queue();
                    } catch (CharacterNotFoundException e) {
                        event.replyEmbeds(Embeds.lgeMenuEntretienGetInfosError(event).build()).addActionRow(ItemComponents.lgeMenuError()).queue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                break;
            default:
                break;
        }
    }
    
}
