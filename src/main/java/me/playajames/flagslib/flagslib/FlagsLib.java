package me.playajames.flagslib.flagslib;

import org.bukkit.plugin.java.JavaPlugin;

public final class FlagsLib extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().info("FlagsLib has been enabled.");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("FlagsLib has been disabled.");
    }

}
