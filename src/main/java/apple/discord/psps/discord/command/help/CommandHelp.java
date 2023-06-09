package apple.discord.psps.discord.command.help;

import apple.discord.psps.discord.command.base.DiscordCommand;
import discord.util.dcf.gui.base.gui.DCFGui;
import java.util.List;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class CommandHelp extends DiscordCommand {

    private static final List<MessageEmbed> pages = List.of(createHomePage());

    private static MessageEmbed createHomePage() {
        EmbedBuilder embed = new EmbedBuilder();
        embed.addField("hi", "you", false);
        return embed.build();
    }

    @Override
    public SlashCommandData getData() {
        return Commands.slash("help", "Show a list of commands");
    }


    @Override
    public void doCommand(SlashCommandInteractionEvent event) {
        DCFGui gui = new DCFGui(dcf, event::reply);
        for (MessageEmbed page : pages)
            gui.addPage(new HelpPage(gui, page));
        gui.send();
    }
}
