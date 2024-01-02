package com.vimebedwars.game.object.manager;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.BedWarsMap;
import com.vimebedwars.game.object.player.PlayerInfo;
import com.vimebedwars.game.state.GameState;
import com.vimebedwars.game.task.TaskManager;
import com.vimebedwars.game.utils.BWUtils;
import com.vimebedwars.game.utils.BWTimer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class MapManager {

    private final TaskManager taskManager = VimeBedWars.instance.getTaskManager();

    public void savePlayer() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            PlayerInfo playerInfo = PlayerInfo.getPlayerInfo(player.getDisplayName());

            VimeBedWars.getInstance().getMySQL().saveStats(playerInfo);

        });
    }

    public void reset() {

        BedWarsMap map = VimeBedWars.getInstance().getMap();
        map.getBlocks().forEach(block -> block.setType(Material.AIR));

        Bukkit.getWorld("world").getEntities().forEach(entity -> {
            if (entity.getType() == EntityType.VILLAGER || entity.getType() == EntityType.DROPPED_ITEM) {
                entity.remove();
            }
        });

        WorldBorderManager.reset(Bukkit.getWorld("world"));
    }

    public void start() {
        BedWarsMap map = VimeBedWars.getInstance().getMap();
        taskManager.start(5 * 20, () -> {

            map.setGameState(GameState.GAME);

            BWUtils.refillVillagers();

            map.getBlueBronzeGenerator().start();
            map.getRedBronzeGenerator().start();

            map.getBlueIronGenerator().start();
            map.getRedIronGenerator().start();

            map.getRedGoldGenerator().start();
            map.getBlueGoldGenerator().start();


            map.getBlueTeam().spawn();
            map.getRedTeam().spawn();

            WorldBorderManager.set(new Location(Bukkit.getWorld("world"),500.5,81.0,500.5),256);

            Bukkit.broadcastMessage("Игра запущена!");

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                BWTimer timer = new BWTimer(onlinePlayer, 1800, this::stop);
                timer.startTimer();
            }
        });
    }



    public void stop() {
        Bukkit.shutdown();
    }

}
