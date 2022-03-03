package me.playajames.flagslib.dao;

import me.playajames.flagslib.flagtypes.Flag;
import me.playajames.flagslib.flagtypes.FlagType;

import java.util.List;

public interface DAO {

    Flag insert(Flag flag);
    Flag update(Flag flag);
    void delete(Flag flag);
    boolean has(String identifier, String key);
    Flag getOne(String identifier, String key);
    List<Flag> getManyWithKey(String key);
    List<Flag> getManyWithValue(String value);
    List<Flag> getManyWithKeyAndValue(String key, String value);
    List<Flag> getManyByType(FlagType type);
    List<Flag> getManyByTypeWithKey(FlagType type, String key);
    List<Flag> getManyByTypeWithValue(FlagType type, String value);
    List<Flag> getManyByTypeWithKeyAndValue(FlagType type, String key, String value);
    List<Flag> getManyByTypeWithIdentifier(FlagType type, String identifier);
    List<Flag> getManyByIdentifier(String identifier);


}
