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
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
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
            commands.addCommands(Commands.slash("move_all", "Déplace tout les joueurs d'un canal vers un autre")
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.VOICE_MOVE_OTHERS))
                    .addOption(OptionType.CHANNEL, "channel_actual",
                            "Le canal ou les joueurs d'ou proviennent les joueurs.", true)
                    .addOption(OptionType.CHANNEL, "channel_redirection", "Le canal ou les joueurs seront déplacés.",
                            true)
                    .addOption(OptionType.ROLE, "role", "déplace uniquement les joueurs possédant ce role.", false,
                            false));
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
        logger.info("Slash command received" + event.getName());
        if (event.getGuild() == null)
            return;
        if (event.getName().equals("lge_menu"))
            SlashCommand.lgeMenu(event);
        if (event.getName().equals("move_all"))
            SlashCommand.massMoovePlayerCommand(event);
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        logger.info("Button interaction received" + event.getComponentId());
        if (event.getComponentId().startsWith("lge_menu"))
            ButtonCommand.lgeMenu(event);
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        logger.info("Modal interaction received" + event.getModalId());
        if (event.getModalId().startsWith("lge_menu"))
            ModalCommand.lgeMenu(event);
    }
}
