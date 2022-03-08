package me.playajames.flagslib.flagtypes;

import org.dalesbred.annotation.DalesbredInstantiator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static me.playajames.flagslib.FlagsLib.DAO_INSTANCE;

public class Flag {

    long id;
    String identifier;
    String key;
    String value;
    FlagType type;
    int temp;
    LocalDateTime updated;
    LocalDateTime created;

    public Flag(String identifier, String key, String value, FlagType type, boolean isTemp) {
        this.id = -1;
        this.identifier = identifier;
        this.key = key;
        this.value = value;
        this.type = type;
        this.temp = (isTemp) ? 1 : 0;
        this.updated = LocalDateTime.now();
        this.created = LocalDateTime.now();

        if (identifier == null) return;
        Flag flag = DAO_INSTANCE.insert(this);
        this.id = flag.getId();
    }

    @DalesbredInstantiator
    public Flag(long id, String identifier, String key, String value, String type, boolean isTemp, String updated, String created) {
        this.id = id;
        this.identifier = identifier;
        this.key = key;
        this.value = value;
        this.type = FlagType.valueOf(type);
        this.temp = (isTemp) ? 1 : 0;
        this.updated = LocalDateTime.parse(updated, DateTimeFormatter.ISO_DATE_TIME);
        this.created = LocalDateTime.parse(created, DateTimeFormatter.ISO_DATE_TIME);
    }

    private void save() {
        if (identifier == null) // This ensures method will only run if flag is not stored in a database(eg. ItemFlag is non database)
            return;
        if (this.id != -1)
            DAO_INSTANCE.update(this);
    }

    public long getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
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

    public int isTemp() {
        return temp;
    }

    public void setTemp(int isTemp) {
        this.temp = temp;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    private void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void delete() {
        DAO_INSTANCE.delete(this);
    }

    @Override
    public String toString() { //todo update with allvariables
        return
                "id: "
                + this.id
                + ", identifier: "
                + this.identifier
                + ", key: "
                + this.key
                + ", value: "
                + this.value
                + ", type: "
                + this.type.name()
                + ", temp: "
                + this.temp
                + ", updated: "
                + this.updated.toString()
                + ", created: "
                + this.created.toString();
    }

}
