package com.vimebedwars.game.listener.player;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.BedWarsMap;
import com.vimebedwars.game.object.player.PlayerInfo;
import com.vimebedwars.game.state.GameState;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

public class BridgeItemListener implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        BedWarsMap map = VimeBedWars.getInstance().getMap();
        PlayerInfo playerInfo = PlayerInfo.getPlayerInfo(event.getPlayer().getDisplayName());

        if (map.getGameState() != GameState.GAME) {
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            event.setCancelled(true);

            Location loc = event.getPlayer().getEyeLocation();
            Vector dir = loc.getDirection();

            for (int i = 0; i < 9; ++i) {
                loc.add(dir);
                Block block = loc.getBlock();

                if (block.getType() == Material.AIR) {
                    loc.getWorld().playEffect(loc, Effect.SMOKE, 10);
                    map.getBlocks().add(block);
                    set(block, (byte) 4);
                } else {
                    break;
                }
            }

            PlayerInventory inv = event.getPlayer().getInventory();
            ItemStack used = inv.getItemInHand();
            used.setAmount(used.getAmount() - 1);
            inv.setItem(inv.getHeldItemSlot(), used.getAmount() == 0 ? null : used);
        }
    }

    private void set(Block block, byte data) {
        if (block.getType() == Material.AIR) {
            block.setTypeIdAndData(Material.CONCRETE.getId(), data, true);
        }
    }

}
