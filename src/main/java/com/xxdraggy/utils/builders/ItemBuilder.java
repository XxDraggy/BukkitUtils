package com.xxdraggy.utils.builders;

import com.xxdraggy.utils.Creator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {
    private ItemStack item;
    private ItemMeta meta;

    public ItemBuilder() {
        item = Creator.item(Material.AIR);
        meta = item.getItemMeta();
    }


    public ItemBuilder setName(String name) {
        meta.setDisplayName(name);

        return this;
    }

    
    public ItemBuilder setLore(String ...lores) {
        meta.setLore(Arrays.asList(lores));

        return this;
    }

    
    public ItemBuilder setMaterial(Material material) {
        item = new ItemStack(material, item.getAmount());

        return this;
    }

    
    public ItemBuilder setAmount(int amount) {
        item = new ItemStack(item.getType(), amount);

        return this;
    }

    
    public ItemBuilder addEnchantment(Enchantment enchantment) {
        meta.addEnchant(enchantment, 1, false);

        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, level < 5);

        return this;
    }

    public ItemBuilder addEnchantments(Enchantment ...enchantments) {
        for (Enchantment enchantment : enchantments) {
            meta.addEnchant(enchantment, 1, false);
        }

        return this;
    }


    public ItemBuilder unbreakable() {
        meta.setUnbreakable(true);

        return this;
    }


    public ItemStack build() {
        item.setItemMeta(meta);

        return item;
    }
}
