package com.vimebedwars.game.object.skull;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.vimebedwars.game.usable.Item;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

public class Skull {

    public static Item getHead(String skinURL, String displayName) {
        Item head = new Item(displayName,new ArrayList<>(),Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta headMeta = head.getItemStack().getItemMeta();

        if (skinURL == null || skinURL.isEmpty()) {
            return head;
        }

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", skinURL).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        try {
            Field profileField = ItemMeta.class.getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        head.getItemStack().setItemMeta(headMeta);
        return head;
    }
}