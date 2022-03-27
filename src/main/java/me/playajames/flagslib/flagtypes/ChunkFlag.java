package me.playajames.flagslib.flagtypes;

import me.playajames.flagslib.utils.IdentifierGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;

import javax.annotation.Nullable;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ChunkFlag extends Flag {

    final Chunk chunk;

    public ChunkFlag(Chunk chunk, String key, String value, boolean isTemp) {
        super(IdentifierGenerator.generate(chunk), key, value, FlagType.Chunk, isTemp);
        this.chunk = chunk;
    }

    public ChunkFlag(int id, String identifier, String key, @Nullable String value, String type, boolean isTemp, String updated, String created) {
        super(id, identifier, key, value, type, isTemp, updated, created);
        String[] identifierArray = identifier.split(":");
        chunk = Objects.requireNonNull(Bukkit.getWorld(identifierArray[0])).getChunkAt(Long.parseLong(identifierArray[1]));
    }

    public ChunkFlag(Flag flag) {
        super(flag.getId(), flag.getIdentifier(), flag.getKey(), flag.getValue(), flag.getType().name(), flag.isTemp(), flag.getUpdated().format(DateTimeFormatter.ISO_DATE_TIME), flag.getCreated().format(DateTimeFormatter.ISO_DATE_TIME));
        String[] identifierArray = identifier.split(":");
        chunk = Objects.requireNonNull(Bukkit.getWorld(identifierArray[0])).getChunkAt(Long.parseLong(identifierArray[1]));
    }

    public Chunk getChunk() {
        return this.chunk;
    }

}
