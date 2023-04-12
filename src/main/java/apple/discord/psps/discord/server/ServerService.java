package apple.discord.psps.discord.server;

import apple.discord.psps.database.passcode.DPasscode;
import apple.discord.psps.database.passcode.PasscodeStorage;
import apple.discord.psps.discord.DiscordModule;
import apple.discord.psps.discord.util.DiscordColors;
import apple.discord.psps.discord.util.channel.ChannelOutputType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class ServerService {

    public static void load() {
        if (ServerState.get().getLastPasscodeSent()) return;
        DPasscode passcode = PasscodeStorage.findLastPasscode();
        if (passcode != null) {
            sendPasscode(passcode);
        }
    }

    public static void sendPasscode(DPasscode passcode) {
        TextChannel passcodeChannel = ChannelOutputType.PASSCODE.getTextChannel();
        if (passcodeChannel == null) return;
        
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(passcode.getPasscode());
        embed.setDescription("The new passcode.");
        embed.setColor(DiscordColors.SUCCESS);

        passcodeChannel.sendMessageEmbeds(embed.build())
            .queue((h) -> ServerState.get().setLastPasscodeSent(true));
    }

    public static void serverError(String title, String error) {
        TextChannel errorChannel = ChannelOutputType.ERROR.getTextChannel(true);
        if (errorChannel == null) {
            DiscordModule.get().logger().fatal("ERROR CHANNEL NOT FOUND");
            return;
        }
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(title);
        embed.setDescription(error);
        embed.setColor(DiscordColors.ERROR);
        errorChannel.sendMessageEmbeds(embed.build())
            .queue((h) -> ServerState.get().setLastPasscodeSent(true));

    }
}
