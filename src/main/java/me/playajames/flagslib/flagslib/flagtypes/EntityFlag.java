package me.playajames.flagslib.flagslib.flagtypes;

import me.playajames.flagslib.flagslib.Flag;
import me.playajames.flagslib.flagslib.FlagType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.UUID;

public class EntityFlag extends Flag {

    final Entity entity;

    public EntityFlag(Entity entity, String key, String value) {
        super(entity.getUniqueId().toString(), key, value, FlagType.Entity);
        this.entity = entity;
    }


    public EntityFlag(Entity entity, String key) {
        super(entity.getUniqueId().toString(), key, null, FlagType.Entity);
        this.entity = entity;
    }


    public EntityFlag(int id, String identifier, String key, @Nullable String value, FlagType type, LocalDateTime updated, LocalDateTime created) {
        super(id, identifier, key, value, type, updated, created);
        entity = Bukkit.getEntity(UUID.fromString(identifier));
    }


    public Entity getEntity() {
        return entity;
    }

}
