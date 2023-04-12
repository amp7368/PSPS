package apple.discord.psps.discord.command.help;

import apple.discord.psps.discord.command.base.DiscordCommand;
import discord.util.dcf.gui.base.gui.DCFGui;
import java.util.List;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class CommandHelpAdmin extends DiscordCommand {

    private static final List<MessageEmbed> pages = List.of(createHomePage());

    private static MessageEmbed createHomePage() {
        EmbedBuilder embed = new EmbedBuilder();
        embed.addField("/code [code]", "Set the new server code", false);
        embed.addField("/purchase [store_item] [discord_name|roblox_name] (store_item_count=1)", "Set the new server code", false);
        embed.addField("/debug", "Check if all channels are accessible", false);
        embed.addField("/config channel [type] [@channel]", "Set output channels for the bot", false);
        return embed.build();
    }

    @Override
    public SlashCommandData getData() {
        return Commands.slash("help_admin", "Show a list of commands")
            .setDefaultPermissions(DefaultMemberPermissions.DISABLED);
    }


    @Override
    protected void doCommand(SlashCommandInteractionEvent event) {
        DCFGui gui = new DCFGui(dcf, event::reply);
        for (MessageEmbed page : pages)
            gui.addPage(new HelpPage(gui, page));
        gui.send();
    }

}
