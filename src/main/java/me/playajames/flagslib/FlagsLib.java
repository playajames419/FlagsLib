package me.playajames.flagslib;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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
        if (Objects.requireNonNull(config.getString("storage-type")).equalsIgnoreCase("mysql"))
            STORAGETYPE = StorageType.MySQL;
    }


}