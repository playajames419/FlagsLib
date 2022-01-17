package me.playajames.flagslib;

import me.playajames.easydatabaseconnector.jooq.Result;
import me.playajames.easydatabaseconnector.jooq.tables.records.FlagsRecord;

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
}
