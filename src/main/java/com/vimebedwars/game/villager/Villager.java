package com.vimebedwars.game.villager;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.nms.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Villager {

    private Location location;
    private final VillagerShop shop = new VillagerShop();

    public Villager(Location location) {
        this.location = location;
    }

    public void spawn() {
        VimeBedWars.getInstance().getTaskManager().start(20, () -> new NPC(location));
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public VillagerShop getShop() {
        return shop;
    }
}
