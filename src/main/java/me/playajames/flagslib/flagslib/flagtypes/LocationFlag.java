package me.playajames.flagslib.flagslib.flagtypes;

import me.playajames.flagslib.flagslib.Flag;
import me.playajames.flagslib.flagslib.FlagType;
import me.playajames.tdsutils.spigot.world.Locations;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.UUID;

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
