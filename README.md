![logo](https://i.imgur.com/ci0RatM.png)

# FlagsLib
A Minecraft library made for developers to make it easy and effortless to attach persistent data to entities, locations, chunks, worlds, and more.

## Examples
![example-1](https://i.imgur.com/PtqLz3U.png)
![example-2](https://i.imgur.com/YXV55RC.png)
![example-3](https://i.imgur.com/E5dVVLA.png)

## Setup
You may shade this plugin or just add the jar to your plugins folder.

### Config.yml
Run your server to generate a config.yml file. Update the config with your MySQL database details and your all set.

### FlagManager
This is how you interact with this API, access if via FlagManager static context.
#### Methods
In the following methods "Identifier" accepts String, Entity, Location, and Chunk.
* `createFlag(Identifier, String, Value)` Create's a new flag returning the new flag, will return null if flag already exists.
* `hasFlag(Identifier, String)` Return's boolean stating whether flag esists or not.
* `getFlag(Identifier, String)` Return's a flag, null if not found.
* `getAllFlagsByType(FlagType type)` Returns list of flags with specified type.
* `getAllFlagsByTypeWithKey(FlagType type, String key)` Returns list of flags with specified type and key.
* `getAllFlagsByTypeWithValue(FlagType type, String value)` Returns list of flags with specified type and value.
* `getAllFlagsByTypeWithKeyAndValue(FlagType type, String key, String value)` Returns list of flags with specified type, key, and value.
* `getAllFlags(Identifier)` Returns list of flags with specified Identifier.

### Flags
* `getId()` Return's unique ID of flag.
* `getIdentifier()` Return's flags identifier.
* `getKey()` Return's flags key.
* `setValue(String value)` Set's flags value.
* `getValue()` Return's flags value.
* `getType()` Return's flags FlagType
* `isTemp()` Return's if flag is temporary or not(Won't change anything, not implemented yet).
* `setTemp(int isTemp)` Set's flag to temporary or not(Won't change anything, not implemented yet).
* `getUpdated()` Return's LocalDateTime of last time flag was modified.
* `getCreated()` Return's LocalDateTime of when the flag was initially created.
* `delete()` Delete's the flag from storage.

## Maven
#### Repository
```
<repository>
  <id>tdsmc-repo-public</id>
  <name>TDSMC Repository</name>
  <url>http://maven.tdsmc.com:8080/public</url>
</repository>
```

#### Dependency
```
<dependency>
  <groupId>me.playajames.flags-lib</groupId>
  <artifactId>flags-lib</artifactId>
  <version>1.0.1-SNAPSHOT</version>
</dependency>
```

## Release History
* 1.0.0-SNAPSHOT - Re-Release rewritten from ground up. 

## License
Licensed under the GPL-3.0 license.
