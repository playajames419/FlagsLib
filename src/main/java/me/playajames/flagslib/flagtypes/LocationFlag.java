package me.playajames.flagslib.flagtypes;

import me.playajames.tdsutils.spigot.world.Locations;
import org.bukkit.Location;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class LocationFlag extends Flag {

    final Location location;


    public LocationFlag(Location location, String key, String value, boolean isTemp) {
        super(Locations.serialize(location, true, false), key, value, FlagType.Location, isTemp) //todo copy Locations.class from TDSUtils so no dependency is required;
        this.location = location;
    }

    public LocationFlag(int id, String identifier, String key, @Nullable String value, FlagType type, boolean isTemp, LocalDateTime updated, LocalDateTime created) {
        super(id, identifier, key, value, type, isTemp, updated, created);
        location = Locations.deserialize(identifier);
    }

    public Location getLocation() {
        return location;
    }

}
