package com.xxdraggy.utils.builders;

import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.data.attribute.AttributeOperation;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {
    private ItemStack item;
    private ItemMeta meta;

    public ItemBuilder() {
        item = Creator.item(Material.DIAMOND_BLOCK);
        meta = item.getItemMeta();
    }

    public ItemBuilder(ItemStack item) {
        this.item = item;
        meta = item.getItemMeta();
    }

    private ItemBuilder(ItemStack item, ItemMeta meta) {
        this.item = item;
        this.meta = meta;
    }

    public ItemBuilder clone() {
        return new ItemBuilder(item, meta);
    }

    public ItemBuilder setName(String name) {
        meta.setDisplayName(name);

        return this;
    }
    
    public ItemBuilder setLore(String ...lore) {
        meta.setLore(Arrays.asList(lore));

        return this;
    }
    public ItemBuilder setLore(List<String> lore) {
        meta.setLore(lore);

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
        meta.addEnchant(enchantment, level, true);

        return this;
    }
    public ItemBuilder addEnchantments(Enchantment ...enchantments) {
        for (Enchantment enchantment : enchantments) {
            meta.addEnchant(enchantment, 1, false);
        }

        return this;
    }

    public ItemBuilder addAttribute(Attribute attribute, float amount, AttributeOperation operation) {
        meta.addAttributeModifier(attribute, new AttributeModifier(UUID.randomUUID(), attribute.getKey().toString(), amount, operation.getBukkitOperation()));

        return this;
    }
    public ItemBuilder addAttribute(Attribute attribute, float amount, AttributeOperation operation, EquipmentSlot slot) {
        meta.addAttributeModifier(attribute, new AttributeModifier(UUID.randomUUID(), attribute.getKey().toString(), amount, operation.getBukkitOperation(), slot));

        return this;
    }
    public ItemBuilder addAttribute(UUID uuid, Attribute attribute, float amount, AttributeOperation operation) {
        meta.addAttributeModifier(attribute, new AttributeModifier(uuid, attribute.getKey().toString(), amount, operation.getBukkitOperation()));

        return this;
    }
    public ItemBuilder addAttribute(UUID uuid, Attribute attribute, float amount, AttributeOperation operation, EquipmentSlot slot) {
        meta.addAttributeModifier(attribute, new AttributeModifier(uuid, attribute.getKey().toString(), amount, operation.getBukkitOperation(), slot));

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
