package apple.discord.psps.discord.util.channel;

import apple.lib.modules.configs.data.config.AppleConfig;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.jetbrains.annotations.NotNull;

public class ChannelConfig {

    public static AppleConfig<ChannelConfig> config;
    private static ChannelConfig instance;
    private final ChannelOutput passcode = new ChannelOutput("passcode", "The output for server-codes");
    private final ChannelOutput error = new ChannelOutput("error", "The output for any bot errors");
    private transient TextChannel passcodeChannel;
    private transient TextChannel errorChannel;

    public ChannelConfig() {
        instance = this;
    }

    public static ChannelConfig get() {
        return instance;
    }

    @NotNull
    public ChannelOutput getPasscode() {
        return passcode;
    }

    public ChannelOutput getErrorChannel() {
        return error;
    }

    public void save() {
        this.config.save();
    }
}
