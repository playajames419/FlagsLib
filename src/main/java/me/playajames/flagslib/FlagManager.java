package me.playajames.flagslib;

import de.tr7zw.nbtapi.NBTItem;
import me.playajames.easydatabaseconnector.jooq.tables.records.FlagsRecord;
import me.playajames.flagslib.flagtypes.*;
import me.playajames.flagslib.utils.IdentifierGenerator;
import me.playajames.tdsutils.spigot.world.Locations;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

import java.util.List;

import static me.playajames.flagslib.FlagsLib.STORAGETYPE;

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

    public static boolean hasFlag(Chunk chunk, String key) { //todo here
        if (STORAGETYPE.equals(StorageType.File)) {
            FlagsFileDAO flagsFileDAO = new FlagsFileDAO();
            return flagsFileDAO.has(IdentifierGenerator.generate(chunk), key);
        } else if (STORAGETYPE.equals(StorageType.MySQL)) {
            FlagsDBDAO flagsDBDAO = new FlagsDBDAO();
            return flagsDBDAO.has(IdentifierGenerator.generate(chunk), key);
        }
        return false;
    }

    public static boolean hasFlag(ItemStack item, String key) {
        NBTItem nbti = new NBTItem(item);
        return nbti.hasKey(key);
    }

    public static void setGlobalFlag(String key, @Nullable String value) {
        if (hasGlobalFlag(key)) {
            getGlobalFlag(key).setValue(value, true);
            return;
        }
        new GlobalFlag(key, value).save();
    }

    public static void setFlag(Entity entity, String key, @Nullable String value) {
        if (hasFlag(entity, key)) {
            getFlag(entity, key).setValue(value, true);
            return;
        }
        new EntityFlag(entity, key, value).save();
    }

    public static void setFlag(Location location, String key, @Nullable String value) {
        if (hasFlag(location, key)) {
            getFlag(location, key).setValue(value, true);
            return;
        }
        new LocationFlag(location, key, value).save();
    }

    public static void setFlag(Chunk chunk, String key, @Nullable String value) {
        if (hasFlag(chunk, key)) {
            getFlag(chunk, key).setValue(value, true);
            return;
        }
        new ChunkFlag(chunk, key, value).save();
    }

    public static ItemStack setFlag(ItemStack item, String key, @Nullable String value) {
        ItemFlag flag = getFlag(item, key);
        if (flag != null) {
            flag.setValue(value, false);
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

    public static ChunkFlag getFlag(Chunk chunk, String key) {
        if (!hasFlag(chunk, key)) return null;
        if (STORAGETYPE.equals(StorageType.File)) {
            FlagsFileDAO flagsFileDAO = new FlagsFileDAO();
            return new ChunkFlag(chunk, key, flagsFileDAO.get(IdentifierGenerator.generate(chunk), key));
        } else if (STORAGETYPE.equals(StorageType.MySQL)) {
            FlagsDBDAO flagsDBDAO = new FlagsDBDAO();
            FlagsRecord record = flagsDBDAO.getOne(IdentifierGenerator.generate(chunk), key);
            return new ChunkFlag(record.getId(), record.getIdentifier(), record.getName(), record.getValue(), FlagType.valueOf(record.getType()), record.getUpdated(), record.getCreated());
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
            return new FlagMapper().mapMany(new FlagsDBDAO().getAllByType(type));
        }
        return null;
    }

    public static List<Flag> getAllFlagsByTypeWithKey(FlagType type, String key) throws Exception { //todo file storage fetch
        if (STORAGETYPE.equals(StorageType.File)) throw new Exception("Feature not implemented yet for file storage");

        if (STORAGETYPE.equals(StorageType.MySQL)) {
            return new FlagMapper().mapMany(new FlagsDBDAO().getAllByTypeWithKey(type, key));
        }
        return null;
    }

    public static List<Flag> getAllFlagsByTypeWithValue(FlagType type, String value) throws Exception { //todo file storage fetch
        if (STORAGETYPE.equals(StorageType.File)) throw new Exception("Feature not implemented yet for file storage");

        if (STORAGETYPE.equals(StorageType.MySQL)) {
            return new FlagMapper().mapMany(new FlagsDBDAO().getAllByTypeWithValue(type, value));
        }
        return null;
    }

    public static List<Flag> getAllFlagsByTypeWithKeyAndValue(FlagType type, String key, String value) throws Exception { //todo file storage fetch
        if (STORAGETYPE.equals(StorageType.File)) throw new Exception("Feature not implemented yet for file storage");

        if (STORAGETYPE.equals(StorageType.MySQL)) {
            return new FlagMapper().mapMany(new FlagsDBDAO().getAllByTypeWithKeyAndValue(type, key, value));
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