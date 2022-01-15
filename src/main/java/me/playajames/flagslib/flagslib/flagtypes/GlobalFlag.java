package me.playajames.flagslib.flagslib.flagtypes;

import me.playajames.flagslib.flagslib.Flag;
import me.playajames.flagslib.flagslib.FlagType;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class GlobalFlag extends Flag {

    public GlobalFlag(String key, String value) {
        super("global", key, value, FlagType.Global);
    }

    public GlobalFlag(String key) {
        super("global", key, null, FlagType.Global);
    }

    public GlobalFlag(int id, String identifier, String key, @Nullable String value, FlagType type, LocalDateTime updated, LocalDateTime created) {
        super(id, identifier, key, value, type, updated, created);
    }

}
