package com.vimebedwars.game;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.object.bed.Bed;
import com.vimebedwars.game.object.team.BWTeam;
import com.vimebedwars.game.state.GameState;
import com.vimebedwars.game.utils.BWConfig;
import com.vimebedwars.game.villager.Villager;
import com.vimebedwars.game.generator.Generator;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BedWarsMap {

    private final Set<Block> blockHashMap = new HashSet<>();

    private GameState gameState = GameState.WAITING;
    private final BWConfig config = VimeBedWars.getInstance().getBwConfig();

    private final Villager redVillager = new Villager(config.getRedVillager());
    private final Villager blueVillager = new Villager(config.getBlueVillager());

    private final Bed redBed = new Bed(config.getRedBedFirst(),config.getRedBedSecond());
    private final Bed blueBed = new Bed(config.getBlueBedFirst(),config.getBlueBedSecond());

    private final BWTeam redTeam = new BWTeam("Красные", Color.RED,new ArrayList<>(),true, config.getRedSpawn(),redVillager,redBed, 14);
    private final BWTeam blueTeam = new BWTeam("Синие", Color.BLUE,new ArrayList<>(),true, config.getBlueSpawn(),blueVillager,blueBed, 11);

    private final Generator redBronzeGenerator = new Generator(config.getRedBronzeGenerator(), Generator.Type.BRONZE);
    private final Generator blueBronzeGenerator = new Generator(config.getBlueBronzeGenerator(), Generator.Type.BRONZE);

    private final Generator redIronGenerator = new Generator(config.getRedIronGenerator(), Generator.Type.IRON);
    private final Generator blueIronGenerator = new Generator(config.getBlueIronGenerator(), Generator.Type.IRON);

    private final Generator redGoldGenerator = new Generator(config.getRedGoldGenerator(), Generator.Type.GOLD);
    private final Generator blueGoldGenerator = new Generator(config.getBlueGoldGenerator(), Generator.Type.GOLD);

    private final Location spawn = config.getSpawn();
    private final String name = config.getName();

    private final int teamPlayersCount = config.getTeamPlayersCount();

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Set<Block> getBlocks() {
        return blockHashMap;
    }

    public String getName() {
        return name;
    }

    public Location getSpawn() {
        return spawn;
    }

    public BWTeam getBlueTeam() {
        return blueTeam;
    }

    public BWTeam getRedTeam() {
        return redTeam;
    }

    public int getTeamPlayersCount() {
        return teamPlayersCount;
    }

    public Villager getBlueVillager() {
        return blueVillager;
    }

    public Villager getRedVillager() {
        return redVillager;
    }

    public Bed getRedBed() {
        return redBed;
    }

    public Bed getBlueBed() {
        return blueBed;
    }

    public Generator getBlueBronzeGenerator() {
        return blueBronzeGenerator;
    }

    public Generator getBlueIronGenerator() {
        return blueIronGenerator;
    }

    public Generator getRedBronzeGenerator() {
        return redBronzeGenerator;
    }

    public Generator getRedIronGenerator() {
        return redIronGenerator;
    }

    public Generator getRedGoldGenerator() {
        return redGoldGenerator;
    }

    public Generator getBlueGoldGenerator() {
        return blueGoldGenerator;
    }
}
