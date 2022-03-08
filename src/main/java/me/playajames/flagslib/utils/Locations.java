package me.playajames.flagslib.utils;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.StringJoiner;

public class Locations {

    public static String serialize(Location location, boolean world, boolean floats) {
        Validate.notNull(location, "Location cannot be null");
        if(world) Validate.notNull(location.getWorld(), "Location's World cannot be null");
        StringJoiner joiner = new StringJoiner(",");
        if(world) joiner.add(location.getWorld().getName());
        joiner.add(String.valueOf(location.getX())).add(String.valueOf(location.getY())).add(String.valueOf(location.getZ()));
        if(floats) joiner.add(String.valueOf(location.getYaw())).add(String.valueOf(location.getPitch()));
        return joiner.toString();
    }

    public static Location deserialize(String string) {
        Validate.notNull(string, "string cannot be null");
        String[] args = string.split(",");
        if(args.length == 3) return new Location(null, Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
        if(args.length == 4) return new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
        if(args.length == 5) return new Location(null, Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]),
                Float.parseFloat(args[3]), Float.parseFloat(args[4]));
        if(args.length == 7) return new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]),
                Float.parseFloat(args[4]), Float.parseFloat(args[5]));
        return null;
    }


}
