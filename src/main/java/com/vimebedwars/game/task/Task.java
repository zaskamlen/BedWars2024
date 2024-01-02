package com.vimebedwars.game.task;

public interface Task {

    void start(long delay,Runnable runnable);
    void startRepeat(long delay,long period, Runnable runnable);

}
