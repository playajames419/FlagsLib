package me.playajames.flagslib.flagtypes;

import me.playajames.flagslib.Flag;
import me.playajames.flagslib.FlagType;

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
