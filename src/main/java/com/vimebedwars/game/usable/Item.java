package com.vimebedwars.game.usable;

import com.vimebedwars.game.utils.BWUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

public class Item {

    private String name;
    private List<String> lore;

    private final Material material;
    private ItemStack stack;
    private ItemMeta meta;

    private final int amount;
    private int subID;

    public Item(String name, List<String> lore, Material material, int amount, int subID) {
        this.name = BWUtils.colored(name);
        this.lore = lore;
        this.material = material;
        this.stack = new ItemStack(material,amount, (short) subID);
        this.amount = amount;
        this.meta = stack.getItemMeta();
        this.subID = subID;

        meta.setDisplayName(BWUtils.colored(name));

        stack.setItemMeta(meta);
    }

    public Item(String name, List<String> lore, Material material, int amount) {
        this.name = BWUtils.colored(name);
        this.lore = lore;
        this.material = material;
        this.stack = new ItemStack(material,amount);
        this.amount = amount;
        this.meta = stack.getItemMeta();

        meta.setDisplayName(BWUtils.colored(name));

        stack.setItemMeta(meta);
    }

    public void appendEnchant(Enchantment enchantment,int level) {
        meta.addEnchant(enchantment,level,false);
        stack.setItemMeta(meta);
    }


    public void appendName(Enchantment enchantment,int level) {
        meta.addEnchant(enchantment,level,false);
        stack.setItemMeta(meta);
    }

    public void appendLore(String... lore) {
        meta.setLore(BWUtils.colored(lore));
        stack.setItemMeta(meta);
    }

    public void appendColor(Color color) {
        LeatherArmorMeta armorMeta = (LeatherArmorMeta) meta;

        armorMeta.setColor(color);

        stack.setItemMeta(meta);
    }


    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemStack getItemStack() {
        return stack;
    }

    public int getAmount() {
        return amount;
    }

    public int getSubID() {
        return subID;
    }
}
