package com.vimebedwars.game.listener.player;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.BedWarsMap;
import com.vimebedwars.game.object.player.PlayerInfo;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItemListener implements Listener {

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent event) {
        PlayerInfo playerInfo = PlayerInfo.getPlayerInfo(event.getPlayer().getDisplayName());
        BedWarsMap map = VimeBedWars.getInstance().getMap();

        if (map == null || map.getGameState() == null) {
            return;
        }

        switch (map.getGameState()) {
            case WAITING:
            case END:
                event.setCancelled(true);
                break;
            case GAME:
                handleItemPickup(event, playerInfo);
                break;
        }
    }

    private void handleItemPickup(PlayerPickupItemEvent event, PlayerInfo playerInfo) {
        Material itemType = event.getItem().getItemStack().getType();
        int amount = event.getItem().getItemStack().getAmount();

        if (itemType == Material.CLAY_BRICK) {
            playerInfo.addMoney(amount);
            removeItemAfterPickup(playerInfo, Material.CLAY_BRICK);

        } else if (itemType == Material.IRON_INGOT) {
            playerInfo.addMoney(5 * amount);
            removeItemAfterPickup(playerInfo, Material.IRON_INGOT);

        } else if (itemType == Material.GOLD_INGOT) {
            playerInfo.addMoney(15 * amount);
            removeItemAfterPickup(playerInfo, Material.GOLD_INGOT);
        }
    }

    private void removeItemAfterPickup(PlayerInfo playerInfo, Material itemType) {
        VimeBedWars.getInstance().getNmsManager().showBoard(playerInfo);
        VimeBedWars.getInstance().getTaskManager().start(0, () -> playerInfo.getPlayer().getInventory().remove(itemType));
    }
}
