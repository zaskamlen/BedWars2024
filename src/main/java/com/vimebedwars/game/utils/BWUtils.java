package com.vimebedwars.game.utils;

import com.vimebedwars.VimeBedWars;
import net.md_5.bungee.api.ChatMessageType;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BWUtils {

    public static void sendPacket(final Player player, final Packet packet) {
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }

    public static List<Player> getNearbyPlayers(final Location loc, double radius) {
        final LinkedList<Player> list = new LinkedList<Player>();
        radius *= radius;
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (player.getLocation().distanceSquared(loc) < radius) {
                list.add(player);
            }
        }
        return list;
    }


    public static void sendActionBar(final Player player, final String message) {
        final IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + colored(message) + "\"}");
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(iChatBaseComponent, (byte) 2));
    }

    public static String colored(final String str) {
        if (str == null) {
            return null;
        }
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static List<String> colored(final String... lines) {
        if (lines == null) {
            return new ArrayList<>();
        }
        IntStream.range(0, lines.length).forEachOrdered(i -> lines[i] = colored(lines[i]));
        return Arrays.asList(lines);
    }

    public static void resetPlayer(Player player) {
        player.setFlying(false);
        player.setAllowFlight(false);
        player.setFireTicks(0);
        player.setNoDamageTicks(20);
        player.setGameMode(GameMode.SURVIVAL);
        player.setFoodLevel(2000);
        player.setLevel(0);
        player.setExp(0);
        player.setTotalExperience(0);
        player.setHealth(player.getMaxHealth());
        player.resetPlayerTime();

        player.getInventory().clear();

        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));

        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
    }

    public static void refillVillagers() {
        VimeBedWars.getInstance().getMap().getRedVillager().spawn();
        VimeBedWars.getInstance().getMap().getBlueVillager().spawn();
    }

    public static String splitList(List<String> messages) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String message : messages) {
            stringBuilder.append(message);
        }

        return stringBuilder.toString();
    }

    public static List<String> colored(final List<String> lines) {
        final ListIterator<String> it = lines.listIterator();
        while (it.hasNext()) {
            it.set(colored((String)it.next()));
        }
        return lines;
    }

    public static String plurals(int n, final String form1, final String form2, final String form3) {
        if (n == 0) {
            return form3;
        }
        n = Math.abs(n) % 100;
        if (n > 10 && n < 20) {
            return form3;
        }
        n %= 10;
        if (n > 1 && n < 5) {
            return form2;
        }
        if (n == 1) {
            return form1;
        }
        return form3;
    }

}
