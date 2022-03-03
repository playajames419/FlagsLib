package me.playajames.flagslib.flagtypes;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

public class ItemFlag extends Flag {

    NBTItem nbti;

<<<<<<< HEAD
    public ItemFlag(ItemStack item, String key, String value, boolean isTemp) { //todo needs to be rewritten this class should not interact with the database at all
        super(null, key, value, FlagType.Item, isTemp); // currently this method will create a new row in the database
        this.nbti = new NBTItem(item);                             //pretty much all flag(modifiable?) fields need to be overwritten
        nbti.setBoolean("flagslib-flag", true);
=======
    public ItemFlag(ItemStack item, String key, String value) {
        super(null, key, value, FlagType.Item);
        this.nbti = new NBTItem(item);
>>>>>>> rewrite
        nbti.setString(key, value);
        nbti.setBoolean("temp", isTemp);
    }

    protected ItemFlag(ItemStack item, String key) {
        super(null, key, null, FlagType.Item);
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

}
