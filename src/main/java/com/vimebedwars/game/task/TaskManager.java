package com.vimebedwars.game.task;

import com.vimebedwars.VimeBedWars;
import org.bukkit.Bukkit;

public class TaskManager implements Task{

    @Override
    public void start(long delay, Runnable runnable) {
        Bukkit.getScheduler().runTaskLater(VimeBedWars.getInstance(),runnable,delay);
    }

    @Override
    public void startRepeat(long delay, long period, Runnable runnable) {
        Bukkit.getScheduler().runTaskTimer(VimeBedWars.getInstance(),runnable,delay,period);
    }

}
