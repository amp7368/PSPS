package apple.discord.psps.discord.command.base;

import apple.discord.psps.discord.DiscordConfig;
import apple.discord.psps.discord.util.CommandMessages;
import discord.util.dcf.slash.DCFSlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public abstract class DiscordCommand extends DCFSlashCommand implements CommandMessages {

    @Override
    public final void onCommand(SlashCommandInteractionEvent event) {
        if (DiscordConfig.get().isGuildAllowed(event.getGuild())) {
            this.doCommand(event);
            return;
        }
        event.reply("No commands in this discord servers D:<").queue();
    }

    protected abstract void doCommand(SlashCommandInteractionEvent event);
}
