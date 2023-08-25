package com.radonn.axon.controllers.discord;

import com.radonn.axon.utils.FileUploadBuilderByPath;
import com.radonn.axon.views.discord.builder.*;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SlashCommand {

    public static void lgeMenu(SlashCommandInteractionEvent event) {

        FileUploadBuilderByPath loding = new FileUploadBuilderByPath("loading.gif", "src/main/resources/images/guild/animation/loading.gif");

        event.deferReply().setEphemeral(true).setEmbeds(Embeds.loading().build()).setFiles(loding.getFileUpload()).queue();

        event.getJDA().getGatewayPool().submit((Runnable) () -> {

            FileUploadBuilderByPath image = new FileUploadBuilderByPath("image.gif", "src/main/resources/images/guild/animation/opener.gif");
            FileUploadBuilderByPath thumbnail = new FileUploadBuilderByPath("thumbnail.png", "src/main/resources/images/guild/logo/logo.png");
            
            event.getHook()
                .editOriginalEmbeds(Embeds.lgeMenu(image.getOutPutName(), thumbnail.getOutPutName()).build())
                .setActionRow(ItemComponents.lgeMenu())
                .setFiles(image.getFileUpload(), thumbnail.getFileUpload())
                .queue();
        });
    }
}
