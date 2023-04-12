package apple.discord.psps.roblox.username;

public class RobloxUsernameRequest {

    public String[] usernames;
    public boolean excludeBannedUsers = true;

    public RobloxUsernameRequest(String... usernames) {
        this.usernames = usernames;
    }
}
