package me.playajames.flagslib.flagslib.flagtypes;

import me.playajames.flagslib.flagslib.Flag;
import me.playajames.flagslib.flagslib.FlagsDAO;
import org.bukkit.Location;

public class LocationFlag extends Flag {

    final Location location;
    public static final String path = "locations";

    public LocationFlag(Location location, String key, String value) {
        super(path + "." + location.serialize().toString(), key, value);
        this.location = location;
    }

    public LocationFlag(Location location, String key, boolean value) {
        super(path + "." + location.serialize().toString(), key, value);
        this.location = location;
    }

    public LocationFlag(Location location, String key, Number value) {
        super(path + "." + location.serialize().toString(), key, String.valueOf(value));
        this.location = location;
    }

    public LocationFlag(Location location, String key) {
        super(path + "." + location.serialize().toString(), key, null);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

}
