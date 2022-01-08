package me.playajames.flagslib.flagslib.flagtypes;

import me.playajames.flagslib.flagslib.Flag;
import me.playajames.flagslib.flagslib.FlagType;

public class GlobalFlag extends Flag {

    public GlobalFlag(String key, String value) {
        super("global", FlagType.Global, key, value);
    }

    public GlobalFlag(String key) {
        super("global", FlagType.Global, key, null);
    }

}
