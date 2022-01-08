package me.playajames.flagslib.flagslib;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlagsLib extends JavaPlugin {

    public static StorageType STORAGETYPE = StorageType.File;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        setConfigSettings();
        this.getLogger().info("Storage type set to " + STORAGETYPE + ".");
        this.getLogger().info("FlagsLib has been enabled.");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("FlagsLib has been disabled.");
    }

    private void setConfigSettings() {
        FileConfiguration config = getConfig();
        if (config.getString("storage-type").equalsIgnoreCase("mysql"))
            this.STORAGETYPE = StorageType.MySQL;
    }

    // TODO Not a very thorough test, should be fixed before implemented
    private void testSystems() {
        FlagManager.setGlobalFlag("q9c8w74yrna987nyhm", null);
        FlagManager.getGlobalFlag("q9c8w74yrna987nyhm").delete();
        if (!FlagManager.hasGlobalFlag("q9c8w74yrna987nyhm"))
            FlagManager.setGlobalFlag("flags-lib-test-passed", "true");
        else
            FlagManager.setGlobalFlag("flags-lib-test-passed", "false");
    }

}
