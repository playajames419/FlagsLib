package me.playajames.flagslib.flagtypes;

import me.playajames.flagslib.FlagType;
import me.playajames.flagslib.Flag;
import me.playajames.tdsutils.spigot.world.Locations;
import org.bukkit.Location;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class LocationFlag extends Flag {

    final Location location;


    public LocationFlag(Location location, String key, String value) {
        super(Locations.serialize(location, true, false), key, value, FlagType.Location);
        this.location = location;
    }


    public LocationFlag(Location location, String key) {
        super(Locations.serialize(location, true, false), key, null, FlagType.Location);
        this.location = location;
    }


    public LocationFlag(int id, String identifier, String key, @Nullable String value, FlagType type, LocalDateTime updated, LocalDateTime created) {
        super(id, identifier, key, value, type, updated, created);
        location = Locations.deserialize(identifier);
    }

    public Location getLocation() {
        return location;
    }

}
