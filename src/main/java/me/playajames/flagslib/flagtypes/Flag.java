package me.playajames.flagslib.flagtypes;

import javax.annotation.Nullable;
import javax.persistence.*;

import java.time.LocalDateTime;

import static me.playajames.flagslib.FlagsLib.DAO_INSTANCE;

@Table(name="flagslib-flags")
public class Flag {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    final String identifier;

    @Column(nullable = false)
    final String key;

    @Column
    String value;

    @Column(nullable = false)
    @Enumerated
    final FlagType type;

    @Column(nullable = false)
    boolean temp;

    @Column
    @GeneratedValue
    LocalDateTime updated;

    @Column
    @GeneratedValue
    LocalDateTime created;

    public Flag(String identifier, String key, @Nullable String value, FlagType type, boolean isTemp) {
        this.id = -1;
        this.identifier = identifier;
        this.key = key;
        this.value = value;
        this.type = type;
        this.temp = isTemp;

        Flag flag = DAO_INSTANCE.insert(this);

        this.id = flag.getId();
        this.updated = flag.getUpdated();
        this.created = flag.getCreated();
    }

    public Flag(int id, String identifier, String key, @Nullable String value, FlagType type, boolean isTemp, LocalDateTime updated, LocalDateTime created) {
        this.id = id;
        this.identifier = identifier;
        this.key = key;
        this.value = value;
        this.type = type;
        this.temp = isTemp;
        this.updated = updated;
        this.created = created;
    }

    public void save() {
        if (this.id != -1)
            DAO_INSTANCE.update(this);
    }

    public int getId() {
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

    public boolean isTemp() {
        return temp;
    }

    public void setTemp(boolean isTemp) {
        this.temp = isTemp;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return
                this.id
                + ", "
                + this.identifier
                + ", "
                + this.key
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
