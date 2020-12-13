package me.playajames.flagslib.flagslib;

import javax.annotation.Nullable;

public abstract class Flag {

    final String path;
    final String key;
    String value;

    public Flag(String path, String key, @Nullable String value) {
        this.path = path;
        this.key = key;
        this.value = value;
        save();
    }

    public Flag(String path, String key, boolean value) {
        this.path = path;
        this.key = key;
        this.value = String.valueOf(value);
        save();
    }

    public String getPath() {
        return path;
    }

    public String getKey() {
        return key;
    }

    public void setValue(String value) {
        this.value = value;
        save();
    }

    public String getValueAsString() {
        return value;
    }

    public void setValue(int value) {
        this.value = String.valueOf(value);
        save();
    }

    public int getValueAsInt() {
        return Integer.parseInt(value);
    }

    public void setValue(double value) {
        this.value = String.valueOf(value);
        save();
    }

    public double getValueAsDouble() {
        return Double.parseDouble(value);
    }

    public void setValue(float value) {
        this.value = String.valueOf(value);
        save();
    }

    public float getValueAsFloat() {
        return Float.parseFloat(value);
    }

    public void setValue(boolean value) {
        this.value = String.valueOf(value);
        save();
    }

    public boolean getValueAsBoolean() {
        return Boolean.valueOf(value);
    }

    /** getPath() will return null when saving is not required. Example: ItemFlags save using NBT which is already persistent. **/

    private void save() {
        if (path != null)
            new FlagsDAO().save(path, getKey(), getValueAsString());
    }

    public void delete() {
        new FlagsDAO().delete(path, getKey());
    }

}
