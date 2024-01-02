package com.vimebedwars.game.object.menu;

import com.vimebedwars.game.usable.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class BWMenu {

    private final String name;
    private final int size;

    public final Inventory inventory;

    public BWMenu(String name, int size) {
        this.name = name;
        this.size = size;
        this.inventory = Bukkit.createInventory(null, size, name);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void hide(Player player) {
        player.closeInventory();
    }

    public void show(Player player) {
        player.playSound(player.getLocation(), Sound.CHEST_OPEN,100,1);
        player.openInventory(this.inventory);
    }

    public void clear() {
        this.inventory.clear();
    }

    public void setItem(int slot, ItemStack stack) {
        this.inventory.setItem(slot,stack);
    }

    public void addItem(Item item) {
        this.inventory.addItem(item.getItemStack());
    }

    public void addItem(Material item) {
        this.inventory.addItem(new ItemStack(item));
    }

    public void addItem(ItemStack item) {
        this.inventory.addItem(item);
    }

    public void setItem(int slot, Material material) {
        this.inventory.setItem(slot,new ItemStack(material));
    }

    public void setItem(int slot, Item item) {
        this.inventory.setItem(slot,item.getItemStack());
    }



}
