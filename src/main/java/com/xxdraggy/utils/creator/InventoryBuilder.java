package com.xxdraggy.utils.creator;

import com.xxdraggy.utils.enums.InventoryType;
import com.xxdraggy.utils.inventory.InventoryController;
import com.xxdraggy.utils.inventory.builders.InventoryDataBuilder;
import com.xxdraggy.utils.inventory.builders.border.InventoryBorderBuilder;
import com.xxdraggy.utils.inventory.structures.border.InventoryBorder;
import com.xxdraggy.utils.inventory.structures.InventoryData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class InventoryBuilder {
    private final InventoryDataBuilder builder = new InventoryDataBuilder();

    public InventoryBuilder setType(InventoryType type) {
        this.builder.setType(type);

        return this;
    }


    public InventoryBuilder setRows(int rows) {
        this.builder.setRows(rows);

        return this;
    }


    public InventoryBuilder setItem(ItemStack item, int slot, Function<Player, Void> callBack) {
        this.builder.addItem((itemBuilder) -> itemBuilder
                .setItem(item)
                .setSlot(slot)
                .setClickCallBack(callBack)
                .build()
        );

        return this;
    }


    public InventoryBuilder setTitle(String title) {
        this.builder.setTitle(title);

        return this;
    }


    public InventoryBuilder setBorder(Function<InventoryBorderBuilder, InventoryBorder> border) {
        this.builder.setBorder(border);

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
                inventory.setItem(i + (data.rows - 1) * 9, data.border.item);

            // Left
            for (int slot = 0; slot < data.rows * 9; slot += 9)
                inventory.setItem(slot, data.border.item);

            // Right
            for (int slot = 8; slot < data.rows * 9; slot += 9)
                inventory.setItem(slot, data.border.item);

            // Back Item
            if(data.border.backButton.use) {
                int backItemSlot = data.rows * 8 + 1;
                ItemStack backItem = data.border.backButton.item;

                builder.addItem((itemBuilder) -> itemBuilder
                        .setSlot(backItemSlot)
                        .setItem(backItem)
                        .setClickCallBack(borderData.border.backButton.callBack)
                        .build()
                );
            }

            // Close Item
            if(data.border.closeButton.use) {
                ItemStack closeItem = data.border.closeButton.item;

                builder.addItem((itemBuilder) -> itemBuilder
                        .setSlot(0)
                        .setItem(closeItem)
                        .setClickCallBack(borderData.border.closeButton.callBack)
                        .build()
                );
            }

            // Proceed Item
            if(data.border.proceedButton.use) {
                int proceedItemSlot = data.rows * 9 - 1;
                ItemStack proceedItem = data.border.proceedButton.item;

                builder.addItem((itemBuilder) -> itemBuilder
                        .setSlot(proceedItemSlot)
                        .setItem(proceedItem)
                        .setClickCallBack(borderData.border.proceedButton.callBack)
                        .build()
                );
            }
        }

        data = builder.build();

        // Add items
        data.items.forEach((object) -> inventory.setItem(object.slot, object.item));

        builder.setInventory(inventory);

        InventoryController.registerInventory(builder);

        return inventory;
    }
}
