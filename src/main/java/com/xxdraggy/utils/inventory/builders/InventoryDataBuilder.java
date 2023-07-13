package com.xxdraggy.utils.inventory.builders;

import com.xxdraggy.utils.data.InventoryType;
import com.xxdraggy.utils.inventory.builders.border.InventoryBorderBuilder;
import com.xxdraggy.utils.inventory.structures.InventoryData;
import com.xxdraggy.utils.inventory.structures.InventoryItem;
import com.xxdraggy.utils.inventory.structures.border.InventoryBorder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.function.Function;

public class InventoryDataBuilder {
    private InventoryData data = new InventoryData();

    public InventoryDataBuilder setRows(int rows) {
        this.data.rows = rows;

        return this;
    }

    public InventoryDataBuilder setOwner(Player owner) {
        this.data.owner = owner;

        return this;
    }

    public InventoryDataBuilder setTitle(String title) {
        this.data.title = title;

        return this;
    }

    public InventoryDataBuilder setType(InventoryType type) {
        this.data.type = type;

        return this;
    }

    public InventoryDataBuilder addItem(Function<InventoryItemBuilder, InventoryItem> item) {
        this.data.items.add(item.apply(new InventoryItemBuilder()));

        return this;
    }

    public InventoryDataBuilder setBorder(Function<InventoryBorderBuilder, InventoryBorder> border) {
        this.data.border = border.apply(new InventoryBorderBuilder());

        return this;
    }

    public InventoryDataBuilder setInventory(Inventory inventory) {
        this.data.inventory = inventory;

        return this;
    }

    public InventoryData build() {
        return this.data;
    }
}