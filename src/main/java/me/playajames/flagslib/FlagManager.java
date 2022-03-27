package me.playajames.flagslib;

import me.playajames.flagslib.flagtypes.*;
import me.playajames.flagslib.utils.IdentifierGenerator;
import me.playajames.flagslib.utils.Locations;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public static Flag createFlag(String identifier, String key, String value, boolean isTemp) {
        if (hasFlag(identifier, key))
            return null;
        return new Flag(identifier, key, value, FlagType.Generic, isTemp);
    }

    public static EntityFlag createFlag(Entity entity, String key, String value, boolean isTemp) {
        if (hasFlag(entity, key))
            return null;
        return new EntityFlag(entity, key, value, isTemp);
    }

    public static LocationFlag createFlag(Location location, String key, String value, boolean isTemp) {
        if (hasFlag(location, key))
            return null;
        return new LocationFlag(location, key, value, isTemp);
    }

    public static ChunkFlag createFlag(Chunk chunk, String key, String value, boolean isTemp) {
        if (hasFlag(chunk, key))
            return null;
        return new ChunkFlag(chunk, key, value, isTemp);
    }

    public static Flag getFlag(String identifier, String key) {
        if (!hasFlag(identifier, key))
            return null;
        return DAO_INSTANCE.getOne(identifier, key);
    }

    public static EntityFlag getFlag(Entity entity, String key) {
        if (!hasFlag(entity, key)) return null;
        Flag flag = DAO_INSTANCE.getOne(entity.getUniqueId().toString(), key);
        return new EntityFlag(flag);
    }

    public static LocationFlag getFlag(Location location, String key) {
        if (!hasFlag(location, key)) return null;
        Flag flag = DAO_INSTANCE.getOne(Locations.serialize(location, true, false), key);
        return new LocationFlag(flag);
    }

    public static ChunkFlag getFlag(Chunk chunk, String key) {
        if (!hasFlag(chunk, key)) return null;
        Flag flag = DAO_INSTANCE.getOne(IdentifierGenerator.generate(chunk), key);
        return new ChunkFlag(flag);
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
        List<ChunkFlag> flags = (List<ChunkFlag>) (List<?>) DAO_INSTANCE.getManyByTypeWithIdentifier(FlagType.Chunk, IdentifierGenerator.generate(chunk));
        if (flags.isEmpty())
            return null;
        return flags;
    }

}
