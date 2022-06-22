package me.playajames.flagslib.utils;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Chunk;
import org.bukkit.World;

public class IdentifierGenerator {

    public static String generate(Chunk chunk) {
        return chunk.getWorld().getUID() + ":" + chunk.getChunkKey();
    }
    public static String generate(World world, ProtectedRegion region) {
        return world.getName() + ":" + region.getId();
    }

}
