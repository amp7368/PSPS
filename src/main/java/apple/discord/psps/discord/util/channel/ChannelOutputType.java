package apple.discord.psps.discord.util.channel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.commands.Command.Choice;

public enum ChannelOutputType {
    ERROR(ChannelConfig.get().getErrorChannel()),
    PASSCODE(ChannelConfig.get().getPasscode());

    public static final Collection<Choice> CHOICES = Arrays.stream(values()).map(output -> output.output.toChoice()).toList();
    public static final Map<String, ChannelOutput> NAMES_TO_OUTPUT = Arrays.stream(values()).map(ChannelOutputType::getOutput)
        .collect(Collectors.toMap(ChannelOutput::getName, o -> o));
    private final ChannelOutput output;

    ChannelOutputType(ChannelOutput output) {
        this.output = output;
    }

    public ChannelOutput getOutput() {
        return this.output;
    }

    public TextChannel getTextChannel() {
        return this.output.getTextChannel();
    }

    public TextChannel getTextChannel(boolean suppressErrors) {
        return this.output.getTextChannel(suppressErrors);
    }
}
