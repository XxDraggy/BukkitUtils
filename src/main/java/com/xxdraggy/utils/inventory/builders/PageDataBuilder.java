package com.xxdraggy.utils.inventory.builders;

import com.xxdraggy.utils.builders.InventoryBuilder;
import com.xxdraggy.utils.inventory.structures.PageData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PageDataBuilder {
    private PageData data = new PageData();

    public PageDataBuilder setGoFirstItem(ItemStack item) {
        this.data.firstItem = item;

        return this;
    }

    public PageDataBuilder setHolder(Player player) {
        this.data.holder = player;

        return this;
    }

    public PageDataBuilder setNextPageItem(ItemStack item) {
        this.data.nextItem = item;

        return this;
    }

    public PageDataBuilder setLastPageItem(ItemStack item) {
        this.data.lastItem = item;

        return this;
    }

    public PageDataBuilder addInventory(InventoryBuilder builder) {
        this.data.inventories.add(builder);

        return this;
    }

    public PageData build() {
        return this.data;
    }
}
