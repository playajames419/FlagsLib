package me.playajames.flagslib.flagslib.flagtypes;

import me.playajames.flagslib.flagslib.Flag;
import me.playajames.flagslib.flagslib.FlagType;
import org.bukkit.entity.Entity;

public class EntityFlag extends Flag {

    final Entity entity;

    public EntityFlag(Entity entity, String key, String value) {
        super(entity.getUniqueId().toString(), FlagType.Entity, key, value);
        this.entity = entity;
    }

    public EntityFlag(Entity entity, String key) {
        super(entity.getUniqueId().toString(), FlagType.Entity, key, null);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

}
