package apple.discord.psps.discord.command.config;

import apple.discord.psps.discord.command.base.DiscordCommand;
import apple.discord.psps.discord.util.CommandMessages;
import apple.discord.psps.discord.util.channel.ChannelOutput;
import apple.discord.psps.discord.util.channel.ChannelOutputType;
import discord.util.dcf.slash.DCFSlashSubCommand;
import java.util.List;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.GuildChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.Nullable;

public class CommandConfig extends DiscordCommand {

    @Override
    public SlashCommandData getData() {
        return Commands.slash("config", "Manage configuration settings for the bot's actions")
            .setDefaultPermissions(DefaultMemberPermissions.DISABLED);
    }

    @Override
    public List<DCFSlashSubCommand> getSubCommands() {
        return List.of(new CommandChannelConfig());
    }

    @Override
    public void doCommand(SlashCommandInteractionEvent event) {
    }

    private static class CommandChannelConfig extends DCFSlashSubCommand implements CommandMessages {

        @Override
        public SubcommandData getData() {
            OptionData output = new OptionData(OptionType.STRING, "type", "The type of output", true)
                .addChoices(ChannelOutputType.CHOICES);
            return new SubcommandData("channel", "Set output channels for the bot")
                .addOptions(output)
                .addOption(OptionType.CHANNEL, "channel", "The new channel to output to", true);
        }

        @Override
        public void onCommand(SlashCommandInteractionEvent event) {
            @Nullable String type = getOption(event, "type", OptionMapping::getAsString);
            GuildChannelUnion channel = getOption(event, "channel", OptionMapping::getAsChannel);
            if (type == null || channel == null) return;
            ChannelOutput output = ChannelOutputType.NAMES_TO_OUTPUT.get(type);
            if (output == null) {
                error(event, "There is no channel output '%s'".formatted(type));
                return;
            }
            if (channel instanceof TextChannel) {
                output.setId(channel.getIdLong());
                success(event, "Updated: %s output prints to %s".formatted(type, channel.getAsMention()));
            } else
                error(event, "'%s' is not a TextChannel".formatted(channel.getAsMention()));
        }
    }
}
