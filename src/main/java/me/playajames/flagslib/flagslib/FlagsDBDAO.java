package me.playajames.flagslib.flagslib;

import me.playajames.easydatabaseconnector.HikariCPFactory;
import me.playajames.easydatabaseconnector.jooq.DSLContext;
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

    public String get(String identifier, String name) {
        DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
        FlagsRecord record = context
                .selectFrom(Flags.FLAGS)
                .where(Flags.FLAGS.IDENTIFIER.eq(identifier))
                .and(Flags.FLAGS.NAME.eq(name))
                .fetchOne();
        return record.getValue();
    }

    public Map<String, String> get(String identifier, Set<String> names) {
        if (names.isEmpty()) return null;
        Map<String, String> flagsMap = new HashMap<>();
        for (String name : names) {
            if (!has(identifier, name)) continue;
            flagsMap.put(name, get(identifier, name));
        }
        if(flagsMap.isEmpty()) return null;
        return flagsMap;
    }

    public void save(String identifier, String name, String value, FlagType type) {
        if (has(identifier, name))
            update(identifier, name, value, type.name());
        else
            insert(identifier, name, value, type.name());
    }

    public void delete(String identifier, String name) {
        if (has(identifier, name)) {
            DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
            context
                    .delete(Flags.FLAGS)
                    .where(Flags.FLAGS.IDENTIFIER.eq(identifier))
                    .and(Flags.FLAGS.NAME.eq(name))
                    .execute();
        }
    }

    public void delete(String identifier, Set<String> flags) {
        if (flags.isEmpty()) return;
        for (String name : flags) {
            if (!has(identifier, name)) continue;
            delete(identifier, name);
        }
    }

    private void update(String identifier, String name, String value, String type) {
        DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
        context
                .update(Flags.FLAGS)
                .set(Flags.FLAGS.VALUE, value)
                .where(Flags.FLAGS.IDENTIFIER.eq(identifier))
                .and(Flags.FLAGS.NAME.eq(name))
                .execute();
    }

    private void insert(String identifier, String name, String value, String type) {
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
