package com.vimebedwars.game.listener.block;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.BedWarsMap;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.HashMap;

public class BlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        BedWarsMap map = VimeBedWars.getInstance().getMap();

        switch (map.getGameState()) {

            case WAITING:
            case END:
                event.setCancelled(true);
                break;
            case GAME:
                if (event.getBlock().getType() == Material.WOOL || event.getBlock().getType() == Material.CONCRETE) {
                    event.setCancelled(!map.getBlocks().contains(event.getBlock()));
                }else {
                    event.setCancelled(true);
                }
                break;

        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        BedWarsMap map = VimeBedWars.getInstance().getMap();

        switch (map.getGameState()) {

            case WAITING:
            case END:
                event.setCancelled(true);
                break;
            case GAME:
                if (event.getBlock().getType() == Material.WOOL || event.getBlock().getType() == Material.CONCRETE) {
                    map.getBlocks().add(event.getBlock());
                    event.setCancelled(false);
                }else {
                    event.setCancelled(true);
                }
                break;

        }

    }

    @EventHandler
    public void onFormTo(BlockFromToEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPhysic(BlockPhysicsEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onForm(BlockFromToEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onExplodeBlock(BlockExplodeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        event.setCancelled(false);
    }

    @EventHandler
    public void onSpread(BlockSpreadEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPistonRetract(BlockPistonRetractEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPistonExtand(BlockPistonExtendEvent event) {
        event.setCancelled(true);
    }


}
