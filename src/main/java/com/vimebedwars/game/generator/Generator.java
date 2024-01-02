package com.vimebedwars.game.generator;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.task.TaskManager;
import com.vimebedwars.game.usable.Item;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;

public class Generator {

    private final Location location;
    private Type spawnerType;

    public static final Item BRONZE = new Item("&cБронза",new ArrayList<>(), Material.CLAY_BRICK,1,0);
    public static final Item IRON = new Item("&7Железо",new ArrayList<>(), Material.IRON_INGOT,1,0);
    public static final Item GOLD = new Item("&eЗолото",new ArrayList<>(), Material.GOLD_INGOT,1,0);

    public Generator(Location location, Type spawnerType) {
        this.location = location;
        this.spawnerType = spawnerType;
    }

    public Location getLocation() {
        return location;
    }

    public Type getSpawnerType() {
        return spawnerType;
    }

    public void setSpawnerType(Type spawnerType) {
        this.spawnerType = spawnerType;
    }

    public void start() {
        TaskManager taskManager = VimeBedWars.getInstance().getTaskManager();
        switch (spawnerType) {
            case BRONZE:
                taskManager.startRepeat(20, 20, () -> location.getWorld().dropItem(location,BRONZE.getItemStack()));
                break;
            case IRON:
                taskManager.startRepeat(80, 80, () -> location.getWorld().dropItem(location,IRON.getItemStack()));
                break;
            case GOLD:
                taskManager.startRepeat(240, 240, () -> location.getWorld().dropItem(location,GOLD.getItemStack()));
                break;
        }
    }

    public enum Type {
        BRONZE,
        IRON,
        GOLD;
    }
}
