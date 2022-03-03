package me.playajames.flagslib;

import de.tr7zw.nbtapi.NBTItem;
<<<<<<< HEAD
=======
import me.playajames.flagslib.dao.FlagsDBDAO;
import me.playajames.flagslib.dao.StorageType;
>>>>>>> rewrite
import me.playajames.flagslib.flagtypes.*;
import me.playajames.flagslib.utils.IdentifierGenerator;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

import java.util.List;

import static me.playajames.flagslib.FlagsLib.DAO_INSTANCE;

public class FlagManager {

    public static boolean hasFlag(String identifier, String key) {
        return DAO_INSTANCE.has(identifier, key);
    }

    public static boolean hasFlag(Entity entity, String key) {
        return DAO_INSTANCE.has(entity.getUniqueId().toString(), key);
    }

    public static boolean hasFlag(Location location, String key) {
        return DAO_INSTANCE.has(location.serialize().toString(), key);
    }

    public static boolean hasFlag(Chunk chunk, String key) { //todo here
        return DAO_INSTANCE.has(IdentifierGenerator.generate(chunk), key);
    }

    public static boolean hasFlag(ItemStack item, String key) {
        NBTItem nbti = new NBTItem(item);
        return nbti.hasKey(key);
    }

<<<<<<< HEAD
    public static Flag createFlag(String identifier, String key, @Nullable String value, boolean isTemp) {
        if (hasFlag(identifier, key))
            return null;
        return new Flag(identifier, key, value, FlagType.Generic, isTemp);
    }

