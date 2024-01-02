package com.vimebedwars.game.usable;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class LobbyItems {

    public static void give(Player player) {
        Item item = new Item("&f&l>> &eВыбрать команду &f&l<<",new ArrayList<>(), Material.NAME_TAG,1,0);

        item.appendLore("VimeWorld.com");

        player.getInventory().setItem(0,item.getItemStack());
    }

}
