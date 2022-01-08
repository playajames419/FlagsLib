package me.playajames.flagslib.flagslib;

import javax.annotation.Nullable;

import static me.playajames.flagslib.flagslib.FlagsLib.STORAGETYPE;

public abstract class Flag {


    final String id;
    final FlagType type;
    final String key;
    String value;


    public Flag(String id, FlagType type, String key, @Nullable String value) {
        this.id = id;
        this.type = type;
        this.key = key;
        this.value = value;
        save();
    }


    /** getId() will return null when saving is not required. Example: ItemFlags save using NBT which is already persistent. **/
    public String getId() {
        return id;
    }


    public String getKey() {
        return key;
    }


    public void setValue(String value) {
        this.value = value;
        save();
    }


    public String getValue() {
        return this.value;
    }

    public FlagType getType() {
        return type;
    }

    private void save() {
        if (this.id != null)
            if (STORAGETYPE.equals(StorageType.File))
                new FlagsFileDAO().save(this.id, this.type, this.key, this.value);
            else if (STORAGETYPE.equals(StorageType.MySQL))
                new FlagsDBDAO().save(this.id, this.key, this.value, this.type);
    }


    public void delete() {
        if (STORAGETYPE.equals(StorageType.File))
            new FlagsFileDAO().delete(this.id, this.key);
        else if (STORAGETYPE.equals(StorageType.MySQL))
            new FlagsDBDAO().delete(this.id, this.key);
    }

}
