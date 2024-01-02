package com.vimebedwars.game.object.team;

import com.vimebedwars.game.object.bed.Bed;
import com.vimebedwars.game.object.player.PlayerInfo;
import com.vimebedwars.game.usable.Item;
import com.vimebedwars.game.utils.BWUtils;
import com.vimebedwars.game.villager.Villager;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class BWTeam {

    private final String name;
    private final Color color;
    private final List<PlayerInfo> players;
    private final Location spawn;
    private final Villager villager;
    private final Bed bed;
    private boolean bedAlive;
    private final int colorID;

    public BWTeam(String name, Color color, List<PlayerInfo> players, boolean bedAlive, Location spawn, Villager villager, Bed bed, int colorID) {
        this.name = name;
        this.color = color;
        this.players = players;
        this.spawn = spawn;
        this.villager = villager;
        this.bed = bed;
        this.bedAlive = bedAlive;
        this.colorID = colorID;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void addPlayer(PlayerInfo playerInfo) {
        if (!this.players.contains(playerInfo)) this.players.add(playerInfo);
    }

    public int getColorID() {
        return colorID;
    }

    public void removePlayer(PlayerInfo playerInfo) {
        this.players.remove(playerInfo);
    }

    public List<PlayerInfo> getPlayers() {
        return players;
    }

    public Location getSpawn() {
        return spawn;
    }

    public Villager getVillager() {
        return villager;
    }

    public Bed getBed() {
        return bed;
    }

    public String isBedAlive() {
        return bedAlive ? "✔" : "✘";
    }

    public boolean getBedAlive() {
        return this.bedAlive;
    }

    public void setBedAlive(boolean bedAlive) {
        this.bedAlive = bedAlive;
    }

    public void spawn(Player player) {
        PlayerInventory inventory = player.getPlayer().getInventory();

        BWUtils.resetPlayer(player.getPlayer());

        Item helmet = new Item("&fКожанный шлем",new ArrayList<>(), Material.LEATHER_HELMET,1,0);
        Item chestplate = new Item("&fКожанный нагрудник",new ArrayList<>(), Material.LEATHER_CHESTPLATE,1,0);
        Item leggings = new Item("&fКожанные штаны",new ArrayList<>(), Material.LEATHER_LEGGINGS,1,0);
        Item boots = new Item("&fКожанные ботинки",new ArrayList<>(), Material.LEATHER_BOOTS,1,0);

        helmet.appendColor(color);
        chestplate.appendColor(color);
        leggings.appendColor(color);
        boots.appendColor(color);

        inventory.setHelmet(helmet.getItemStack());
        inventory.setChestplate(chestplate.getItemStack());
        inventory.setLeggings(leggings.getItemStack());
        inventory.setBoots(boots.getItemStack());
    }

    public void spawn() {

        this.players.forEach(player -> {
            PlayerInventory inventory = player.getPlayer().getInventory();

            BWUtils.resetPlayer(player.getPlayer());

            player.getPlayer().teleport(spawn);

            Item helmet = new Item("&fКожанный шлем",new ArrayList<>(), Material.LEATHER_HELMET,1,0);
            Item chestplate = new Item("&fКожанный нагрудник",new ArrayList<>(), Material.LEATHER_CHESTPLATE,1,0);
            Item leggings = new Item("&fКожанные штаны",new ArrayList<>(), Material.LEATHER_LEGGINGS,1,0);
            Item boots = new Item("&fКожанные ботинки",new ArrayList<>(), Material.LEATHER_BOOTS,1,0);

            helmet.appendColor(color);
            chestplate.appendColor(color);
            leggings.appendColor(color);
            boots.appendColor(color);

            inventory.setHelmet(helmet.getItemStack());
            inventory.setChestplate(chestplate.getItemStack());
            inventory.setLeggings(leggings.getItemStack());
            inventory.setBoots(boots.getItemStack());
        });

    }

}
