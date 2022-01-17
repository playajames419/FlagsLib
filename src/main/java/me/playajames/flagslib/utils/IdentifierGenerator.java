package me.playajames.flagslib.utils;

import org.bukkit.Chunk;

public class IdentifierGenerator {

    public static String generate(Chunk chunk) {
        return chunk.getWorld().getUID() + ":" + chunk.getChunkKey();
    }
}
