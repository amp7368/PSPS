package apple.discord.psps.discord.util;

import java.util.function.Function;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.Nullable;
import org.slf4j.helpers.CheckReturnValue;

public interface CommandMessages {

    @Nullable
    default <T> T getOption(SlashCommandInteractionEvent event, String name, Function<OptionMapping, T> fn) {
        OptionMapping option = event.getOption(name);
        if (option == null) {
            this.error(event, "'%s' is required".formatted(name));
            return null;
        }
        return fn.apply(option);
    }

    default void error(SlashCommandInteractionEvent event, String msg) {
        event.replyEmbeds(error(msg)).queue();
    }

    @CheckReturnValue
    default MessageEmbed error(String msg) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Error");
        embed.setDescription(msg);
        embed.setColor(DiscordColors.ERROR);
        return embed.build();
    }

    default void success(SlashCommandInteractionEvent event, String msg) {
        event.replyEmbeds(success(msg)).queue();
    }

    @CheckReturnValue
    default MessageEmbed success(String msg) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Success");
        embed.setDescription(msg);
        embed.setColor(DiscordColors.SUCCESS);
        return embed.build();
    }

}