    public static EntityFlag createFlag(Entity entity, String key, @Nullable String value, boolean isTemp) {
        if (hasFlag(entity, key))
            return null;
        new EntityFlag(entity, key, value, isTemp);
=======
    public static void setFlag(String identifier, String key, @Nullable String value) {
        if (hasFlag(identifier, key)) {
            getFlag(identifier, key).setValue(value);
            return;
        }
        new Flag(identifier, key, value, FlagType.Generic).save();
    }

    public static void setFlag(Entity entity, String key, @Nullable String value) {
        if (hasFlag(entity, key)) {
            getFlag(entity, key).setValue(value);
            return;
        }
        new EntityFlag(entity, key, value).save();
>>>>>>> rewrite
    }

    public static LocationFlag createFlag(Location location, String key, @Nullable String value, boolean isTemp) {
        if (hasFlag(location, key)) {
<<<<<<< HEAD
            return null;
=======
            getFlag(location, key).setValue(value);
            return;
>>>>>>> rewrite
        }
        return new LocationFlag(location, key, value, isTemp);
    }

    public static ChunkFlag createFlag(Chunk chunk, String key, @Nullable String value, boolean isTemp) {
        if (hasFlag(chunk, key)) {
<<<<<<< HEAD
            return null;
=======
            getFlag(chunk, key).setValue(value);
            return;
>>>>>>> rewrite
        }
        return new ChunkFlag(chunk, key, value, isTemp);
    }

    public static ItemFlag createFlag(ItemStack item, String key, @Nullable String value, boolean isTemp) {
        ItemFlag flag = getFlag(item, key);
        if (flag != null) {
<<<<<<< HEAD
            return null;
=======
            flag.setValue(value);
            return flag.getItem();
>>>>>>> rewrite
        }
        return new ItemFlag(item, key, value, isTemp);
    }

    public static Flag getFlag(String identifier, String key) {
        if (!hasFlag(identifier, key)) return null;
<<<<<<< HEAD
        return DAO_INSTANCE.getOne(identifier, key);
=======
        if (STORAGETYPE.equals(StorageType.File)) {
            FlagsFileDAO flagsFileDAO = new FlagsFileDAO();
            return new Flag(identifier, key, flagsFileDAO.get(identifier, key), FlagType.Generic);
        } else if (STORAGETYPE.equals(StorageType.MySQL)) {
            FlagsDBDAO flagsDBDAO = new FlagsDBDAO();
            return new Flag(identifier, key, /** todo fetch value */, FlagType.Generic);
        }
        return null;
>>>>>>> rewrite
    }

    public static EntityFlag getFlag(Entity entity, String key) {
        if (!hasFlag(entity, key)) return null;
        return (EntityFlag) DAO_INSTANCE.getOne(entity.getUniqueId().toString(), key);
    }

    public static LocationFlag getFlag(Location location, String key) {
        if (!hasFlag(location, key)) return null;
        return (LocationFlag) DAO_INSTANCE.getOne(Locations.serialize(location, true, false), key);
    }

    public static ChunkFlag getFlag(Chunk chunk, String key) {
        if (!hasFlag(chunk, key)) return null;
        return (ChunkFlag) DAO_INSTANCE.getOne(IdentifierGenerator.generate(chunk), key);
    }

    public static ItemFlag getFlag(ItemStack item, String key) { //todo after ItemFlag rewrite
        if (!hasFlag(item, key)) return null;
        NBTItem nbti = new NBTItem(item);
        return new ItemFlag(item, key, nbti.getString(key));
    }

    public static List<Flag> getAllFlagsByType(FlagType type) { //todo file storage fetch
        List<Flag> flags = DAO_INSTANCE.getManyByType(type);
        if (flags.isEmpty())
            return null;
        return flags;
    }

    public static List<Flag> getAllFlagsByTypeWithKey(FlagType type, String key) { //todo file storage fetch
        List<Flag> flags = DAO_INSTANCE.getManyByTypeWithKey(type, key);
        if (flags.isEmpty())
            return null;
        return flags;
    }

    public static List<Flag> getAllFlagsByTypeWithValue(FlagType type, String value) { //todo file storage fetch
        List<Flag> flags = DAO_INSTANCE.getManyByTypeWithValue(type, value);
        if (flags.isEmpty())
            return null;
        return flags;
    }

    public static List<Flag> getAllFlagsByTypeWithKeyAndValue(FlagType type, String key, String value) { //todo file storage fetch
        List<Flag> flags = DAO_INSTANCE.getManyByTypeWithKeyAndValue(type, key, value);
        if (flags.isEmpty())
            return null;
        return flags;
    }

    public static List<Flag> getAllFlags(String identifier) {
        List<Flag> flags = DAO_INSTANCE.getManyByTypeWithIdentifier(FlagType.Generic, identifier);
        if (flags.isEmpty())
            return null;
        return flags;
    }

    public static List<EntityFlag> getAllFlags(Entity entity) {
        List<EntityFlag> flags = (List<EntityFlag>) (List<?>) DAO_INSTANCE.getManyByTypeWithIdentifier(FlagType.Entity, entity.getUniqueId().toString());
        if (flags.isEmpty())
            return null;
        return flags;
    }

    public static List<LocationFlag> getAllFlags(Location location) {
        List<LocationFlag> flags = (List<LocationFlag>) (List<?>) DAO_INSTANCE.getManyByTypeWithIdentifier(FlagType.Location, Locations.serialize(location, true, false));
        if (flags.isEmpty())
            return null;
        return flags;
    }

    public static List<ChunkFlag> getAllFlags(Chunk chunk) {
<<<<<<< HEAD
        List<ChunkFlag> flags = (List<ChunkFlag>) (List<?>) DAO_INSTANCE.getManyByTypeWithIdentifier(FlagType.Chunk, IdentifierGenerator.generate(chunk));
        if (flags.isEmpty())
            return null;
        return flags;
=======
        if (STORAGETYPE.equals(StorageType.File)) try {
            throw new Exception("Feature not implemented yet for file storage");
        } catch (Exception e) {
            e.printStackTrace();
        }

        else if (STORAGETYPE.equals(StorageType.MySQL)) {
            return new FlagMapper().mapManyChunk(new FlagsDBDAO().getAllByIdentifier(IdentifierGenerator.generate(chunk)));
        }

        return null;
    }

    public static List<Flag> getAllFlags() {
        if (STORAGETYPE.equals(StorageType.File)) try {
            throw new Exception("Feature not implemented yet for file storage");
        } catch (Exception e) {
            e.printStackTrace();
        }

        else if (STORAGETYPE.equals(StorageType.MySQL)) {
            return new FlagMapper().mapManyGlobal(new FlagsDBDAO().getAllByIdentifier("global"));
        }

        return null;
>>>>>>> rewrite
    }

}
