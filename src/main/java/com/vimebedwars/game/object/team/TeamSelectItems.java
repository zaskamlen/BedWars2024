package com.vimebedwars.game.object.team;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.BedWarsMap;
import com.vimebedwars.game.object.player.PlayerInfo;
import com.vimebedwars.game.usable.Item;
import com.vimebedwars.game.utils.BWUtils;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamSelectItems {

    public Item team(BWTeam team) {

        BedWarsMap map = VimeBedWars.getInstance().getMap();

        if (team.getColor() == Color.RED) {
            Item item = new Item("&cКоманда красных &7("  + team.getPlayers().size() + "/" + map.getTeamPlayersCount() + ")",new ArrayList<>(), Material.CONCRETE_POWDER,1,14);

            List<String> redPlayers = map.getRedTeam().getPlayers().stream().map(PlayerInfo::getName).collect(Collectors.toList());

            item.appendLore("", BWUtils.colored("&c" + BWUtils.splitList(redPlayers)),"", "&aНажмите чтобы войти");

            return item;
        }else if (team.getColor() == Color.BLUE) {
            Item item = new Item("&9Команда синих &7(" + team.getPlayers().size() + "/" + map.getTeamPlayersCount() + ")",new ArrayList<>(), Material.CONCRETE_POWDER,1,11);
            List<String> bluePlayers = map.getBlueTeam().getPlayers().stream().map(PlayerInfo::getName).collect(Collectors.toList());

            item.appendLore("",BWUtils.colored("&9" + BWUtils.splitList(bluePlayers)),"", "&aНажмите чтобы войти");

            return item;
        }else {
            Item item = new Item("&cЭтой команды несуществует",new ArrayList<>(), Material.BARRIER,1,0);

            item.appendLore("&7Вы что-то сделали не так.");

            return item;
        }
    }


}
