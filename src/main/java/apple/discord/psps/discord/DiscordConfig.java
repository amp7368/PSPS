package apple.discord.psps.discord;

public class DiscordConfig {

    private static DiscordConfig instance;
    public String token = "DiscordToken";

    public DiscordConfig() {
        instance = this;
    }

    public static DiscordConfig get() {
        return instance;
    }

    public String getToken() {
        return token;
    }
}
