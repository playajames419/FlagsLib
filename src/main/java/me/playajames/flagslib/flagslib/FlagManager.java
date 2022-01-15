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

import java.util.List;

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
            FlagsRecord record = flagsDBDAO.getOne("global", key);
            return new GlobalFlag(record.getId(), record.getIdentifier(), record.getName(), record.getValue(), FlagType.valueOf(record.getType()), record.getUpdated(), record.getCreated());
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
            FlagsRecord record = flagsDBDAO.getOne(entity.getUniqueId().toString(), key);
            return new EntityFlag(record.getId(), record.getIdentifier(), record.getName(), record.getValue(), FlagType.valueOf(record.getType()), record.getUpdated(), record.getCreated());
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
            FlagsRecord record = flagsDBDAO.getOne(Locations.serialize(location, true, false), key);
            return new LocationFlag(record.getId(), record.getIdentifier(), record.getName(), record.getValue(), FlagType.valueOf(record.getType()), record.getUpdated(), record.getCreated());
        }
        return null;
    }

    public static ItemFlag getFlag(ItemStack item, String key) {
        if (!hasFlag(item, key)) return null;
        NBTItem nbti = new NBTItem(item);
        return new ItemFlag(item, key, nbti.getString(key));
    }

    public static List<Flag> getAllFlagsByType(FlagType type) throws Exception { //todo file storage fetch
        if (STORAGETYPE.equals(StorageType.File)) throw new Exception("Feature not implemented yet for file storage");

        if (STORAGETYPE.equals(StorageType.MySQL)) {
            FlagsDBDAO flagsDBDAO = new FlagsDBDAO();
            //FlagsRecord[] records = flagsDBDAO.getByType(type);
        }
        return null;
    }

    public static List<Flag> getAllFlagsByTypeWithKey(FlagType type, String key) throws Exception { //todo file storage fetch
        if (STORAGETYPE.equals(StorageType.File)) throw new Exception("Feature not implemented yet for file storage");

        if (STORAGETYPE.equals(StorageType.MySQL)) {
            FlagsDBDAO flagsDBDAO = new FlagsDBDAO();
            return flagsDBDAO.getByTypeWithKey(type, key);
        }
        return null;
    }

    public static List<Flag> getAllFlagsByTypeWithValue(FlagType type, String key) throws Exception { //todo file storage fetch
        if (STORAGETYPE.equals(StorageType.File)) throw new Exception("Feature not implemented yet for file storage");

        if (STORAGETYPE.equals(StorageType.MySQL)) {
            FlagsDBDAO flagsDBDAO = new FlagsDBDAO();
            return flagsDBDAO.getByTypeWithKey(type, key);
        }
        return null;
    }

    public static List<Flag> getAllFlagsByTypeWithKeyAndValue(FlagType type, String key) throws Exception { //todo file storage fetch
        if (STORAGETYPE.equals(StorageType.File)) throw new Exception("Feature not implemented yet for file storage");

        if (STORAGETYPE.equals(StorageType.MySQL)) {
            FlagsDBDAO flagsDBDAO = new FlagsDBDAO();
            return flagsDBDAO.getByTypeWithKey(type, key);
        }
        return null;
    }

//    TODO Implement methods to get all flags on an object, and get all flags of an object

//    public static Map<String, EntityFlag> getAllFlags(Entity entity) {
//        return null;
//    }
//
//    public static Map<String, LocationFlag> getAllFlags(Location location) {
//        return null;
//    }

}
