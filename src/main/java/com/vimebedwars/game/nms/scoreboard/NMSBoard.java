package com.vimebedwars.game.nms.scoreboard;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.BedWarsMap;
import com.vimebedwars.game.object.player.PlayerInfo;
import com.vimebedwars.game.utils.BWUtils;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.Player;

public class NMSBoard {

    public void send(Player player) {
        BedWarsMap map = VimeBedWars.getInstance().getMap();

        PlayerInfo info = PlayerInfo.getPlayerInfo(player.getDisplayName());

        Scoreboard scoreboard = new Scoreboard();
        ScoreboardObjective objective = scoreboard.registerObjective(BWUtils.colored("&b&lBedWars"), IScoreboardCriteria.b);

        PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(objective,1);
        PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(objective,0);
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1,objective);

        BWUtils.sendPacket(player,removePacket);
        BWUtils.sendPacket(player,createPacket);
        BWUtils.sendPacket(player,display);

        addLine(scoreboard,objective,"&fКарта &l" + map.getName(),player,9);
        addLine(scoreboard,objective,"&e",player,8);
        addLine(scoreboard,objective,"&c" + map.getRedTeam().isBedAlive() + " Красные &7(" + map.getRedTeam().getPlayers().size() + "/" + map.getTeamPlayersCount() + ")",player,7);
        addLine(scoreboard,objective,"&9"  + map.getBlueTeam().isBedAlive() + " Синие &7(" + map.getBlueTeam().getPlayers().size() + "/" + map.getTeamPlayersCount() + ")",player,6);
        addLine(scoreboard,objective,"&3",player,5);
        addLine(scoreboard,objective,"&fУбийства &e" + info.getKills(),player,4);
        addLine(scoreboard,objective,"&fСмертей &e" + info.getDeaths(),player,3);
        addLine(scoreboard,objective,"&fМонет &e" + info.getMoney(),player,2);
        addLine(scoreboard,objective,"&6",player,1);
        addLine(scoreboard,objective,"&a&lVimeWorld",player,0);





    }


    public void addLine(Scoreboard scoreboard, ScoreboardObjective objective,String text,Player player,int id) {
        ScoreboardScore scoreboardScore = new ScoreboardScore(scoreboard,objective,BWUtils.colored(text));
        scoreboardScore.setScore(id);

        PacketPlayOutScoreboardScore packetPlayOutScoreboardScore = new PacketPlayOutScoreboardScore(scoreboardScore);

        BWUtils.sendPacket(player,packetPlayOutScoreboardScore);
    }







}
