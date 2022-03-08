package me.playajames.flagslib;

import me.playajames.flagslib.dao.DAO;
import me.playajames.flagslib.dao.FlagsDBDAO;
import me.playajames.flagslib.dao.StorageType;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlagsLib extends JavaPlugin {

    public static StorageType STORAGETYPE;
    public static DAO DAO_INSTANCE;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        intiDAO();
        this.getLogger().info("Storage type is set to " + STORAGETYPE + ".");
        this.getLogger().info("FlagsLib has been enabled.");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("FlagsLib has been disabled.");
    }

    private void intiDAO() {
        STORAGETYPE = StorageType.MySQL;

        String driver = getConfig().getString("sql.driver");
        String host = getConfig().getString("sql.host");
        int port = getConfig().getInt("sql.port");
        String database = getConfig().getString("sql.database");
        boolean useSSL = getConfig().getBoolean("sql.use-ssl");
        String user = getConfig().getString("sql.user");
        String password = getConfig().getString("sql.password");

        setDatabaseParams(driver, host, port, database, useSSL, user, password);
    }

    private void setDatabaseParams(String driver, String host, int port, String database, boolean useSSL, String user, String password) {
        DAO_INSTANCE = new FlagsDBDAO(driver, host, port, database, useSSL, user, password);
    }

}
