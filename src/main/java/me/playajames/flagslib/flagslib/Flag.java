package me.playajames.flagslib.flagslib;

import javax.annotation.Nullable;

public abstract class Flag {

    final String key;
    String value;

    public Flag(String key, @Nullable String value) {
        this.key = key;
        this.value = value;
    }

    public Flag(String key, boolean value) {
        this.key = key;
        this.value = String.valueOf(value);
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
        return Boolean.getBoolean(value);
    }

    public abstract String getPath();

    public abstract void save();

    public abstract void delete();

}
