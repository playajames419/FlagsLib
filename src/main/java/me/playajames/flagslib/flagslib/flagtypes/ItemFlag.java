package me.playajames.flagslib.flagslib.flagtypes;

import de.tr7zw.nbtapi.NBTItem;
import me.playajames.flagslib.flagslib.Flag;
import org.bukkit.inventory.ItemStack;

public class ItemFlag extends Flag {

    NBTItem nbti;

    public ItemFlag(ItemStack item, String key, String value) {
        super(null, key, value);
        this.nbti = new NBTItem(item);
        nbti.setString(key, value);
    }

    public ItemFlag(ItemStack item, String key, boolean value) {
        super(null, key, value);
        this.nbti = new NBTItem(item);
        nbti.setString(key, String.valueOf(value));
    }

    public ItemFlag(ItemStack item, String key, Number value) {
        super(null, key, String.valueOf(value));
        this.nbti = new NBTItem(item);
        nbti.setString(key, String.valueOf(value));
    }

    public ItemFlag(ItemStack item, String key) {
        super(null, key, null);
        this.nbti = new NBTItem(item);
        nbti.setString(key, null);
    }

    public ItemStack getItem() {
        return nbti.getItem();
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
        nbti.setString(getKey(), value);
    }

    @Override
    public void setValue(int value) {
        super.setValue(value);
        nbti.setInteger(getKey(), value);
    }

    @Override
    public void setValue(double value) {
        super.setValue(value);
        nbti.setDouble(getKey(), value);
    }

    @Override
    public void setValue(float value) {
        super.setValue(value);
        nbti.setFloat(getKey(), value);
    }

    @Override
    public void setValue(boolean value) {
        super.setValue(value);
        nbti.setString(getKey(), String.valueOf(value));
    }

    @Override
    public void delete() {
        nbti.removeKey(getKey());
    }

}
