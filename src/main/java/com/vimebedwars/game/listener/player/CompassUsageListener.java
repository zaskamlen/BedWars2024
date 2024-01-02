package com.vimebedwars.game.listener.player;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.object.skull.Skull;
import com.vimebedwars.game.state.GameState;
import com.vimebedwars.game.usable.Item;
import com.vimebedwars.game.utils.BWUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CompassUsageListener implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();


        if (VimeBedWars.getInstance().getMap().getGameState() == GameState.GAME) {
            switch (event.getAction()) {
                case RIGHT_CLICK_AIR:
                case RIGHT_CLICK_BLOCK:
                    if (event.getItem().getType() == Material.COMPASS) {
                        gps(player);
                        event.setCancelled(true);
                    }
                    break;
            }
        }


    }

    private void gps(Player player) {

        List<Player> nearbyPlayers = BWUtils.getNearbyPlayers(player.getLocation(),255);
        nearbyPlayers.remove(player);

        if (nearbyPlayers.isEmpty()) {
            player.getPlayer().playSound(player.getLocation(), Sound.VILLAGER_NO,100,1);
            return;
        }

        nearbyPlayers.forEach(nearbyPlayer -> {
            int distance = (int) player.getLocation().distance(nearbyPlayer.getLocation());


            if (!Bukkit.getOnlinePlayers().isEmpty()) {

                    Item item = new Item("&e&l" + nearbyPlayer.getDisplayName(), Collections.singletonList(BWUtils.colored("&7От вас &e" + distance)),Material.SKULL_ITEM,1,3);
                    item.appendLore("&7От вас в &e" + distance);

                    CompassMenu compassMenu = new CompassMenu();

                    compassMenu.addItem(item);
                    compassMenu.show(player);

            }else {
                player.sendMessage(BWUtils.colored("&cПоблизости нет игрока"));
            }


        });


    }

}
