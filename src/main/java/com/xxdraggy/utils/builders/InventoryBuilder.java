package com.xxdraggy.utils.builders;

import com.xxdraggy.utils.data.InventoryType;
import com.xxdraggy.utils.inventory.InventoryController;
import com.xxdraggy.utils.inventory.builders.InventoryDataBuilder;
import com.xxdraggy.utils.inventory.builders.InventoryItemBuilder;
import com.xxdraggy.utils.inventory.builders.border.InventoryBorderBuilder;
import com.xxdraggy.utils.inventory.structures.InventoryData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.function.Function;

public class InventoryBuilder {
    private InventoryDataBuilder builder = new InventoryDataBuilder();
    public InventoryDataBuilder getBuilder() {
        return builder;
    }
    public InventoryData getData() {
        return builder.build();
    }

    public InventoryBuilder() {

    }
    private InventoryBuilder(InventoryDataBuilder builder) {
        this.builder = builder;
    }

    public InventoryBuilder clone() {
        return new InventoryBuilder(builder);
    }

    public InventoryBuilder setType(InventoryType type) {
        this.builder.setType(type);

        return this;
    }

    public InventoryBuilder setRows(int rows) {
        this.builder.setRows(rows);

        return this;
    }

    public InventoryBuilder setItem(Function<InventoryItemBuilder, InventoryItemBuilder> builder) {
        this.builder.addItem(
                builder.apply(
                        new InventoryItemBuilder()
                ).build()
        );

        return this;
    }

    public InventoryBuilder setTitle(String title) {
        this.builder.setTitle(title);

        return this;
    }

    public InventoryBuilder setBorder(Function<InventoryBorderBuilder, InventoryBorderBuilder> builder) {
        this.builder.setBorder(
                builder.apply(
                        new InventoryBorderBuilder()
                ).build()
        );

        return this;
    }

    public Inventory build(Player owner) {
        this.builder.setOwner(owner);

        return InventoryBuilder.build(this.builder);
    }

    public static Inventory build(InventoryDataBuilder builder) {
        Inventory inventory;
        InventoryData data = builder.build();

        // Create Inventory
        if(data.type == InventoryType.Container)
            if(data.title == null)
                inventory = Bukkit.createInventory(data.owner, data.rows);
            else
                inventory = Bukkit.createInventory(data.owner, data.rows, data.title);
        else
            if(data.title == null)
                inventory = Bukkit.createInventory(data.owner, data.type.type);
            else
                inventory = Bukkit.createInventory(data.owner, data.type.type, data.title);

        // Add Border
        if(data.border.useBorder) {
            InventoryData borderData = data;

            // Top
            for (int i = 0; i < 9; i++)
                inventory.setItem(i, data.border.item);

            // Bottom
            for (int i = 0; i < 1; i++)
                inventory.setItem(i + ((data.rows - 1) * 9 + 1), data.border.item);

            // Left
            for (int slot = 0; slot < data.rows * 9; slot += 9)
                inventory.setItem(slot, data.border.item);

            // Right
            for (int slot = 8; slot < data.rows * 9; slot += 9)
                inventory.setItem(slot, data.border.item);

            // Back Item
            if(data.border.backButton.use)
                builder.addItem(new InventoryItemBuilder()
                        .setSlot(data.rows * 8 + 1)
                        .setItem(data.border.backButton.item)
                        .setCallback(borderData.border.backButton.callBack)
                        .build()
                );

            // Close Item
            if(data.border.closeButton.use)
                builder.addItem(new InventoryItemBuilder()
                        .setSlot(0)
                        .setItem(data.border.closeButton.item)
                        .setCallback(borderData.border.closeButton.callBack)
                        .build()
                );

            // Proceed Item
            if(data.border.proceedButton.use)
                builder.addItem(new InventoryItemBuilder()
                        .setSlot(data.rows * 9 - 1)
                        .setItem(data.border.proceedButton.item)
                        .setCallback(borderData.border.proceedButton.callBack)
                        .build()
                );
        }

        data = builder.build();

        // Add items
        data.items.forEach((object) -> inventory.setItem(object.slot, object.item));

        builder.setInventory(inventory);

        InventoryController.registerInventory(builder);

        return inventory;
    }
}
