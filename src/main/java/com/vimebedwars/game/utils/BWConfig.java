package com.vimebedwars.game.utils;

import com.vimebedwars.VimeBedWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class BWConfig {

    private final FileConfiguration cfg = VimeBedWars.getInstance().getConfig();

    private final String name = cfg.getString("name");
    private final Location spawn = getLocation("spawn",true);

    private final Location redVillager = getLocation("teams.red.villager",true);
    private final Location blueVillager = getLocation("teams.blue.villager",true);

    private final Location redBedFirst = getLocation("teams.red.bed.1",false);
    private final Location blueBedFirst = getLocation("teams.blue.bed.1",false);

    private final Location redBedSecond = getLocation("teams.red.bed.2",false);
    private final Location blueBedSecond = getLocation("teams.blue.bed.2",false);

    private final Location redBronzeGenerator = getLocation("teams.red.generators.bronze",false);
    private final Location blueBronzeGenerator = getLocation("teams.blue.generators.bronze",false);

    private final Location redGoldGenerator = getLocation("teams.red.generators.gold",false);
    private final Location blueGoldGenerator = getLocation("teams.blue.generators.gold",false);

    private final Location redIronGenerator = getLocation("teams.red.generators.iron",false);
    private final Location blueIronGenerator = getLocation("teams.blue.generators.iron",false);

    private final Location redSpawn = getLocation("teams.red.spawn",true);
    private final Location blueSpawn = getLocation("teams.blue.spawn",true);

    private final int teamPlayersCount = cfg.getInt("teamPlayersCount");

    public String getName() {
        return name;
    }

    public Location getSpawn() {
        return spawn;
    }

    public Location getRedVillager() {
        return redVillager;
    }

    public Location getBlueVillager() {
        return blueVillager;
    }

    public Location getRedSpawn() {
        return redSpawn;
    }

    public Location getBlueSpawn() {
        return blueSpawn;
    }

    public Location getRedBedFirst() {
        return redBedFirst;
    }

    public Location getBlueBedFirst() {
        return blueBedFirst;
    }

    public Location getRedBedSecond() {
        return redBedSecond;
    }

    public Location getBlueBedSecond() {
        return blueBedSecond;
    }

    public Location getBlueBronzeGenerator() {
        return blueBronzeGenerator;
    }

    public Location getBlueIronGenerator() {
        return blueIronGenerator;
    }

    public Location getRedBronzeGenerator() {
        return redBronzeGenerator;
    }

    public Location getRedIronGenerator() {
        return redIronGenerator;
    }

    public Location getBlueGoldGenerator() {
        return blueGoldGenerator;
    }

    public Location getRedGoldGenerator() {
        return redGoldGenerator;
    }

    public int getTeamPlayersCount() {
        return teamPlayersCount;
    }

    public Location getLocation(String path, boolean player) {
        String[] strings = cfg.getString(path).split(",");

        if (player) {

            double x = Double.parseDouble(strings[0]);
            double y = Double.parseDouble(strings[1]);
            double z = Double.parseDouble(strings[2]);
            double yaw = Double.parseDouble(strings[3]);
            double pitch = Double.parseDouble(strings[4]);

            return new Location(Bukkit.getWorld("world"),x,y,z, (float) yaw, (float) pitch);
        }else {
            double x = Double.parseDouble(strings[0]);
            double y = Double.parseDouble(strings[1]);
            double z = Double.parseDouble(strings[2]);

            return new Location(Bukkit.getWorld("world"),x,y,z);
        }

    }

}
