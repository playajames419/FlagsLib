package me.playajames.flagslib.flagtypes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.UUID;

public class EntityFlag extends Flag {

    final Entity entity;

    public EntityFlag(Entity entity, String key, @Nullable String value, boolean isTemp) {
        super(entity.getUniqueId().toString(), key, value, FlagType.Entity, isTemp);
        this.entity = entity;
    }

    public EntityFlag(int id, String identifier, String key, @Nullable String value, FlagType type, boolean isTemp, LocalDateTime updated, LocalDateTime created) {
        super(id, identifier, key, value, type, isTemp updated, created);
        entity = Bukkit.getEntity(UUID.fromString(identifier)); //todo dont think this will this work if entity is offline or not loaded? may just need to return null entity, but still allow modification of the flag
    }

    public Entity getEntity() {
        return entity;
    }

}
