package com.radonn.axon.discord.controllers;

import com.radonn.axon.discord.Builder;
import com.radonn.axon.utils.FileUploadBuilderByPath;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SlashCommand {

    public static void lgeMenu(SlashCommandInteractionEvent event) {
        event.getJDA().getGatewayPool().submit((Runnable) () -> {

            FileUploadBuilderByPath image = new FileUploadBuilderByPath("image.gif", "src/main/resources/images/guild/animation/opener.gif");
            FileUploadBuilderByPath thumbnail = new FileUploadBuilderByPath("thumbnail.png", "src/main/resources/images/guild/logo/logo.png");
            
            event.deferReply(false).queue();
            event.getHook()
                .editOriginalEmbeds(Builder.Embeds.lgeMenu(image.getOutPutName(), thumbnail.getOutPutName()).build())
                .setActionRow(Builder.ItemComponents.lgeMenu())
                .setFiles(image.getFileUpload(), thumbnail.getFileUpload())
                .queue();
        });
    }
}
