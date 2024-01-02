package com.vimebedwars;

import com.vimebedwars.game.BedWarsMap;
import com.vimebedwars.game.commands.BedWarsCommand;
import com.vimebedwars.game.listener.player.GameListener;
import com.vimebedwars.game.listener.player.WaitingListener;
import com.vimebedwars.game.listener.block.BedBreakListener;
import com.vimebedwars.game.listener.block.BlockListener;
import com.vimebedwars.game.listener.chat.AsyncChatListener;
import com.vimebedwars.game.listener.player.*;
import com.vimebedwars.game.listener.stand.ArmorStandListener;
import com.vimebedwars.game.mysql.MySQL;
import com.vimebedwars.game.nms.NMSManager;
import com.vimebedwars.game.nms.npc.NPCListener;
import com.vimebedwars.game.object.manager.MapManager;
import com.vimebedwars.game.object.team.TeamSelectEvent;
import com.vimebedwars.game.task.TaskManager;
import com.vimebedwars.game.utils.BWConfig;
import com.vimebedwars.game.villager.VillagerEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class VimeBedWars extends JavaPlugin {

    private TaskManager taskManager;
    private NMSManager nmsManager;
    private MapManager mapManager;
    private BWConfig bwConfig;
    private BedWarsMap map;
    private MySQL mySQL;
    public static VimeBedWars instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        bwConfig = new BWConfig();
        taskManager = new TaskManager();
        nmsManager = new NMSManager();
        mapManager = new MapManager();
        map = new BedWarsMap();
        mySQL = new MySQL();

        Bukkit.getPluginManager().registerEvents(new WaitingListener(),this);
        Bukkit.getPluginManager().registerEvents(new GameListener(),this);
        Bukkit.getPluginManager().registerEvents(new TeamSelectEvent(),this);
        Bukkit.getPluginManager().registerEvents(new VillagerEvent(),this);

        Bukkit.getPluginManager().registerEvents(new NPCListener(),this);
        Bukkit.getPluginManager().registerEvents(new AsyncChatListener(),this);
        Bukkit.getPluginManager().registerEvents(new ArmorStandListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerPickupItemListener(),this);
        //Bukkit.getPluginManager().registerEvents(new BridgeItemListener(),this) В разработке;
        Bukkit.getPluginManager().registerEvents(new BlockListener(),this);
        Bukkit.getPluginManager().registerEvents(new CraftListener(),this);
        Bukkit.getPluginManager().registerEvents(new BedBreakListener(),this);
        Bukkit.getPluginManager().registerEvents(new CompassUsageListener(),this);
        Bukkit.getPluginManager().registerEvents(new TntUsageListener(),this);

        getCommand("bedwars").setExecutor(new BedWarsCommand());
        resetWeatherSunMob();
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onDisable() {

        mapManager.reset();
    }

    private void resetWeatherSunMob() {
        VimeBedWars.getInstance().getTaskManager().startRepeat(20, 20, () -> {
            BedWarsMap map = VimeBedWars.getInstance().getMap();
            World world = map.getSpawn().getWorld();
            world.setTime(2000);
            world.setMonsterSpawnLimit(0);
            world.setSpawnFlags(false,false);
            world.setThundering(false);
            world.setStorm(false);
        });
    }
    public static VimeBedWars getInstance() {
        return instance;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public NMSManager getNmsManager() {
        return nmsManager;
    }

    public BWConfig getBwConfig() {
        return bwConfig;
    }

    public BedWarsMap getMap() {
        return map;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

}
