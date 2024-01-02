package com.vimebedwars.game.villager;

import com.vimebedwars.game.object.menu.BWMenu;

public class VillagerShop extends BWMenu {

    public VillagerShop() {
        super("Торговец", 36);

        //setItem(11, VillagerItems.BRIDGE()); Предемет не доделан
        setItem(12, VillagerItems.SWORD());
        setItem(13, VillagerItems.WOOL());
        setItem(14, VillagerItems.SHEAR());
        setItem(15, VillagerItems.FISH_ROD());
        setItem(20,VillagerItems.IRON_ARMOR());
        setItem(21,VillagerItems.ENDER_PEARL());
        setItem(22,VillagerItems.PICKAXE());
        setItem(23,VillagerItems.COMPASS());
        setItem(24,VillagerItems.TNT());
    }
}
