package com.vimebedwars.game.nms.npc;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.utils.BWUtils;
import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftVillager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class NPC {


    private final Location location;

    public NPC(Location location) {
        this.location = location;

        Villager villager = location.getWorld().spawn(location, Villager.class);

        villager.setCustomName(BWUtils.colored("&e&lТорговец"));
        villager.setCustomNameVisible(true);
        villager.setProfession(Villager.Profession.FARMER);
        villager.setNoDamageTicks(Integer.MAX_VALUE);
        villager.setRemainingAir(0);
        villager.setMaximumAir(0);

        VimeBedWars.getInstance().getTaskManager().startRepeat(0, 0, () -> {
            if (villager.getLocation().equals(location)) return;
            villager.teleport(location);
        });

        EntityVillager nmsVillager = ((CraftVillager) villager).getHandle();
        nmsVillager.goalSelector = new PathfinderGoalSelector(nmsVillager.getWorld().methodProfiler);
        nmsVillager.targetSelector = new PathfinderGoalSelector(nmsVillager.getWorld().methodProfiler);


    }

    public Location getLocation() {
        return location;
    }
}
