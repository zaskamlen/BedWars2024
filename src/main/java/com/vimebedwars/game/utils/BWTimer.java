package com.vimebedwars.game.utils;

import com.vimebedwars.VimeBedWars;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BWTimer extends BukkitRunnable {

    private int secondsLeft;
    private final Player player;
    private final Runnable onStop;

    public BWTimer(Player player, int initialSeconds, Runnable onStop) {
        this.secondsLeft = initialSeconds;
        this.player = player;
        this.onStop = onStop;
    }

    @Override
    public void run() {
        if (secondsLeft <= 0) {
            stopTimer();
            return;
        }

        int hours = secondsLeft / 3600;
        int minutes = (secondsLeft % 3600) / 60;
        int seconds = secondsLeft % 60;

        BWUtils.sendActionBar(player,"&fДо конца игры &e" +  String.format("%02d:%02d:%02d", hours, minutes, seconds));

        secondsLeft--;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    public void startTimer() {
        runTaskTimer(VimeBedWars.getInstance(), 0, 20);
    }

    public void stopTimer() {
        onStop.run();
        cancel();
    }

    public void setTime(int seconds) {
        this.secondsLeft = seconds;
    }

    private void sendActionBar(String message) {
        System.out.println(message);
    }
}
