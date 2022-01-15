package me.playajames.flagslib.flagslib;

import de.leonhard.storage.Yaml;

import java.util.*;

public class FlagsFileDAO {

    //todo probably broken need to test and fix added updated and created fields to flag and should keep track of flag type

    private final String fileName = "flags-storage";
    private final String dataFolderPath = FlagsLib.getPlugin(FlagsLib.class).getDataFolder().getPath() + "/";

    public boolean has(String id, String key) {
        Yaml yaml = new Yaml(fileName, dataFolderPath);
        return yaml.contains(id + "." + key);
    }

    public String get(String id, String key) {
        Yaml yaml = new Yaml(fileName, dataFolderPath);
        if (!has(id, key)) return null;
        return yaml.getString(id + "." + key);
    }

    public Map<String, String> get(String id, Set<String> keys) {
        if (keys.isEmpty()) return null;
        Map<String, String> flagsMap = new HashMap<>();
        for (String key : keys) {
            if (!has(id, key)) continue;
            flagsMap.put(key, get(id, key));
        }
        if(flagsMap.isEmpty()) return null;
        return flagsMap;
    }

    public void save(String identifier, String key, String value, FlagType type) {
        Yaml yaml = new Yaml(fileName, dataFolderPath);
        yaml.set(identifier + "." + key,  value);
    }

    public void save(String identifier, Map<String, String> flags) {
        if (flags.isEmpty()) return;
        for (Map.Entry<String, String> entry: flags.entrySet())
            save(identifier, entry.getKey(), entry.getValue(), null); // Null type until type is implemented above
    }

    public void delete(String identifier, String key) {
        if (!has(identifier, key)) return;
        Yaml yaml = new Yaml(fileName, dataFolderPath);
        yaml.remove(identifier + "." + key);
    }

    public void delete(String identifier, Set<String> flags) {
        if (flags.isEmpty()) return;
        for (String key : flags) {
            if (!has(identifier, key)) continue;
            delete(identifier, key);
        }
    }

}
