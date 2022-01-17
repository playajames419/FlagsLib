package me.playajames.flagslib;

import me.playajames.easydatabaseconnector.HikariCPFactory;
import me.playajames.easydatabaseconnector.jooq.DSLContext;
import me.playajames.easydatabaseconnector.jooq.Result;
import me.playajames.easydatabaseconnector.jooq.SQLDialect;
import me.playajames.easydatabaseconnector.jooq.impl.DSL;
import me.playajames.easydatabaseconnector.jooq.tables.Flags;
import me.playajames.easydatabaseconnector.jooq.tables.records.FlagsRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FlagsDBDAO {

    public boolean has(String identifier, String name) {
        DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
        return context.fetchExists(
                context
                        .selectFrom(Flags.FLAGS)
                        .where(Flags.FLAGS.IDENTIFIER.eq(identifier))
                        .and(Flags.FLAGS.NAME.eq(name)));
    }


    public boolean has(int id) {
        DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
        return context.fetchExists(
                context
                        .selectFrom(Flags.FLAGS)
                        .where(Flags.FLAGS.ID.eq(id)));
    }


    public FlagsRecord getOne(String identifier, String key) {
        DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
        FlagsRecord record = context
                .selectFrom(Flags.FLAGS)
                .where(Flags.FLAGS.IDENTIFIER.eq(identifier))
                .and(Flags.FLAGS.NAME.eq(key))
                .fetchOne();
        return record;
    }


    public Map<String, FlagsRecord> getMany(String identifier, Set<String> keys) {
        Map<String, FlagsRecord> flagsMap = new HashMap<>();
        if (keys.isEmpty()) return flagsMap;
        for (String key : keys) {
            if (!has(identifier, key)) continue;
            flagsMap.put(key, getOne(identifier, key));
        }
        if(flagsMap.isEmpty()) return null;
        return flagsMap;
    }


    public Result<FlagsRecord> getAllByType(FlagType type) {
        DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
        return context
                .selectFrom(Flags.FLAGS)
                .where(Flags.FLAGS.TYPE.eq(type.name()))
                .fetch();
    }


    public Result<FlagsRecord> getAllByTypeWithKey(FlagType type, String key) {
        DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
        return context
                .selectFrom(Flags.FLAGS)
                .where(Flags.FLAGS.TYPE.eq(type.name()))
                .and(Flags.FLAGS.NAME.eq(key))
                .fetch();
    }


    public Result<FlagsRecord> getAllByTypeWithValue(FlagType type, String value) {
        DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
        return context
                .selectFrom(Flags.FLAGS)
                .where(Flags.FLAGS.TYPE.eq(type.name()))
                .and(Flags.FLAGS.VALUE.eq(value))
                .fetch();
    }


    public Result<FlagsRecord> getAllByTypeWithKeyAndValue(FlagType type, String key, String value) {
        DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
        return context
                .selectFrom(Flags.FLAGS)
                .where(Flags.FLAGS.TYPE.eq(type.name()))
                .and(Flags.FLAGS.NAME.eq(key))
                .and(Flags.FLAGS.VALUE.eq(value))
                .fetch();
    }

    public Result<FlagsRecord> getAllByIdentifier(String identifier) {
        DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
        return context
                .selectFrom(Flags.FLAGS)
                .where(Flags.FLAGS.IDENTIFIER.eq(identifier))
                .fetch();
    }


    public void delete(int id) {
        if (has(id)) {
            DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
            context
                    .delete(Flags.FLAGS)
                    .where(Flags.FLAGS.ID.eq(id))
                    .execute();
        }
    }


    public void delete(int id, Set<String> flags) {
        if (flags.isEmpty()) return;
        for (String name : flags) {
            if (!has(id)) continue;
            delete(id);
        }
    }


    public void update(int id, String value) {
        DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
        context
                .update(Flags.FLAGS)
                .set(Flags.FLAGS.VALUE, value)
                .where(Flags.FLAGS.ID.eq(id))
                .execute();
    }


    public void insert(String identifier, String name, String value, String type) {
        DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
        context
                .insertInto(Flags.FLAGS)
                .set(Flags.FLAGS.IDENTIFIER, identifier)
                .set(Flags.FLAGS.NAME, name)
                .set(Flags.FLAGS.VALUE, value)
                .set(Flags.FLAGS.TYPE, type)
                .execute();
    }

}
