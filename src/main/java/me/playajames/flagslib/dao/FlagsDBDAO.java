package me.playajames.flagslib.dao;

import com.dieselpoint.norm.Database;
import me.playajames.flagslib.flagtypes.Flag;
import me.playajames.flagslib.flagtypes.FlagType;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FlagsDBDAO implements DAO{

<<<<<<< HEAD
    private final Database db = new Database();

    public FlagsDBDAO(String driver, String host, int port, String database, boolean useSSL, String user, String password) {
        if (driver.equalsIgnoreCase("sqlite"))
            db.setJdbcUrl("jdbc:" + driver + ":" + database);
        else {
            db.setJdbcUrl("jdbc:" + driver + "://" + host + ":" + port + "/" + database + "?useSSL=" + useSSL);
            db.setUser(user);
            db.setPassword(password);
        }

        if (!tableExists("flagslib-flags")) {
            db.createTable(Flag.class);
        }
=======
    private Database db;

    public FlagsDBDAO(String driver, String host, int port, String database, boolean useSSL, String user, String password) {
        this.db = new Database();
        db.setJdbcUrl("jdbc:" + driver + "://" + host + ":" + port + "/" + database + "?useSSL=" + useSSL);
        db.setUser(user);
        db.setPassword(password);
    }

    private void createTable() {
        if (!tableExists("flags"));
            db.createTable(Flag.class);
>>>>>>> rewrite
    }

    private boolean tableExists(String tableName) {
        DatabaseMetaData meta = null;
        try {
            meta = db.getConnection().getMetaData();
            ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Flag insert(Flag flag) {
        return db.insert(flag).first(Flag.class);
    }

    @Override
    public Flag update(Flag flag) {
        return db.update(flag).first(Flag.class);
    }

    @Override
    public void delete(Flag flag) {
        db.delete(flag);
    }

    @Override
    public boolean has(String identifier, String key) {
        return !db.where("identifier=?, key=?", identifier, key).results(Flag.class).isEmpty();
    }


    @Override
    public Flag getOne(String identifier, String key) {
        List<Flag> results= db.where("identifier=?, key=?", identifier, key).results(Flag.class);
        return (results.isEmpty()) ? null : results.get(0);
    }

    @Override
    public List<Flag> getManyWithKey(String key) {
        List<Flag> results = db.where("key=?", key).results(Flag.class);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyWithValue(String value) {
        List<Flag> results = db.where("value=?", value).results(Flag.class);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyWithKeyAndValue(String key, String value) {
        List<Flag> results = db.where("key=?, value=?", key, value).results(Flag.class);
        return (results.isEmpty()) ? null : results;
    }


    @Override
    public List<Flag> getManyByType(FlagType type) {
        List<Flag> results = db.where("flagtype=?", type).results(Flag.class);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyByTypeWithKey(FlagType type, String key) {
        List<Flag> results = db.where("flagtype=?, key=?", type, key).results(Flag.class);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyByTypeWithValue(FlagType type, String value) {
        List<Flag> results = db.where("flagtype=?, value=?", type, value).results(Flag.class);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyByTypeWithKeyAndValue(FlagType type, String key, String value) {
        List<Flag> results = db.where("flagtype=?, key=?, value=?", type, key, value).results(Flag.class);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyByTypeWithIdentifier(FlagType type, String identifier) {
        List<Flag> results = db.where("flagtype=?, identifier=?", type, identifier).results(Flag.class);
        return (results.isEmpty()) ? null : results;
    }

    @Override
    public List<Flag> getManyByIdentifier(String identifier) {
        List<Flag> results = db.where("identifier=?", identifier).results(Flag.class);
        return (results.isEmpty()) ? null : results;
    }

}
