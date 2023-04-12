package apple.discord.psps.discord.server;

import apple.lib.modules.configs.data.config.AppleConfig;

public class ServerState {

    public static AppleConfig<ServerState> config;
    private static ServerState instance;
    private boolean lastPasscodeSent = true;

    public ServerState() {
        instance = this;
    }

    public static ServerState get() {
        return instance;
    }

    public void save() {
        config.save();
    }

    public boolean getLastPasscodeSent() {
        return this.lastPasscodeSent;
    }

    public synchronized void setLastPasscodeSent(boolean lastPasscodeSent) {
        this.lastPasscodeSent = lastPasscodeSent;
        save();
    }
}
