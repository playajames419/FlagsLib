package me.playajames.flagslib.flagslib.flagtypes;

import me.playajames.flagslib.flagslib.Flag;
import me.playajames.flagslib.flagslib.FlagsDAO;
import org.bukkit.Location;

public class LocationFlag extends Flag {

    final Location location;
    public static final String path = "locations";

    public LocationFlag(Location location, String key, String value) {
        super(key, value);
        this.location = location;
    }

    public LocationFlag(Location location, String key, boolean value) {
        super(key, value);
        this.location = location;
    }

    public LocationFlag(Location location, String key, Number value) {
        super(key, String.valueOf(value));
        this.location = location;
    }

    public LocationFlag(Location location, String key) {
        super(key, null);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String getPath() {
        return path + "." + location.serialize().toString();
    }

    @Override
    public void save() {
        new FlagsDAO().save(getPath(), getKey(), getValueAsString());
    }

    @Override
    public void delete() {
        new FlagsDAO().delete(getPath(), getKey());
    }
}
