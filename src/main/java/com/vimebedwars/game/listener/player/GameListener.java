package com.vimebedwars.game.listener.player;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.BedWarsMap;
import com.vimebedwars.game.nms.NMSManager;
import com.vimebedwars.game.object.player.PlayerInfo;
import com.vimebedwars.game.state.GameState;
import com.vimebedwars.game.utils.BWUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GameListener implements Listener {

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(VimeBedWars.getInstance().getMap().getGameState() != GameState.GAME);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        BedWarsMap map = VimeBedWars.getInstance().getMap();

        PlayerInfo info = PlayerInfo.getPlayerInfo(event.getPlayer().getDisplayName());

        switch (map.getGameState()) {
            case WAITING:
            case END:
                event.setRespawnLocation(map.getSpawn());
                break;
            case GAME:

                if (info.getTeam().getBedAlive()) {
                    info.getTeam().spawn(info.getPlayer());
                    event.setRespawnLocation(info.getTeam().getSpawn());
                }else {

                    info.getPlayer().setAllowFlight(true);

                    VimeBedWars.getInstance().getTaskManager().start(5, () ->
                            info.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,Integer.MAX_VALUE,1)));

                    info.getPlayer().sendMessage(BWUtils.colored("&aВы зритель"));

                    // TODO Если надо добавить выбор для зрителя

                    event.setRespawnLocation(map.getSpawn());

                    info.getPlayer().getInventory().clear();

                    info.getPlayer().getInventory().setHelmet(null);
                    info.getPlayer().getInventory().setChestplate(null);
                    info.getPlayer().getInventory().setLeggings(null);
                    info.getPlayer().getInventory().setBoots(null);

                    info.getTeam().removePlayer(info);

                    Bukkit.getOnlinePlayers().forEach(player -> {
                        player.hidePlayer(info.getPlayer());
                        VimeBedWars.getInstance().getNmsManager().showBoard(PlayerInfo.getPlayerInfo(player.getDisplayName()));
                    });
                }

                VimeBedWars.getInstance().getTaskManager().start(5, BWUtils::refillVillagers);

                break;
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        BedWarsMap map = VimeBedWars.getInstance().getMap();

        switch (map.getGameState()) {
            case WAITING:
            case END:
                event.setDeathMessage(null);
                break;
            case GAME:
                if (killer != null){

                    PlayerInfo killerInfo = PlayerInfo.getPlayerInfo(killer.getDisplayName());
                    PlayerInfo playerInfo = PlayerInfo.getPlayerInfo(player.getDisplayName());

                    killerInfo.addMoney(40);
                    killerInfo.addKills(1);
                    playerInfo.removeMoney(20);
                    playerInfo.addDeaths(1);

                    event.setDeathMessage(BWUtils.colored(
                            "&fИгрок &e" + killer.getPlayer().getDisplayName() + "&f убил игрока &e" + player.getPlayer().getDisplayName() + " &f и получил &a+40&e монет"));
                    VimeBedWars.getInstance().getNmsManager().showBoard(playerInfo);
                    VimeBedWars.getInstance().getNmsManager().showBoard(killerInfo);
                }else {
                    PlayerInfo playerInfo = PlayerInfo.getPlayerInfo(player.getDisplayName());
                    playerInfo.addDeaths(1);
                    playerInfo.removeMoney(20);
                    event.setDeathMessage(BWUtils.colored(
                            "&fИгрок &e" + player.getPlayer().getDisplayName() + "&f умер"));

                    VimeBedWars.getInstance().getNmsManager().showBoard(playerInfo);

                }
                break;
        }



        event.setKeepInventory(true);
        event.setDroppedExp(0);
        event.setNewLevel(0);
        event.setNewTotalExp(0);
        event.setKeepLevel(true);

    }

}
