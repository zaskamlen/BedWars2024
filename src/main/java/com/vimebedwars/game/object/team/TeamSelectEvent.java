package com.vimebedwars.game.object.team;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.BedWarsMap;
import com.vimebedwars.game.nms.NMSManager;
import com.vimebedwars.game.nms.scoreboard.NMSBoard;
import com.vimebedwars.game.object.player.PlayerInfo;
import com.vimebedwars.game.state.GameState;
import com.vimebedwars.game.utils.BWUtils;
import com.vimebedwars.game.villager.VillagerItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TeamSelectEvent implements Listener {

    @EventHandler
    public void onClickItem(PlayerInteractEvent event) {
        BedWarsMap map = VimeBedWars.getInstance().getMap();
        TeamSelectMenu menu = new TeamSelectMenu();

        switch (event.getAction()) {
            case RIGHT_CLICK_AIR:
            case RIGHT_CLICK_BLOCK:

                if (map.getGameState() == GameState.WAITING) {
                    if (event.getItem() != null && event.getItem().getType() == Material.NAME_TAG) {
                        menu.show(event.getPlayer());
                    }
                }
                break;
        }
    }

    @EventHandler
    public void onSelect(InventoryClickEvent event) {
        TeamSelectMenu menu = new TeamSelectMenu();
        BedWarsMap map = VimeBedWars.getInstance().getMap();

        Player player = (Player) event.getWhoClicked();
        PlayerInfo playerInfo = PlayerInfo.getPlayerInfo(player.getDisplayName());

        if (event.getInventory() != null) {
            if (event.getInventory().getTitle().equals("Выбор команды")) {
                event.setCancelled(true);
                switch (event.getSlot()) {
                    case 12:

                        if (map.getRedTeam().getPlayers().contains(playerInfo)) {
                            event.setCancelled(true);
                        }else {

                            if (map.getRedTeam().getPlayers().size() != map.getTeamPlayersCount()) {
                                menu.hide(player);

                                player.sendMessage(BWUtils.colored("Вы зашли за команду &c&lКрасные"));

                                playerInfo.setTeam(map.getRedTeam());

                                map.getRedTeam().addPlayer(playerInfo);
                                map.getBlueTeam().getPlayers().remove(playerInfo);

                                VimeBedWars.getInstance().getNmsManager().showBoard(playerInfo);
                            }else {
                                event.setCancelled(true);
                            }

                        }

                        break;
                    case 14:

                        if (map.getBlueTeam().getPlayers().contains(playerInfo)) {
                            event.setCancelled(true);
                        }else {
                            if (map.getBlueTeam().getPlayers().size() != map.getTeamPlayersCount()) {
                                menu.hide(player);

                                player.sendMessage(BWUtils.colored("Вы зашли за команду &9&lСиние"));

                                playerInfo.setTeam(map.getBlueTeam());

                                map.getBlueTeam().addPlayer(playerInfo);
                                map.getRedTeam().getPlayers().remove(playerInfo);

                                VimeBedWars.getInstance().getNmsManager().showBoard(playerInfo);
                            }else {
                                event.setCancelled(true);
                            }
                        }
                        break;
                }
            }
        }
    }

}
