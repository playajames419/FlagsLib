package me.playajames.flagslib.flagslib.flagtypes;

import me.playajames.flagslib.flagslib.Flag;
import me.playajames.flagslib.flagslib.FlagsDAO;
import org.bukkit.entity.Entity;

public class EntityFlag extends Flag {

    Entity entity;
    public static String path = "entities";

    public EntityFlag(Entity entity, String key, String value) {
        super(key, value);
        this.entity = entity;
    }

    public EntityFlag(Entity entity, String key, boolean value) {
        super(key, value);
        this.entity = entity;
    }

    public EntityFlag(Entity entity, String key, Number value) {
        super(key, String.valueOf(value));
        this.entity = entity;
    }

    public EntityFlag(Entity entity, String key) {
        super(key, null);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }

    @Override
    public String getPath() {
        return path + "." + entity.getUniqueId();
    }

    @Override
    public void save() {
        new FlagsDAO().save(getPath(), getKey(), getValueAsString());
    }

    @Override
    public void delete() {
        new FlagsDAO().delete(getPath(), getKey());
    }
}
