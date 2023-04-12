package apple.discord.psps.discord.util.channel;

import apple.discord.psps.discord.DiscordModule;
import apple.discord.psps.discord.server.ServerService;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.commands.Command.Choice;
import org.jetbrains.annotations.Nullable;

public class ChannelOutput {

    private long id = 0;
    private transient TextChannel channel;
    private String name;
    private String description;

    public ChannelOutput(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ChannelOutput() {
    }

    @Nullable
    public TextChannel getTextChannel() {
        return getTextChannel(false);
    }

    @Nullable
    public TextChannel getTextChannel(boolean suppressErrors) {
        if (channel != null) return channel;
        channel = DiscordModule.dcf.jda().getTextChannelById(id);
        if (channel == null && !suppressErrors) {
            DiscordModule.get().logger().error("%s [%d] does not exist".formatted(name, id));
            ServerService.serverError("Channel Missing", "/config channel [%s] [channelName]".formatted(name));
        }
        return channel;
    }

    public Choice toChoice() {
        return new Choice(this.name, this.name);
    }

    public String getName() {
        return this.name;
    }

    public void setId(long id) {
        this.id = id;
        ChannelConfig.get().save();
    }
}
