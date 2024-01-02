package com.vimebedwars.game.nms.npc;

import com.vimebedwars.game.villager.VillagerShop;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class NPCListener implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager) {
            event.setCancelled(true);
            VillagerShop villagerShop = new VillagerShop();
            villagerShop.show(event.getPlayer());
        }
    }

}
