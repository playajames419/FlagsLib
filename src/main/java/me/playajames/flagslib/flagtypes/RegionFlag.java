package me.playajames.flagslib.flagtypes;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.playajames.flagslib.utils.IdentifierGenerator;
import org.bukkit.Bukkit;
import org.bukkit.World;

import javax.annotation.Nullable;
import java.time.format.DateTimeFormatter;

public class RegionFlag extends Flag {

    final ProtectedRegion region;

    public RegionFlag(World world, ProtectedRegion region, String key, String value, boolean isTemp) {
        super(IdentifierGenerator.generate(world, region), key, value, FlagType.Region, isTemp);
        this.region = region;
    }

    public RegionFlag(int id, String identifier, String key, @Nullable String value, String type, boolean isTemp, String updated, String created) {
        super(id, identifier, key, value, type, isTemp, updated, created);
        String[] identifierArray = identifier.split(":");
        region = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(Bukkit.getWorld(identifierArray[0]))).getRegion(identifierArray[1]);
    }

    public RegionFlag(Flag flag) {
        super(flag.getId(), flag.getIdentifier(), flag.getKey(), flag.getValue(), flag.getType().name(), flag.isTemp(), flag.getUpdated().format(DateTimeFormatter.ISO_DATE_TIME), flag.getCreated().format(DateTimeFormatter.ISO_DATE_TIME));
        String[] identifierArray = identifier.split(":");
        region = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(Bukkit.getWorld(identifierArray[0]))).getRegion(identifierArray[1]);
    }

    public ProtectedRegion getRegion() {
        return this.region;
    }

}
