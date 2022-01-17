package me.playajames.flagslib.flagslib;

import me.playajames.easydatabaseconnector.jooq.tables.records.FlagsRecord;

import javax.annotation.Nullable;

import java.time.LocalDateTime;

import static me.playajames.flagslib.flagslib.FlagsLib.STORAGETYPE;

public class Flag {

    int id;
    final String identifier;
    final String name;
    String value;
    final FlagType type;
    LocalDateTime updated;
    LocalDateTime created;


    public Flag(String identifier, String key, @Nullable String value, FlagType type) {
        this.id = -1;
        this.identifier = identifier;
        this.name = key;
        this.value = value;
        this.type = type;
        this.updated = null; //todo implement this for file storage
        this.created = null; //todo implement this for file storage
    }

    public Flag(int id, String identifier, String key, @Nullable String value, FlagType type, LocalDateTime updated, LocalDateTime created) {
        this.id = id;
        this.identifier = identifier;
        this.name = key;
        this.value = value;
        this.type = type;
        this.updated = updated;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }


    public String getKey() {
        return name;
    }


    public void setValue(String value, boolean updateDatabase) {
        this.value = value;
        if (updateDatabase)
            save();
    }


    public String getValue() {
        return this.value;
    }

    public FlagType getType() {
        return type;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void save() {
        if (STORAGETYPE.equals(StorageType.File))
            new FlagsFileDAO().save(this.identifier, this.name, this.value, this.type);
        else if (STORAGETYPE.equals(StorageType.MySQL)) {
            if (this.id == -1) {
                new FlagsDBDAO().insert(this.identifier, this.name, this.value, this.type.name());
                updateWithDatabaseFields();
            } else
                new FlagsDBDAO().update(this.id, this.value);
        }
    }


    public void delete() {
        if (STORAGETYPE.equals(StorageType.File))
            new FlagsFileDAO().delete(this.identifier, this.name);
        else if (STORAGETYPE.equals(StorageType.MySQL))
            new FlagsDBDAO().delete(this.id);
    }

    private void updateWithDatabaseFields() {
        FlagsRecord record = new FlagsDBDAO().getOne(this.identifier, this.getKey());
        this.id = record.getId();
        this.updated = record.getUpdated();
        this.created = record.getCreated();
    }

    @Override
    public String toString() {
        return
                this.id
                + ", "
                + this.identifier
                + ", "
                + this.name
                + ", "
                + this.value
                + ", "
                + this.type.name()
                + ", "
                + this.updated.toString()
                + ", "
                + this.created.toString();
    }

}
