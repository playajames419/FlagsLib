package me.playajames.flagslib.flagslib;

import me.playajames.flagslib.flagslib.flagtypes.EntityFlag;
import me.playajames.flagslib.flagslib.flagtypes.LocationFlag;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class FlagManager {

    public static boolean hasFlag(Entity entity, String key) {
        FlagsDAO flagsDAO = new FlagsDAO();
        return flagsDAO.has(EntityFlag.path + "." + entity.getUniqueId().toString(), key);
    }

    public static boolean hasFlag(Location location, String key) {
        FlagsDAO flagsDAO = new FlagsDAO();
        return flagsDAO.has(LocationFlag.path + "." + location.serialize().toString(), key);
    }

    public static void setFlag(Entity entity, String key, String value) {
        if (hasFlag(entity, key)) {
            getFlag(entity, key).setValue(value);
            return;
        }
        new EntityFlag(entity, key, value).save();
    }

    public static void setFlag(Entity entity, String key, boolean value) {
        if (hasFlag(entity, key)) {
            getFlag(entity, key).setValue(String.valueOf(value));
            return;
        }
        new EntityFlag(entity, key, value).save();
    }

    public static void setFlag(Entity entity, String key, Number value) {
        if (hasFlag(entity, key)) {
            getFlag(entity, key).setValue(String.valueOf(value));
            return;
        }
        new EntityFlag(entity, key, value).save();
    }

    public static void setFlag(Entity entity, String key) {
        if (hasFlag(entity, key)) {
            getFlag(entity, key).setValue(null);
            return;
        }
        new EntityFlag(entity, key).save();
    }

    public static void setFlag(Location location, String key, String value) {
        if (hasFlag(location, key)) {
            getFlag(location, key).setValue(value);
            return;
        }
        new LocationFlag(location, key, value).save();
    }

    public static void setFlag(Location location, String key, boolean value) {
        if (hasFlag(location, key)) {
            getFlag(location, key).setValue(value);
            return;
        }
        new LocationFlag(location, key, value).save();
    }

    public static void setFlag(Location location, String key, Number value) {
        if (hasFlag(location, key)) {
            getFlag(location, key).setValue(String.valueOf(value));
            return;
        }
        new LocationFlag(location, key, value).save();
    }

    public static void setFlag(Location location, String key) {
        if (hasFlag(location, key)) {
            getFlag(location, key).setValue(null);
            return;
        }
        new LocationFlag(location, key).save();
    }

    public static EntityFlag getFlag(Entity entity, String key) {
        if (!hasFlag(entity, key)) return null;
        FlagsDAO flagsDAO = new FlagsDAO();
        return new EntityFlag(entity, key, flagsDAO.get(EntityFlag.path + "." + entity.getUniqueId().toString(), key));
    }

    public static LocationFlag getFlag(Location location, String key) {
        if (!hasFlag(location, key)) return null;
        FlagsDAO flagsDAO = new FlagsDAO();
        return new LocationFlag(location, key, flagsDAO.get(LocationFlag.path + "." + location.serialize().toString(), key));
    }

//    public static Map<String, EntityFlag> getFlags(Entity entity) {
//        return null;
//    }
//
//    public static Map<String, LocationFlag> getFlags(Location location) {
//        return null;
//    }

}
