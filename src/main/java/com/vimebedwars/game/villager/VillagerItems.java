package com.vimebedwars.game.villager;

import com.vimebedwars.VimeBedWars;
import com.vimebedwars.game.object.player.PlayerInfo;
import com.vimebedwars.game.usable.Item;
import com.vimebedwars.game.utils.BWUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;

public class VillagerItems {

    public static Item SWORD() {
        Item item = new Item("&eМеч &7[1x]",new ArrayList<>(), Material.STONE_SWORD,1,0);

        item.appendLore("&7Данный предмет поможет","&7вам убивать ваших противников","","&fЦена &e120 монет","","&aНажмите чтобы купить");

        return item;
    }

    public static Item WOOL() {

        Item item = new Item("&eШерсть &7[2x]",new ArrayList<>(), Material.WOOL,2);

        item.appendLore("&7Данный предмет поможет","&7вам строиться до противников"," ","&fЦена &e1 монета","","&aНажмите чтобы купить");

        return item;
    }

    public static Item SHEAR() {
        Item item = new Item("&eНожницы &7[1x]",new ArrayList<>(), Material.SHEARS,1,0);

        item.appendLore("&7Данный предмет поможет","&7вам бысрее ломать блоки","","&fЦена &e60 монет","","&aНажмите чтобы купить");

        return item;
    }

    public static Item BRIDGE() {
        Item item = new Item("&eСтроительный мост &7[1x]",new ArrayList<>(), Material.CONCRETE,1,4);

        item.appendLore("&7Данный предмет поможет","&7вам бысрее дойти до базы противника","","&fЦена &e40 монет","","&aНажмите чтобы купить");

        return item;
    }


    public static Item FISH_ROD() {
        Item item = new Item("&eУдочка &7[1x]",new ArrayList<>(), Material.FISHING_ROD,1,0);

        item.appendLore("&7Данный предмет поможет","&7вам лучше комбить вашего противника","","&fЦена &e50 монет","","&aНажмите чтобы купить");

        return item;
    }

    public static Item PICKAXE() {
        Item item = new Item("&eКирка &7[1x]",new ArrayList<>(), Material.IRON_PICKAXE,1,0);

        item.appendLore("&7Данный предмет поможет","&7вам лучше ломать блоки","","&fЦена &e150 монет","","&aНажмите чтобы купить");

        return item;
    }

    public static Item COMPASS() {
        Item item = new Item("&eGPS Трекер &7[1x]",new ArrayList<>(), Material.COMPASS,1,0);

        item.appendLore("&7Данный предмет поможет","&7вам искать ваших врагов","","&fЦена &e175 монет","","&aНажмите чтобы купить");

        return item;
    }

    public static Item TNT() {
        Item item = new Item("&eДинамит &7[1x]",new ArrayList<>(), Material.TNT,1,0);

        item.appendLore("&7Данный предмет поможет","&7вам разрушить блоки","&7в 5 блоках от вас","","&fЦена &e125 монет","","&aНажмите чтобы купить");

        return item;
    }

    public static Item IRON_ARMOR() {
        Item item = new Item("&eЖелезный сет &7[1x]",new ArrayList<>(), Material.IRON_CHESTPLATE,1,0);

        item.appendLore("&7Данный предмет поможет","&7вам отбить удары противников","","&fЦена &e240 монет","","&aНажмите чтобы купить");

        return item;
    }

    public static Item ENDER_PEARL() {
        Item item = new Item("&eЭндер-Пёрл &7[1x]",new ArrayList<>(), Material.ENDER_PEARL,1,0);

        item.appendLore("&7Данный предмет поможет","&7вам перелетать через карту","","&fЦена &e250 монет","","&aНажмите чтобы купить");

        return item;
    }

