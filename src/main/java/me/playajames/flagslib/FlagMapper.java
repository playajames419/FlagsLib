package me.playajames.flagslib;

import me.playajames.easydatabaseconnector.jooq.Result;
import me.playajames.easydatabaseconnector.jooq.tables.records.FlagsRecord;
import me.playajames.flagslib.flagtypes.ChunkFlag;
import me.playajames.flagslib.flagtypes.EntityFlag;
import me.playajames.flagslib.flagtypes.GlobalFlag;
import me.playajames.flagslib.flagtypes.LocationFlag;

import java.util.ArrayList;
import java.util.List;


public class FlagMapper {

    public Flag mapOne(FlagsRecord record) {
        return new Flag(
                record.getId(),
                record.getIdentifier(),
                record.getName(),
                record.getValue(),
                FlagType.valueOf(record.getType()),
                record.getUpdated(),
                record.getCreated());
    }


    public List<Flag> mapMany(Result<FlagsRecord> results) {
        List<Flag> flags = new ArrayList<>();
        for (FlagsRecord record : results) {
            flags.add(
                    new Flag(
                            record.getId(),
                            record.getIdentifier(),
                            record.getName(),
                            record.getValue(),
                            FlagType.valueOf(record.getType()),
                            record.getUpdated(),
                            record.getCreated())
            );
        }
        return flags;
    }


    public List<EntityFlag> mapManyEntity(Result<FlagsRecord> results) {
        List<EntityFlag> flags = new ArrayList<>();
        for (FlagsRecord record : results) {
            flags.add(
                    new EntityFlag(
                            record.getId(),
                            record.getIdentifier(),
                            record.getName(),
                            record.getValue(),
                            FlagType.valueOf(record.getType()),
                            record.getUpdated(),
                            record.getCreated())
            );
        }
        return flags;
    }


    public List<LocationFlag> mapManyLocation(Result<FlagsRecord> results) {
        List<LocationFlag> flags = new ArrayList<>();
        for (FlagsRecord record : results) {
            flags.add(
                    new LocationFlag(
                            record.getId(),
                            record.getIdentifier(),
                            record.getName(),
                            record.getValue(),
                            FlagType.valueOf(record.getType()),
                            record.getUpdated(),
                            record.getCreated())
            );
        }
        return flags;
    }


    public List<ChunkFlag> mapManyChunk(Result<FlagsRecord> results) {
        List<ChunkFlag> flags = new ArrayList<>();
        for (FlagsRecord record : results) {
            flags.add(
                    new ChunkFlag(
                            record.getId(),
                            record.getIdentifier(),
                            record.getName(),
                            record.getValue(),
                            FlagType.valueOf(record.getType()),
                            record.getUpdated(),
                            record.getCreated())
            );
        }
        return flags;
    }


    public List<GlobalFlag> mapManyGlobal(Result<FlagsRecord> results) {
        List<GlobalFlag> flags = new ArrayList<>();
        for (FlagsRecord record : results) {
            flags.add(
                    new GlobalFlag(
                            record.getId(),
                            record.getIdentifier(),
                            record.getName(),
                            record.getValue(),
                            FlagType.valueOf(record.getType()),
                            record.getUpdated(),
                            record.getCreated())
            );
        }
        return flags;
    }


}
