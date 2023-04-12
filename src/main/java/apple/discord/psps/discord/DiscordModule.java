package apple.discord.psps.discord;

import apple.discord.psps.discord.command.code.CommandPasscode;
import apple.discord.psps.discord.command.config.CommandConfig;
import apple.discord.psps.discord.command.debug.CommandDebug;
import apple.discord.psps.discord.command.help.CommandHelp;
import apple.discord.psps.discord.command.help.CommandHelpAdmin;
import apple.discord.psps.discord.command.purchase.CommandPurchase;
import apple.discord.psps.discord.server.ServerService;
import apple.discord.psps.discord.server.ServerState;
import apple.discord.psps.discord.util.channel.ChannelConfig;
import apple.lib.modules.AppleModule;
import apple.lib.modules.configs.data.config.AppleConfig.Builder;
import apple.lib.modules.configs.factory.AppleConfigLike;
import discord.util.dcf.DCF;
import discord.util.dcf.DCFCommandManager;
import java.util.List;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class DiscordModule extends AppleModule {

    public static DCF dcf;
    private static DiscordModule instance;

    public DiscordModule() {
        instance = this;
    }

    public static DiscordModule get() {
        return instance;
    }

    @Override
    public void onEnable() {
        JDABuilder builder = JDABuilder.createLight(DiscordConfig.get().getToken());
        JDA client = builder.build();
        client.getPresence().setPresence(Activity.playing("Slash commands!"), false);
        dcf = new DCF(client);
        DCFCommandManager commands = dcf.commands();
        // user
        commands.addCommand(new CommandHelp());
        // admin
        commands.addCommand(new CommandHelpAdmin(), new CommandPasscode(), new CommandPurchase());
        commands.addCommand(new CommandDebug(), new CommandConfig());

        commands.updateCommands();
        try {
            client.awaitReady();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ServerService.load();
    }

    @Override
    public String getName() {
        return "Discord";
    }

    @Override
    public List<AppleConfigLike> getConfigs() {

        Builder<ServerState> discordState = configJson(ServerState.class, "ServerState");
        ServerState.config = discordState.getConfig();
        Builder<ChannelConfig> channelConfig = configJson(ChannelConfig.class, "Channels.config");
        ChannelConfig.config = channelConfig.getConfig();
        return List.of(configJson(DiscordConfig.class, "Discord.config"),
            channelConfig,
            discordState);
    }
}
