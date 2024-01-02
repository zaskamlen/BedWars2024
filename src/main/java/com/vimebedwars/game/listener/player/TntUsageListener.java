package com.vimebedwars.game.listener.player;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.BedWarsMap;
import com.vimebedwars.game.state.GameState;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TntUsageListener implements Listener {

    @EventHandler
    public void onTntExplode(EntityExplodeEvent event) {
        BedWarsMap map = VimeBedWars.getInstance().getMap();
        event.setCancelled(true);

        if (event.getEntity() instanceof TNTPrimed && map.getGameState() == GameState.GAME) {
            for (int x = -3; x <= 3; x++) {
                for (int y = -3; y <= 3; y++) {
                    for (int z = -3; z <= 3; z++) {
                        Block nearbyBlock = event.getLocation().getBlock().getRelative(x, y, z);

                        if (nearbyBlock.getType() == Material.WOOL || nearbyBlock.getType() == Material.CONCRETE) {
                            if (!map.getBlocks().contains(nearbyBlock)) return;

                            nearbyBlock.setType(Material.AIR);
                            nearbyBlock.getWorld().playEffect(nearbyBlock.getLocation(), Effect.EXPLOSION_LARGE,100);
                        }
                    }
                }
            }
        }


    }

    @EventHandler
    public void onTntPlace(BlockPlaceEvent event) {
        if (VimeBedWars.getInstance().getMap().getGameState() == GameState.GAME) {

            Block block = event.getBlockPlaced();

            if (block.getType() == Material.TNT) {
                TNTPrimed tnt = block.getWorld().spawn(block.getLocation().add(0, 1, 0), TNTPrimed.class);
                tnt.setFuseTicks(40);

                event.getBlockPlaced().setType(Material.AIR);

                event.setCancelled(false);
            }

        }
    }


}
