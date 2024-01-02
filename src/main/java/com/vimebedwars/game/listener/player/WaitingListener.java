package com.vimebedwars.game.listener.player;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.BedWarsMap;
import com.vimebedwars.game.object.player.PlayerInfo;
import com.vimebedwars.game.state.GameState;
import com.vimebedwars.game.usable.LobbyItems;
import com.vimebedwars.game.utils.BWUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class WaitingListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        BedWarsMap map = VimeBedWars.getInstance().getMap();
        if (map.getGameState() != GameState.WAITING) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Ошибка, сервер не может принять вас");
        }
    }

    @EventHandler
    public void onDamageTeamPlayer(EntityDamageByEntityEvent event) {
        BedWarsMap map = VimeBedWars.getInstance().getMap();

        if (map.getGameState() == GameState.WAITING || map.getGameState() == GameState.END) {
            event.setCancelled(true);
            return;
        }

        if (event.getDamager().getType() == EntityType.PLAYER && event.getEntity().getType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();

            PlayerInfo playerInfo = PlayerInfo.getPlayerInfo(player.getDisplayName());
            PlayerInfo damagerInfo = PlayerInfo.getPlayerInfo(damager.getDisplayName());

            event.setCancelled(damagerInfo.getTeam() != null && damagerInfo.getTeam().getPlayers().contains(playerInfo));
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        BedWarsMap map = VimeBedWars.getInstance().getMap();

        if (map.getGameState() == GameState.WAITING || map.getGameState() == GameState.END) {
            if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                event.getEntity().teleport(VimeBedWars.getInstance().getBwConfig().getSpawn());
            }
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        BedWarsMap map = VimeBedWars.getInstance().getMap();
        Player player = event.getPlayer();

        if (map.getGameState() == GameState.WAITING) {
            PlayerInfo playerInfo = new PlayerInfo(player.getDisplayName(), player.getUniqueId(), 0, 0, 0, 0, null);
            playerInfo.create();

            player.teleport(map.getSpawn());
            player.setCanPickupItems(true);

            BWUtils.resetPlayer(player);

            VimeBedWars.getInstance().getNmsManager().showBoard(playerInfo);

            LobbyItems.give(player);

            event.setJoinMessage(BWUtils.colored("&fИгрок &e" + playerInfo.getName() + "&f подключился"));
        } else {
            event.setJoinMessage(null);
        }
    }
}
