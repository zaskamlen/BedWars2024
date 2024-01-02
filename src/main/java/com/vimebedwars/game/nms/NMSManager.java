package com.vimebedwars.game.nms;

import com.mojang.authlib.GameProfile;
import com.vimebedwars.game.nms.scoreboard.NMSBoard;
import com.vimebedwars.game.object.player.PlayerInfo;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NMSManager {

    public void showBoard(PlayerInfo info) {

        Player player = info.getPlayer();

        NMSBoard nmsBoard = new NMSBoard();

        nmsBoard.send(player);
    }

}
