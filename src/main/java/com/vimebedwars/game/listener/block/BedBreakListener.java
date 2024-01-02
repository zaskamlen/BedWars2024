package com.vimebedwars.game.listener.block;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.BedWarsMap;
import com.vimebedwars.game.nms.NMSManager;
import com.vimebedwars.game.object.manager.MapManager;
import com.vimebedwars.game.object.player.PlayerInfo;
import com.vimebedwars.game.object.team.BWTeam;
import com.vimebedwars.game.utils.BWUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BedBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        BedWarsMap map = VimeBedWars.getInstance().getMap();
        PlayerInfo playerInfo = PlayerInfo.getPlayerInfo(event.getPlayer().getDisplayName());

        switch (map.getGameState()) {

            case WAITING:
            case END:
                event.setCancelled(true);
                break;
            case GAME:
                if (event.getBlock().getType() == Material.BED_BLOCK) {
                    breakBed(playerInfo, event.getBlock());
                    event.setCancelled(true);
                }
                break;

        }

    }

    private void breakBed(PlayerInfo breaker,Block bed) {
        BWTeam team = breaker.getTeam();
        BWTeam breakTeam = getBreakTeam(breaker);

        Location p1 = team.getBed().getFirst();
        Location p2 = team.getBed().getSecond();

        int x = bed.getX();
        int y = bed.getY();
        int z = bed.getZ();

        Location bedLoc = new Location(bed.getWorld(),x,y,z);

        if (breakTeam.getBedAlive()) {
            if (bed.getType() == Material.BED_BLOCK) {
                if (p1.getBlock().getLocation().equals(bedLoc) || p2.getBlock().getLocation().equals(bedLoc)) {
                    breaker.getPlayer().sendMessage(BWUtils.colored("&cВы ломаете свою кровать"));
                }else {
                    String teamName = breakTeam.getName();

                    breakTeam.setBedAlive(false);
                    breaker.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,80,2));
                    breaker.addMoney(350);

                    Bukkit.getOnlinePlayers().forEach(player -> {
                        player.playSound(player.getLocation(),Sound.ENDERDRAGON_GROWL,100,1);
                        player.sendMessage(BWUtils.colored("Игрок &e" + breaker.getName() + "&f сломал кровать команды &e" + teamName));
                        VimeBedWars.getInstance().getNmsManager().showBoard(PlayerInfo.getPlayerInfo(player.getDisplayName()));
                    });

                    breakTeam.getBed().getFirst().getBlock().setType(Material.AIR);
                    breakTeam.getBed().getSecond().getBlock().setType(Material.AIR);

                }
            }
        }

    }

    private BWTeam getBreakTeam(PlayerInfo playerInfo) {
        return playerInfo.getTeam().getColor() == Color.RED ? VimeBedWars.getInstance().getMap().getBlueTeam() : VimeBedWars.getInstance().getMap().getRedTeam();
    }



}
