package me.playajames.flagslib.flagtypes;

import me.playajames.flagslib.utils.Locations;
import org.bukkit.Location;

import javax.annotation.Nullable;
import java.time.format.DateTimeFormatter;

public class LocationFlag extends Flag {

    final Location location;

    public LocationFlag(Location location, String key, String value, boolean isTemp) {
        super(Locations.serialize(location, true, false), key, value, FlagType.Location, isTemp); //todo copy Locations.class from TDSUtils so no dependency is required;
        this.location = location;
    }

    public LocationFlag(int id, String identifier, String key, @Nullable String value, String type, boolean isTemp, String updated, String created) {
        super(id, identifier, key, value, type, isTemp, updated, created);
        location = Locations.deserialize(identifier);
    }

    public LocationFlag(Flag flag) {
        super(flag.getId(), flag.getIdentifier(), flag.getKey(), flag.getValue(), flag.getType().name(), flag.isTemp(), flag.getUpdated().format(DateTimeFormatter.ISO_DATE_TIME), flag.getCreated().format(DateTimeFormatter.ISO_DATE_TIME));
        location = Locations.deserialize(identifier);
    }

    public Location getLocation() {
        return location;
    }

}
