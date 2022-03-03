package me.playajames.flagslib.flagtypes;

import me.playajames.flagslib.utils.IdentifierGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Objects;

public class ChunkFlag extends Flag {

    final Chunk chunk;

    public ChunkFlag(Chunk chunk, String key, String value, boolean isTemp) {
        super(IdentifierGenerator.generate(chunk), key, value, FlagType.Chunk, isTemp);
        this.chunk = chunk;
    }


    public ChunkFlag(int id, String identifier, String key, @Nullable String value, FlagType type, boolean isTemp, LocalDateTime updated, LocalDateTime created) {
        super(id, identifier, key, value, type, isTemp, updated, created);
        String[] identifierArray = identifier.split(":");
        chunk = Objects.requireNonNull(Bukkit.getWorld(identifierArray[0])).getChunkAt(Long.parseLong(identifierArray[1]));
    }


    public Chunk getChunk() {
        return this.chunk;
    }

}
