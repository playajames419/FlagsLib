package me.playajames.flagslib.flagslib.flagtypes;

import de.tr7zw.nbtapi.NBTItem;
import me.playajames.flagslib.flagslib.Flag;
import me.playajames.flagslib.flagslib.FlagType;
import org.bukkit.inventory.ItemStack;

public class ItemFlag extends Flag {

    NBTItem nbti;


    public ItemFlag(ItemStack item, String key, String value) {
        super(null, FlagType.Item, key, value);
        this.nbti = new NBTItem(item);
        nbti.setString(key, value);
    }


    public ItemFlag(ItemStack item, String key) {
        super(null, FlagType.Item, key, null);
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
    public void delete() {
        nbti.removeKey(getKey());
    }

}
