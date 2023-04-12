package apple.discord.psps.database;

import apple.discord.psps.PSPS;
import apple.discord.psps.database.passcode.DPasscode;
import apple.discord.psps.database.payment.DPayment;
import apple.discord.psps.database.player.DPlayer;
import apple.discord.psps.database.subscription.DSubscription;
import apple.lib.modules.AppleModule;
import apple.lib.modules.configs.data.config.AppleConfig.Builder;
import apple.lib.modules.configs.factory.AppleConfigLike;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class DatabaseModule extends AppleModule {

    private File databaseConfigFile;

    private static List<Class<?>> getEntities() {
        List<Class<?>> entities = new ArrayList<>();
        entities.add(DPasscode.class);

        entities.addAll(List.of(DPlayer.class, DPayment.class, DSubscription.class));
        return entities;
    }

    @NotNull
    private static DatabaseConfig configureDatabase(DataSourceConfig dataSourceConfig) {
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setDataSourceConfig(dataSourceConfig);
        dbConfig.setDdlGenerate(true);
        dbConfig.setDdlRun(CloverDatabaseConfig.get().getDDLCreateDatabase());
        dbConfig.setRunMigration(CloverDatabaseConfig.get().getDDLMigration());
        dbConfig.addAll(getEntities());
        return dbConfig;
    }

    @NotNull
    private static DataSourceConfig configureDataSource(CloverDatabaseConfig loadedConfig) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUsername(loadedConfig.getUsername());
        dataSourceConfig.setPassword(loadedConfig.getPassword());
        dataSourceConfig.setUrl(loadedConfig.getUrl());
        return dataSourceConfig;
    }

    @Override
    public void onLoad() {
        if (!CloverDatabaseConfig.get().isConfigured()) {
            this.logger().fatal("Please configure " + this.databaseConfigFile.getAbsolutePath());
            System.exit(1);
        }
        DataSourceConfig dataSourceConfig = configureDataSource(CloverDatabaseConfig.get());
        DatabaseConfig dbConfig = configureDatabase(dataSourceConfig);
        DatabaseFactory.createWithContextClassLoader(dbConfig, PSPS.class.getClassLoader());
        logger().info("Successfully created database");
    }

    @Override
    public List<AppleConfigLike> getConfigs() {
        Builder<CloverDatabaseConfig> databaseConfig = configJson(CloverDatabaseConfig.class, "Database.config");
        this.databaseConfigFile = this.getFile("Database.config.json");
        return List.of(databaseConfig);
    }

    @Override
    public String getName() {
        return "Database";
    }

}
