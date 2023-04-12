package apple.discord.psps.discord;

import net.dv8tion.jda.api.entities.Guild;

public class DiscordConfig {

    private static DiscordConfig instance;
    public String token = "DiscordToken";
    public long guildId = 0;

    public DiscordConfig() {
        instance = this;
    }

    public static DiscordConfig get() {
        return instance;
    }

    public String getToken() {
        return token;
    }

    public boolean isGuildAllowed(Guild guild) {
        if (guild == null) return false;
        return guild.getIdLong() == this.guildId;
    }
}