    private static void checkItem(PlayerInfo playerInfo,ItemStack stack,int money) {
        PlayerInventory inventory = playerInfo.getPlayer().getInventory();

        if (playerInfo.getMoney() < money) {
            int needMoney = money-playerInfo.getMoney();
            playerInfo.getPlayer().sendMessage(BWUtils.colored("&cНедостаточное количество монет &6&l" + needMoney + BWUtils.plurals(needMoney," монета"," монеты"," монет")));
            playerInfo.getPlayer().playSound(playerInfo.getPlayer().getLocation(), Sound.VILLAGER_NO,100,1);
        } else {
            playerInfo.removeMoney(money);

            VimeBedWars.getInstance().getNmsManager().showBoard(playerInfo);

            inventory.addItem(stack);
        }
    }

    private static void checkArmor(PlayerInfo playerInfo,int money) {
        PlayerInventory inventory = playerInfo.getPlayer().getInventory();

        if (playerInfo.getMoney() < money) {
            int needMoney = money-playerInfo.getMoney();
            playerInfo.getPlayer().sendMessage(BWUtils.colored("&cНедостаточное количество монет &6&l" + needMoney + BWUtils.plurals(needMoney," монета"," монеты"," монет")));
            playerInfo.getPlayer().playSound(playerInfo.getPlayer().getLocation(), Sound.VILLAGER_NO,100,1);
        } else {
            playerInfo.removeMoney(money);

            VimeBedWars.getInstance().getNmsManager().showBoard(playerInfo);

            inventory.setHelmet(new ItemStack(Material.IRON_HELMET));
            inventory.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
            inventory.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
            inventory.setBoots(new ItemStack(Material.IRON_BOOTS));

        }
    }

    public static void buyItem(Player player, Item item) {
        PlayerInfo playerInfo = PlayerInfo.getPlayerInfo(player.getDisplayName());

        Item GAME_SWORD = new Item(null,new ArrayList<>(),Material.STONE_SWORD,1,0);
        Item GAME_WOOL = new Item(null,new ArrayList<>(),Material.WOOL,2,playerInfo.getTeam().getColorID());
        Item GAME_SHEAR = new Item(null,new ArrayList<>(),Material.SHEARS,1,0);
        Item GAME_BRIDGE = new Item("&eСтроительный мост",new ArrayList<>(),Material.CONCRETE,1,4);
        Item GAME_ROD = new Item(null,new ArrayList<>(),Material.FISHING_ROD,1,0);
        Item GAME_PICKAXE = new Item("&eКирка",new ArrayList<>(),Material.IRON_PICKAXE,1,0);
        Item GAME_PEARL = new Item(null,new ArrayList<>(),Material.ENDER_PEARL,1,0);
        Item GAME_GPS = new Item("&eGPS Трекер",new ArrayList<>(),Material.COMPASS,1,0);
        Item GAME_TNT = new Item(null,new ArrayList<>(),Material.TNT,1,0);

        switch (item.getItemStack().getType()) {
            case STONE_SWORD:
                checkItem(playerInfo,GAME_SWORD.getItemStack(),90);
                break;
            case WOOL:
                checkItem(playerInfo,GAME_WOOL.getItemStack(),1);
                break;
            case SHEARS:
                checkItem(playerInfo,GAME_SHEAR.getItemStack(),60);
                break;
            case CONCRETE:
                checkItem(playerInfo,GAME_BRIDGE.getItemStack(),40);
                break;
            case FISHING_ROD:
                checkItem(playerInfo,GAME_ROD.getItemStack(),50);
                break;
            case IRON_PICKAXE:
                checkItem(playerInfo,GAME_PICKAXE.getItemStack(),150);
                break;
            case ENDER_PEARL:
                checkItem(playerInfo,GAME_PEARL.getItemStack(),250);
                break;
            case COMPASS:
                checkItem(playerInfo,GAME_GPS.getItemStack(),175);
                break;
            case TNT:
                checkItem(playerInfo,GAME_TNT.getItemStack(),125);
                break;
            case IRON_CHESTPLATE:
                checkArmor(playerInfo,240);
        }
    }

}
