package com.xxdraggy.utils.inventory.builders;

import com.xxdraggy.utils.inventory.structures.InventoryItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class InventoryItemBuilder {
    private InventoryItem data = new InventoryItem();

    public InventoryItemBuilder setItem(ItemStack item) {
        this.data.item = item;

        return this;
    }

    public InventoryItemBuilder setCallback(Function<Player, Void> callback) {
        this.data.callback = callback;

        return this;
    }

    public InventoryItemBuilder setSlot(int slot) {
        this.data.slot = slot;

        return this;
    }

    public InventoryItem build() {
        return this.data;
    }
}
