package com.vimebedwars.game.object.manager;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;

public class WorldBorderManager {

    public static void set(Location location, int radius) {
        WorldBorder worldBorder = location.getWorld().getWorldBorder();

        worldBorder.setCenter(location);
        worldBorder.setSize(radius);
        worldBorder.setDamageAmount(0);
        worldBorder.setWarningDistance(15);
        worldBorder.setDamageBuffer(0);
        worldBorder.setWarningTime(0);

    }

    public static void reset(World world) {
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.reset();
    }

}
