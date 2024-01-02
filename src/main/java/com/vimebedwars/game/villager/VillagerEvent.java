package com.vimebedwars.game.villager;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.BedWarsMap;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.UUID;

public class VillagerEvent implements Listener {

    // Можно вынести и сделать по другому, но чтобы работало сделал так.
    @EventHandler
    public void onBuy(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getInventory() != null) {
            if (event.getInventory().getTitle().equals("Торговец")) {
                event.setCancelled(true);
                switch (event.getSlot()) {
/*                    case 11:
                        VillagerItems.buyItem(player,VillagerItems.BRIDGE());
                        break; потом переделать*/
                    case 12:
                        VillagerItems.buyItem(player,VillagerItems.SWORD());
                        break;
                    case 13:
                        VillagerItems.buyItem(player,VillagerItems.WOOL());
                        break;
                    case 14:
                        VillagerItems.buyItem(player,VillagerItems.SHEAR());
                        break;
                    case 15:
                        VillagerItems.buyItem(player,VillagerItems.FISH_ROD());
                        break;
                    case 20:
                        VillagerItems.buyItem(player,VillagerItems.IRON_ARMOR());
                        break;
                    case 21:
                        VillagerItems.buyItem(player,VillagerItems.ENDER_PEARL());
                        break;
                    case 22:
                        VillagerItems.buyItem(player,VillagerItems.PICKAXE());
                        break;
                    case 23:
                        VillagerItems.buyItem(player,VillagerItems.COMPASS());
                        break;
                    case 24:
                        VillagerItems.buyItem(player,VillagerItems.TNT());
                        break;
                }
            }
        }
    }


}
