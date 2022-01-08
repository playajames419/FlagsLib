package me.playajames.flagslib.flagslib.flagtypes;

import me.playajames.flagslib.flagslib.Flag;
import me.playajames.flagslib.flagslib.FlagType;
import org.bukkit.Location;

public class LocationFlag extends Flag {

    final Location location;


    public LocationFlag(Location location, String key, String value) {
        super(location.serialize().toString(), FlagType.Location, key, value);
        this.location = location;
    }


    public LocationFlag(Location location, String key) {
        super(location.serialize().toString(), FlagType.Location, key, null);
        this.location = location;
    }


    public Location getLocation() {
        return location;
    }

}
