package me.playajames.flagslib.flagslib.flagtypes;

import me.playajames.flagslib.flagslib.Flag;
import org.bukkit.entity.Entity;

public class EntityFlag extends Flag {

    final Entity entity;
    public static final String path = "entities";

    public EntityFlag(Entity entity, String key, String value) {
        super(path + "." + entity.getUniqueId(), key, value);
        this.entity = entity;
    }

    public EntityFlag(Entity entity, String key, boolean value) {
        super(path + "." + entity.getUniqueId(), key, value);
        this.entity = entity;
    }

    public EntityFlag(Entity entity, String key, Number value) {
        super(path + "." + entity.getUniqueId(), key, String.valueOf(value));
        this.entity = entity;
    }

    public EntityFlag(Entity entity, String key) {
        super(path + "." + entity.getUniqueId(), key, null);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }

}
