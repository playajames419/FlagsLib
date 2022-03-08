package me.playajames.flagslib.dao;

import com.zaxxer.hikari.HikariDataSource;
import me.playajames.flagslib.FlagsLib;
import me.playajames.flagslib.flagtypes.Flag;
import me.playajames.flagslib.flagtypes.FlagType;
import org.dalesbred.Database;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

import static me.playajames.flagslib.FlagsLib.STORAGETYPE;

public class FlagsDBDAO implements DAO{

    private final Database db;
    private final String databaseTableName = "flagslib_flags";

    public FlagsDBDAO(String driver, String host, int port, String database, boolean useSSL, String user, String password) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:" + driver + "://" + host + ":" + port + "/" + database + "?useSSL=" + useSSL);
        ds.setUsername(user);
        ds.setPassword(password);
        this.db = Database.forDataSource(ds);

        if (!tableExists(databaseTableName)) {
            Logger logger = FlagsLib.getPlugin(FlagsLib.class).getLogger();
            logger.info("Database not found, creating...");
            createTable();
            logger.info("Database created!");
        }
    }

    private boolean tableExists(String tableName) {
        return db.findTable("SHOW TABLES LIKE ?", databaseTableName).getRowCount() > 0;
    }

    private void createTable() {
        if (STORAGETYPE == StorageType.MySQL)
            db.update("CREATE TABLE `" + databaseTableName + "` (" +
                    "  `id` bigint(20) NOT NULL AUTO_INCREMENT," +
                    "  `identifier` varchar(255) NOT NULL," +
                    "  `keyname` varchar(255) NOT NULL," +
                    "  `value` varchar(255) DEFAULT NULL," +
                    "  `type` varchar(255) NOT NULL," +
                    "  `temp` boolean DEFAULT true NOT NULL," +
                    "  `updated` varchar(255) NOT NULL," +
                    "  `created` varchar(255) NOT NULL," +
                    "  PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;");
    }

    @Override
    public Flag insert(Flag flag) {
        if (!has(flag.getIdentifier(), flag.getKey()))
            db.update("INSERT INTO " + databaseTableName + " (identifier, keyname, value, type, temp, updated, created) VALUES (?,?,?,?,?,?,?)",
                    flag.getIdentifier(),
                    flag.getKey(),
                    flag.getValue(),
                    flag.getType(),
                    flag.isTemp(),
                    flag.getUpdated().format(DateTimeFormatter.ISO_DATE_TIME),
                    flag.getCreated().format(DateTimeFormatter.ISO_DATE_TIME));
        return getOne(flag.getIdentifier(), flag.getKey());
    }

    @Override
    public void update(Flag flag) {
        db.updateUnique("UPDATE " + databaseTableName + " SET value=?, temp=?, updated=? WHERE id=?", flag.getValue(), flag.isTemp(), LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME), flag.getId());
    }

    @Override
    public void delete(Flag flag) {
        db.updateUnique("DELETE FROM " + databaseTableName + " WHERE id=?", flag.getId());
    }

    @Override
    public boolean has(String identifier, String key) {
        return !db.findAll(Flag.class,"SELECT * FROM " + databaseTableName + " WHERE identifier=? AND keyname=?", identifier, key).isEmpty();
    }

    @Override
    public Flag getOne(String identifier, String key) {
        return db.findUnique(Flag.class, "SELECT * FROM " + databaseTableName + " WHERE identifier=? AND keyname=?", identifier, key);
    }

    @Override
    public List<Flag> getManyWithKey(String key) {
        List<Flag> results = db.findAll(Flag.class, "SELECT * FROM " + databaseTableName + " WHERE keyname=?", key);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyWithValue(String value) {
        List<Flag> results = db.findAll(Flag.class, "SELECT * FROM " + databaseTableName + " WHERE value=?", value);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyWithKeyAndValue(String key, String value) {
        List<Flag> results = db.findAll(Flag.class, "SELECT * FROM " + databaseTableName + " WHERE keyname=? AND value=?", key, value);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyByType(FlagType type) {
        List<Flag> results = db.findAll(Flag.class, "SELECT * FROM " + databaseTableName + " WHERE type=?", type.name());
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyByTypeWithKey(FlagType type, String key) {
        List<Flag> results = db.findAll(Flag.class, "SELECT * FROM " + databaseTableName + " WHERE type=? AND keyname=?", type.name(), key);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyByTypeWithValue(FlagType type, String value) {
        List<Flag> results = db.findAll(Flag.class, "SELECT * FROM " + databaseTableName + " WHERE type=? AND value=?", type.name(), value);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyByTypeWithKeyAndValue(FlagType type, String key, String value) {
        List<Flag> results = db.findAll(Flag.class, "SELECT * FROM " + databaseTableName + " WHERE type=? AND keyname=? AND value=?", type.name(), key, value);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyByTypeWithIdentifier(FlagType type, String identifier) {
        List<Flag> results = db.findAll(Flag.class, "SELECT * FROM " + databaseTableName + " WHERE type=? AND identifier=?", type.name(), identifier);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyByIdentifier(String identifier) {
        List<Flag> results = db.findAll(Flag.class, "SELECT * FROM " + databaseTableName + " WHERE identifier=?", identifier);
        return (results.isEmpty()) ? null : results;
    }

}
