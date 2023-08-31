package com.radonn.axon.controllers.discord;

import com.radonn.axon.utils.FileUploadBuilderByPath;
import com.radonn.axon.views.discord.builder.Embeds;
import com.radonn.axon.views.discord.builder.Modals;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class ButtonCommand {

    public static void lgeMenu(ButtonInteractionEvent event) {
        try {
            switch (event.getComponentId()) {
            /// Entretien
                case "lge_menu_entretien":
                    event.getJDA().getGatewayPool().submit((Runnable) () -> {
                        event.replyModal(Modals.modEntretien(event.getUser())).queue();
                    });
                    break;

                case "lge_menu_entretien_charte":
                    event.getJDA().getGatewayPool().submit((Runnable) () -> {
                        try {
                            FileUploadBuilderByPath charte = new FileUploadBuilderByPath("charte.png", "src/main/resources/images/guild/icons/charte.png");
                            event.getHook().editOriginalEmbeds(Embeds.lgeMenuEntretienCharte(charte.getOutPutName()).build()).setAttachments(charte.getFileUpload()).queue();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    break;
                
                case "lge_menu_entretien_rules":
                    event.getJDA().getGatewayPool().submit((Runnable) () -> {
                        FileUploadBuilderByPath rules = new FileUploadBuilderByPath("rules.png", "src/main/resources/images/guild/icons/rules.png");
                        event.getHook().editOriginalEmbeds(Embeds.lgeMenuEntretienRules(rules.getOutPutName()).build()).setAttachments(rules.getFileUpload()).queue();
                    });
                    break;

                case "lge_menu_entretien_cgu":
                    event.getJDA().getGatewayPool().submit((Runnable) () -> {
                        FileUploadBuilderByPath cgu = new FileUploadBuilderByPath("cgu.png", "src/main/resources/images/guild/icons/cgu.png");
                        event.getHook().editOriginalEmbeds(Embeds.lgeMenuEntretienCGU(cgu.getOutPutName()).build()).setAttachments(cgu.getFileUpload()).queue();
                    });
                    break;
                
                case "lge_menu_entretien_retry":
                    event.getJDA().getGatewayPool().submit((Runnable) () -> {
                        event.getHook().editOriginalAttachments().queue();
                        event.getHook().deleteOriginal().queue();
                        event.replyModal(Modals.modEntretienRetry(event.getUser())).queue();
                    });
                    break;
                


            /// Invite
                case "lge_menu_invite":
                        event.getJDA().getGatewayPool().submit((Runnable) () -> {
                            event.replyModal(Modals.modInvite(event.getUser())).queue();
                        });
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
