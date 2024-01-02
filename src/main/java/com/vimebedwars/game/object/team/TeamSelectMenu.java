package com.vimebedwars.game.object.team;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.object.menu.BWMenu;

public class TeamSelectMenu extends BWMenu {
    public TeamSelectMenu() {
        super("Выбор команды", 27);
        TeamSelectItems teamSelectItems = new TeamSelectItems();

        setItem(12,teamSelectItems.team(VimeBedWars.getInstance().getMap().getRedTeam()));
        setItem(14,teamSelectItems.team(VimeBedWars.getInstance().getMap().getBlueTeam()));

    }


}
