package apple.discord.psps.discord.command.purchase;

import discord.util.dcf.slash.DCFSlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class CommandPurchase extends DCFSlashCommand {

    @Override
    public SlashCommandData getData() {
        OptionData discordName = new OptionData(OptionType.USER, "discord_name", "The player's discord", false);
        OptionData robloxName = new OptionData(OptionType.STRING, "roblox_name", "The player's username", false);
        OptionData storeItem = new OptionData(OptionType.STRING, "store_item", "The predefined purchase", true);
        OptionData storeItemCount = new OptionData(OptionType.NUMBER, "store_item_count",
            "The number of store_item's that the player purchased (default 1)", false);
        return Commands.slash("purchase", "Mark that a player has purchased access")
            .addOptions(discordName, robloxName, storeItem, storeItemCount)
            .setDefaultPermissions(DefaultMemberPermissions.DISABLED);
    }

    @Override
    public void onCommand(SlashCommandInteractionEvent slashCommandInteractionEvent) {

    }
}
