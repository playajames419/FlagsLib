package me.playajames.flagslib.flagslib;

import de.tr7zw.nbtapi.NBTItem;
import me.playajames.flagslib.flagslib.flagtypes.EntityFlag;
import me.playajames.flagslib.flagslib.flagtypes.GlobalFlag;
import me.playajames.flagslib.flagslib.flagtypes.ItemFlag;
import me.playajames.flagslib.flagslib.flagtypes.LocationFlag;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

import static me.playajames.flagslib.flagslib.FlagsLib.STORAGETYPE;

public class FlagManager {

    public static boolean hasGlobalFlag(String key) {
        if (STORAGETYPE.equals(StorageType.File)) {
            FlagsFileDAO flagsFileDAO = new FlagsFileDAO();
            return flagsFileDAO.has("global", key);
        } else if (STORAGETYPE.equals(StorageType.MySQL)) {
            FlagsDBDAO flagsDBDAO = new FlagsDBDAO();
            return flagsDBDAO.has("global", key);
        }
        return false;
    }

    public static boolean hasFlag(Entity entity, String key) {
        if (STORAGETYPE.equals(StorageType.File)) {
            FlagsFileDAO flagsFileDAO = new FlagsFileDAO();
            return flagsFileDAO.has(entity.getUniqueId().toString(), key);
        } else if (STORAGETYPE.equals(StorageType.MySQL)) {
            FlagsDBDAO flagsDBDAO = new FlagsDBDAO();
            return flagsDBDAO.has(entity.getUniqueId().toString(), key);
        }
        return false;
    }

    public static boolean hasFlag(Location location, String key) {
        if (STORAGETYPE.equals(StorageType.File)) {
            FlagsFileDAO flagsFileDAO = new FlagsFileDAO();
            return flagsFileDAO.has(location.serialize().toString(), key);
        } else if (STORAGETYPE.equals(StorageType.MySQL)) {
            FlagsDBDAO flagsDBDAO = new FlagsDBDAO();
            return flagsDBDAO.has(location.serialize().toString(), key);
        }
        return false;
    }

    public static boolean hasFlag(ItemStack item, String key) {
        NBTItem nbti = new NBTItem(item);
        return nbti.hasKey(key);
    }

    public static void setGlobalFlag(String key, @Nullable String value) {
        if (hasGlobalFlag(key)) {
            getGlobalFlag(key).setValue(value);
            return;
        }
        new GlobalFlag(key, value);
    }

    public static void setFlag(Entity entity, String key, @Nullable String value) {
        if (hasFlag(entity, key)) {
            getFlag(entity, key).setValue(value);
            return;
        }
        new EntityFlag(entity, key, value);
    }

    public static void setFlag(Location location, String key, @Nullable String value) {
        if (hasFlag(location, key)) {
            getFlag(location, key).setValue(value);
            return;
        }
        new LocationFlag(location, key, value);
    }

    public static ItemStack setFlag(ItemStack item, String key, @Nullable String value) {
        ItemFlag flag = getFlag(item, key);
        if (flag != null) {
            flag.setValue(value);
            return flag.getItem();
        }
        return new ItemFlag(item, key, value).getItem();
    }

    public static GlobalFlag getGlobalFlag(String key) {
        if (!hasGlobalFlag(key)) return null;
        if (STORAGETYPE.equals(StorageType.File)) {
            FlagsFileDAO flagsFileDAO = new FlagsFileDAO();
            return new GlobalFlag(key, flagsFileDAO.get("global", key));
        } else if (STORAGETYPE.equals(StorageType.MySQL)) {
            FlagsDBDAO flagsDBDAO = new FlagsDBDAO();
            return new GlobalFlag(key, flagsDBDAO.get("global", key));
        }
        return null;
    }

    public static EntityFlag getFlag(Entity entity, String key) {
        if (!hasFlag(entity, key)) return null;
        if (STORAGETYPE.equals(StorageType.File)) {
            FlagsFileDAO flagsFileDAO = new FlagsFileDAO();
            return new EntityFlag(entity, key, flagsFileDAO.get(entity.getUniqueId().toString(), key));
        } else if (STORAGETYPE.equals(StorageType.MySQL)) {
            FlagsDBDAO flagsDBDAO = new FlagsDBDAO();
            return new EntityFlag(entity, key, flagsDBDAO.get(entity.getUniqueId().toString(), key));
        }
        return null;
    }

    public static LocationFlag getFlag(Location location, String key) {
        if (!hasFlag(location, key)) return null;
        if (STORAGETYPE.equals(StorageType.File)) {
            FlagsFileDAO flagsFileDAO = new FlagsFileDAO();
            return new LocationFlag(location, key, flagsFileDAO.get(location.serialize().toString(), key));
        } else if (STORAGETYPE.equals(StorageType.MySQL)) {
            FlagsDBDAO flagsDBDAO = new FlagsDBDAO();
            return new LocationFlag(location, key, flagsDBDAO.get(location.serialize().toString(), key));
        }
        return null;
    }

    public static ItemFlag getFlag(ItemStack item, String key) {
        if (!hasFlag(item, key)) return null;
        NBTItem nbti = new NBTItem(item);
        return new ItemFlag(item, key, nbti.getString(key));
    }

//    TODO Implement methods to get all flags on an object, and get all flags of a type

//    public static Map<String, EntityFlag> getFlags(Entity entity) {
//        return null;
//    }
//
//    public static Map<String, LocationFlag> getFlags(Location location) {
//        return null;
//    }

}
