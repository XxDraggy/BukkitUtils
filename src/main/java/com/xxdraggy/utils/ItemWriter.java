package com.xxdraggy.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemWriter {
    public static ItemStack name(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack lore(ItemStack item, String... lore) {
        ItemMeta meta = item.getItemMeta();

        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack amount(ItemStack item, int amount) {
        item.setAmount(amount);

        return item;
    }

    public static ItemStack material(ItemStack item, Material material) {
        ItemStack newItem = new ItemStack(material, item.getAmount());

        newItem.setItemMeta(item.getItemMeta());

        return item;
    }
}
