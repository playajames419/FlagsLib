package me.playajames.flagslib.flagslib.flagtypes;

import me.playajames.flagslib.flagslib.Flag;
import me.playajames.flagslib.flagslib.FlagType;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Objects;

public class ChunkFlag extends Flag {

    final Chunk chunk;

    public ChunkFlag(Chunk chunk, String key, String value) {
        super(chunk.getWorld().getUID() + ":" + chunk.getChunkKey(), key, value, FlagType.Chunk);
        this.chunk = chunk;
    }


    public ChunkFlag(Chunk chunk, String key) {
        super(chunk.getWorld().getUID() + ":" + chunk.getChunkKey(), key, null, FlagType.Chunk);
        this.chunk = chunk;
    }


    public ChunkFlag(int id, String identifier, String key, @Nullable String value, FlagType type, LocalDateTime updated, LocalDateTime created) {
        super(id, identifier, key, value, type, updated, created);
        String[] identifierArray = identifier.split(":");
        chunk = Objects.requireNonNull(Bukkit.getWorld(identifierArray[0])).getChunkAt(Long.parseLong(identifierArray[1]));
    }


    public Chunk getChunk() {
        return this.chunk;
    }

}
