package com.radonn.axon.services.discord;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.radonn.axon.controllers.discord.ButtonCommand;
import com.radonn.axon.controllers.discord.ModalCommand;
import com.radonn.axon.controllers.discord.SlashCommand;

import org.slf4j.Logger;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

@Service
public class DiscordBotManager extends ListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(DiscordBotManager.class);

    @Value("${discord.api.bot-token}")
    private String discordBotToken;

    public JDA jda;

    public void init() {
        try {
            this.jda = JDABuilder.createDefault(discordBotToken)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS,
                            GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_PRESENCES,
                            GatewayIntent.GUILD_VOICE_STATES)
                    .setMemberCachePolicy(MemberCachePolicy.ALL)
                    .addEventListeners(new ListenerAdapter() {
                        @Override
                        public void onException(ExceptionEvent event) {
                            Throwable throwable = event.getCause();
                            logger.error("Erreur non traitée détectée!", throwable);
                        }
                    })
                    .addEventListeners(this)
                    .build();

            CommandListUpdateAction commands = jda.updateCommands();
            commands.addCommands(Commands.slash("lge_menu", "Démarre le programme mère"));
            commands.queue();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Erreur lors de l'initialisation du bot Discord.", e);
        }
    }

    public JDA getJda() {
        return this.jda;
    }

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        //logger.info("Slash command received");
        if (event.getGuild() == null) return;
        if (event.getName().equals("lge_menu")) SlashCommand.lgeMenu(event);
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) { 
        if (event.getComponentId().startsWith("lge_menu")) ButtonCommand.lgeMenu(event);
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getModalId().startsWith("lge_menu")) ModalCommand.lgeMenu(event);
    }
}
