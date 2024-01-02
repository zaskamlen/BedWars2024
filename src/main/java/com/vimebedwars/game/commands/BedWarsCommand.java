package com.vimebedwars.game.commands;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.BedWarsMap;
import com.vimebedwars.game.object.manager.MapManager;
import com.vimebedwars.game.utils.BWUtils;
import com.vimebedwars.game.villager.VillagerShop;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BedWarsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        BedWarsMap map = VimeBedWars.getInstance().getMap();
        MapManager mapManager = VimeBedWars.getInstance().getMapManager();
        Player player = (Player) commandSender;

        if (player.isOp()) {
            if (strings.length == 0) {
                player.sendMessage("/bw start");
                player.sendMessage("/bw stop");
                player.sendMessage("/bw state");
                player.sendMessage("/bw shop");
            }else if (strings[0].equalsIgnoreCase("state")) {
                player.sendMessage("Информация об игре:");
                player.sendMessage(BWUtils.colored(""));
                player.sendMessage(BWUtils.colored("&fИгроков &e" + Bukkit.getOnlinePlayers().size()));
                player.sendMessage(BWUtils.colored("&fСтадия игры &e" + map.getGameState().name()));
                player.sendMessage(BWUtils.colored("&fКоманды:"));
                player.sendMessage(BWUtils.colored(""));
                player.sendMessage(BWUtils.colored("&e - &cКрасные &e[" + map.getRedTeam().getPlayers().size() + "/1]"));
                player.sendMessage(BWUtils.colored("&e - &9Синие &e[" + map.getBlueTeam().getPlayers().size() + "/1]"));
                player.sendMessage(BWUtils.colored(""));
            } else if (strings[0].equalsIgnoreCase("start")) {
                player.sendMessage("Игра запущена через 5 сек вас телепортирует.");
                mapManager.start();
            }else if (strings[0].equalsIgnoreCase("stop")) {
                mapManager.stop();
            }else if (strings[0].equalsIgnoreCase("shop")) {
                VillagerShop villagerShop = new VillagerShop();
                villagerShop.show(player);
            }
        }

        return true;
    }
}
