package com.vimebedwars.game.object.bed;

import com.vimebedwars.game.object.team.BWTeam;
import org.bukkit.Location;

public class Bed {

    private final Location first;
    private final Location second;

    public Bed(Location first, Location second) {
        this.first = first;
        this.second = second;
    }

    public Location getFirst() {
        return first;
    }

    public Location getSecond() {
        return second;
    }

}
