package apple.discord.psps.discord.command.code;

import apple.discord.psps.database.passcode.DPasscode;
import apple.discord.psps.database.passcode.PasscodeStorage;
import apple.discord.psps.discord.server.ServerService;
import apple.discord.psps.discord.server.ServerState;
import apple.discord.psps.discord.util.CommandMessages;
import discord.util.dcf.slash.DCFSlashCommand;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.Nullable;

public class CommandPasscode extends DCFSlashCommand implements CommandMessages {

    @Override
    public SlashCommandData getData() {
        return Commands.slash("code", "none")
            .addOption(OptionType.STRING, "code", "The new code to login to the server", true)
            .setDefaultPermissions(DefaultMemberPermissions.DISABLED);
    }

    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        PasscodeStorage.revokeAll();
        @Nullable String code = this.getOption(event, "code", OptionMapping::getAsString);
        if (code == null) return;
        ServerState.get().setLastPasscodeSent(false);
        DPasscode passcode = PasscodeStorage.create(code);

        MessageEmbed message = this.success("Revoked all earlier codes and saved code '%s'".formatted(code));
        event.replyEmbeds(message)
            .setEphemeral(true)
            .queue((h) -> ServerService.sendPasscode(passcode));

    }
}
