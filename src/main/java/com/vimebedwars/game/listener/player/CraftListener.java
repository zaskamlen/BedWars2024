package com.vimebedwars.game.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftListener implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        event.setCancelled(true);
    }

}
