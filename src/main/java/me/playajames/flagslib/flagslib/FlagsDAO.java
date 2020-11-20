package me.playajames.flagslib.flagslib;

import de.leonhard.storage.Yaml;

import java.util.*;

public class FlagsDAO {

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

    public void save(String id, String key, String value) {
        Yaml yaml = new Yaml(fileName, dataFolderPath);
        yaml.set(id + "." + key, value);
    }

    public void save(String id, Map<String, String> flags) {
        if (flags.isEmpty()) return;
        for (Map.Entry<String, String> entry: flags.entrySet())
            save(id, entry.getKey(), entry.getValue());
    }

    public void delete(String id, String key) {
        if (!has(id, key)) return;
        Yaml yaml = new Yaml(fileName, dataFolderPath);
        yaml.remove(id + "." + key);
    }

    public void delete(String id, Set<String> flags) {
        if (flags.isEmpty()) return;
        for (String key : flags) {
            if (!has(id, key)) continue;
            delete(id, key);
        }
    }

}
