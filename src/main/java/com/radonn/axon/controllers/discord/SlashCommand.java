package com.radonn.axon.controllers.discord;

import java.util.List;

import com.radonn.axon.utils.FileUploadBuilder;
import com.radonn.axon.views.discord.builder.*;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.exceptions.RateLimitedException;

public class SlashCommand {

    public static void loading(SlashCommandInteractionEvent event) {
        FileUploadBuilder loading = FileUploadBuilder.fromPath("loading.gif",
                "src/main/resources/images/guild/animation/loading.gif");

        event.deferReply().setEphemeral(false).setEmbeds(Embeds.loading().build()).setFiles(loading.getFileUpload())
                .queue();
    }

    public static void lgeMenu(SlashCommandInteractionEvent event) {
        loading(event);
        event.getJDA().getGatewayPool().submit((Runnable) () -> {

            FileUploadBuilder image = FileUploadBuilder.fromPath("image.gif",
                    "src/main/resources/images/guild/animation/opener.gif");

            FileUploadBuilder thumbnail = FileUploadBuilder.fromPath("thumbnail.png",
                    "src/main/resources/images/guild/logo/logo.png");

            event.getHook()
                    .editOriginalEmbeds(Embeds.lgeMenu(image.getOutPutName(), thumbnail.getOutPutName()).build())
                    .setActionRow(ItemComponents.lgeMenu(event))
                    .setFiles(image.getFileUpload(), thumbnail.getFileUpload())
                    .queue();
        });
    }

    public static void massMoovePlayerCommand(SlashCommandInteractionEvent event) {
        loading(event);

        AudioChannel channel = event.getOption("channel_redirection").getAsChannel().asAudioChannel();
        List<Member> members = event.getGuild()
                .getVoiceChannelById(event.getOption("channel_actual").getAsChannel().getId()).getMembers();

        if (event.getOption("role") != null) {
            Role role = event.getOption("role").getAsRole();
            event.getJDA().getGatewayPool().submit(() -> {
                members.removeIf(member -> !member.getRoles().contains(role));
            });
        }

        event.getJDA().getGatewayPool().submit(() -> {
            FileUploadBuilder thumbnail = FileUploadBuilder.fromPath("command_trigger.png",
                    "src/main/resources/images/guild/icons/command_trigger.png");
            event.getHook()
                    .editOriginalEmbeds(
                            Embeds.massMoovePlayerCommand(event, thumbnail.getOutPutName(), members).build())
                    .setAttachments(thumbnail.getFileUpload()).queue();
        });

        for (Member member : members) {
            event.getJDA().getGatewayPool().submit(() -> {
                try {
                    movePlayer(member, channel, event);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void movePlayer(Member member, AudioChannel channel, SlashCommandInteractionEvent event)
            throws InterruptedException {
        try {
            Thread.sleep(100);
            event.getGuild().moveVoiceMember(member, channel).complete(false);
        } catch (RateLimitedException e) {
            Thread.sleep(500);
            movePlayer(member, channel, event);
        }
    }
}
