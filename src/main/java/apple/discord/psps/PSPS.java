package apple.discord.psps;

import apple.discord.psps.database.DatabaseModule;
import apple.discord.psps.discord.DiscordModule;
import apple.discord.psps.roblox.RobloxModule;
import apple.lib.modules.AppleModule;
import apple.lib.modules.ApplePlugin;
import java.util.List;

public class PSPS extends ApplePlugin {

    private static PSPS instance;

    public PSPS() {
        instance = this;
    }

    public static void main(String[] args) {
        new PSPS().start();
    }

    public static PSPS get() {
        return instance;
    }

    @Override
    public String getName() {
        return "PSPS";
    }

    @Override
    public List<AppleModule> createModules() {
        return List.of(new DatabaseModule(), new RobloxModule(), new DiscordModule());
    }
}
