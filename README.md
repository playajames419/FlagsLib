![logo](https://i.imgur.com/ci0RatM.png)

# FlagsLib
A minecraft library made for developers that makes it easy to attach persistent data to entities, locations, and items.

## Examples
![example-1](https://i.imgur.com/BAxBCGD.png)
![example-2](https://i.imgur.com/dl0lP4e.png)
![example-3](https://i.imgur.com/O12xKHR.png)

## Documentation
#### FlagManager.class
* `setFlag(Player|Location, String, Value)` Set a flag's value on a player or location, flag will be created if not already.
* `hasFlag(Player|Location, String)` Return's boolean.
* `getFlag(Player|Location, String)` Return's a flag, null if not found.

#### Flag.class
* `getValueAsString()` Return's value as a string.
* `getValueAsInt()` Return's value as a int.
* `getValueAsDouble` Return's value as double.
* `getValueAsFloat` Return's value as float.
* `delete()` Delete's the flag.

## Maven
#### Repository
```
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

#### Dependency
```
<dependency>
    <groupId>com.github.playajames419</groupId>
    <artifactId>FlagsLib</artifactId>
    <version>1.1-SNAPSHOT</version>
</dependency>
```

## Release History
* 1.1-SNAPSHOT - Added ItemStack support. 
* 1.0-SNAPSHOT - Pre-release, there may be bugs. 

## License
Licensed under the GPL-3.0 license.
