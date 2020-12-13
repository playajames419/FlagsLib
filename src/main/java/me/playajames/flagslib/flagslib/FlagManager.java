package me.playajames.flagslib.flagslib;

import de.tr7zw.nbtapi.NBTItem;
import me.playajames.flagslib.flagslib.flagtypes.EntityFlag;
import me.playajames.flagslib.flagslib.flagtypes.ItemFlag;
import me.playajames.flagslib.flagslib.flagtypes.LocationFlag;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public class FlagManager {

    public static boolean hasFlag(Entity entity, String key) {
        FlagsDAO flagsDAO = new FlagsDAO();
        return flagsDAO.has(EntityFlag.path + "." + entity.getUniqueId().toString(), key);
    }

    public static boolean hasFlag(Location location, String key) {
        FlagsDAO flagsDAO = new FlagsDAO();
        return flagsDAO.has(LocationFlag.path + "." + location.serialize().toString(), key);
    }

    public static boolean hasFlag(ItemStack item, String key) {
        NBTItem nbti = new NBTItem(item);
        return nbti.hasKey(key);
    }

    public static void setFlag(Entity entity, String key, String value) {
        if (hasFlag(entity, key)) {
            getFlag(entity, key).setValue(value);
            return;
        }
        new EntityFlag(entity, key, value);
    }

    public static void setFlag(Entity entity, String key, boolean value) {
        if (hasFlag(entity, key)) {
            getFlag(entity, key).setValue(String.valueOf(value));
            return;
        }
        new EntityFlag(entity, key, value);
    }

    public static void setFlag(Entity entity, String key, Number value) {
        if (hasFlag(entity, key)) {
            getFlag(entity, key).setValue(String.valueOf(value));
            return;
        }
        new EntityFlag(entity, key, value);
    }

    public static void setFlag(Entity entity, String key) {
        if (hasFlag(entity, key)) {
            getFlag(entity, key).setValue(null);
            return;
        }
        new EntityFlag(entity, key);
    }

    public static void setFlag(Location location, String key, String value) {
        if (hasFlag(location, key)) {
            getFlag(location, key).setValue(value);
            return;
        }
        new LocationFlag(location, key, value);
    }

    public static void setFlag(Location location, String key, boolean value) {
        if (hasFlag(location, key)) {
            getFlag(location, key).setValue(value);
            return;
        }
        new LocationFlag(location, key, value);
    }

    public static void setFlag(Location location, String key, Number value) {
        if (hasFlag(location, key)) {
            getFlag(location, key).setValue(String.valueOf(value));
            return;
        }
        new LocationFlag(location, key, value);
    }

    public static void setFlag(Location location, String key) {
        if (hasFlag(location, key)) {
            getFlag(location, key).setValue(null);
            return;
        }
        new LocationFlag(location, key);
    }

    public static ItemStack setFlag(ItemStack item, String key, String value) {
        ItemFlag flag = getFlag(item, key);
        if (flag != null) {
            flag.setValue(value);
            return flag.getItem();
        }
        return new ItemFlag(item, key, value).getItem();
    }

    public static ItemStack setFlag(ItemStack item, String key, boolean value) {
        ItemFlag flag = getFlag(item, key);
        if (flag != null) {
            flag.setValue(value);
            return flag.getItem();
        }
        return new ItemFlag(item, key, value).getItem();
    }

    public static ItemStack setFlag(ItemStack item, String key, Number value) {
        ItemFlag flag = getFlag(item, key);
        if (flag != null) {
            flag.setValue(String.valueOf(value));
            return flag.getItem();
        }
        return new ItemFlag(item, key, value).getItem();
    }

    public static ItemStack setFlag(ItemStack item, String key) {
        ItemFlag flag = getFlag(item, key);
        if (flag != null) {
            flag.setValue(null);
            return flag.getItem();
        }
        return new ItemFlag(item, key).getItem();
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

    public static ItemFlag getFlag(ItemStack item, String key) {
        if (!hasFlag(item, key)) return null;
        NBTItem nbti = new NBTItem(item);
        return new ItemFlag(item, key, nbti.getString(key));
    }

//    TODO Implement methods to get all flags on an object

//    public static Map<String, EntityFlag> getFlags(Entity entity) {
//        return null;
//    }
//
//    public static Map<String, LocationFlag> getFlags(Location location) {
//        return null;
//    }

}
