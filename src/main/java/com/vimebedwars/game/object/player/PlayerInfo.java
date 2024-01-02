package com.vimebedwars.game.object.player;

import com.vimebedwars.game.object.team.BWTeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerInfo {

    public static Map<String, PlayerInfo> PLAYERS = new ConcurrentHashMap<>();

    private final String name;
    private final Player player;
    private final UUID uuid;
    private int kills;
    private int deaths;
    private int blockBreaks;
    private int money;

    private BWTeam team;

    public PlayerInfo(String name, UUID uuid,int money, int kills, int deaths, int blockBreaks, BWTeam team) {
        this.name = name;
        this.player = Bukkit.getPlayer(uuid);
        this.uuid = uuid;
        this.kills = kills;
        this.deaths = deaths;
        this.blockBreaks = blockBreaks;
        this.team = team;
        this.money = money;
    }

    public void create() {
        PLAYERS.put(name,this);
    }

    public static PlayerInfo getPlayerInfo(String name) {
        return PLAYERS.get(name);
    }

    public BWTeam getTeam() {
        return team;
    }

    public void setTeam(BWTeam team) {
        this.team = team;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int money) {
        setMoney(this.money+money);
    }

    public void removeMoney(int money) {
        setMoney(this.money-money);
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    public int getKills() {
        return kills;
    }

    public void addKills(int kills) {
        this.kills = this.kills+kills;
    }

    public void removeKills(int kills) {
        this.kills = this.kills-kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void addDeaths(int deaths) {
        this.deaths = this.deaths+deaths;
    }

    public void removeDeaths(int deaths) {
        this.deaths = this.deaths-deaths;
    }


    public int getBlockBreaks() {
        return blockBreaks;
    }

    public void addBlocks(int blocks) {
        this.blockBreaks = this.blockBreaks+blocks;
    }

    public void removeBlocks(int blocks) {
        this.blockBreaks = this.blockBreaks-blocks;
    }

}
