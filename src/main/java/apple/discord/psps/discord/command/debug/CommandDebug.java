package apple.discord.psps.discord.command.debug;

import apple.discord.psps.discord.command.base.DiscordCommand;
import apple.discord.psps.discord.util.channel.ChannelOutputType;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class CommandDebug extends DiscordCommand {

    @Override
    public SlashCommandData getData() {
        return Commands.slash("debug", "Check if all channels are accessible");
    }

    @Override
    public void doCommand(SlashCommandInteractionEvent event) {
        for (ChannelOutputType output : ChannelOutputType.values()) {
            TextChannel channel = output.getTextChannel();
            if (channel != null)
                channel.sendMessage("Channel is set to receive from " + output.getOutput().getName()).queue();
        }
        event.reply("Test").queue();
    }
}
