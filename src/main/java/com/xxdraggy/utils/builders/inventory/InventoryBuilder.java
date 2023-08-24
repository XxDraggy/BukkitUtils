package com.xxdraggy.utils.builders.inventory;

import com.xxdraggy.utils.data.inventory.InventoryData;
import com.xxdraggy.utils.data.inventory.InventoryItem;
import com.xxdraggy.utils.data.inventory.InventoryType;
import com.xxdraggy.utils.data.inventory.border.InventoryBorder;
import com.xxdraggy.utils.inventory.InventoryController;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class InventoryBuilder {
    private InventoryData data = new InventoryData();
    public InventoryData getData() {
        return data;
    }

    public InventoryBuilder() {}
    private InventoryBuilder(InventoryData data) {
        this.data = data;
    }

    public InventoryBuilder clone() {
        return new InventoryBuilder(data);
    }

    public InventoryBuilder setType(InventoryType type) {
        this.data.setType(type);

        return this;
    }

    public InventoryBuilder setRows(int rows) {
        this.data.setRows(rows);

        return this;
    }

    public InventoryBuilder setItem(Function<InventoryItem, InventoryItem> builder) {
        this.setItem(builder.apply(new InventoryItem()));

        return this;
    }
    public InventoryBuilder setItem(InventoryItem item) {
        this.data.addItem(item);

        return this;
    }

    public InventoryBuilder addItems(int startSlot, int endSlot, Predicate<Integer> condition, Function<InventoryItem, InventoryItem>... functions) {
        InventoryItem[] items = new InventoryItem[functions.length];

        for (int i = 0; i < functions.length; i++)
            items[i] = functions[i].apply(new InventoryItem());

        this.addItems(startSlot, endSlot, condition, items);

        return this;
    }
    public InventoryBuilder addItems(int startSlot, int endSlot, Predicate<Integer> condition, InventoryItem... items) {
        int index = 0;

        for (int slot = startSlot; slot == endSlot; slot++) {
            if(!condition.test(slot)) continue;

            InventoryItem item = items[index];

            item.setSlot(slot);

            data.addItem(item);

            index++;
        }

        return this;
    }

    public InventoryBuilder setTitle(String title) {
        this.data.setTitle(title);

        return this;
    }

    public InventoryBuilder setBorder(Function<InventoryBorder, InventoryBorder> builder) {
        this.data.setBorder(builder.apply(new InventoryBorder()));

        return this;
    }

    public InventoryBuilder removeItems() {
        this.data.setItems(new HashMap<>());

        return this;
    }
    public InventoryBuilder clean() {
        this.data = new InventoryData();

        return this;
    }
    
    public Inventory open(Player player) {
        player.openInventory(this.build(player));

        return InventoryBuilder.build(this.data);
    }
    public Inventory build(Player owner) {
        this.data.setOwner(owner);

        return InventoryBuilder.build(this.data);
    }

    public static Inventory build(InventoryData data) {
        Inventory inventory;

        // Create Inventory
        if(data.getType() == InventoryType.Container)
            if(data.getTitle() == null)
                inventory = Bukkit.createInventory(data.getOwner(), data.getRows() * 9);
            else
                inventory = Bukkit.createInventory(data.getOwner(), data.getRows() * 9, data.getTitle());
        else
            if(data.getTitle() == null)
                inventory = Bukkit.createInventory(data.getOwner(), data.getType().getBukkit());
            else
                inventory = Bukkit.createInventory(data.getOwner(), data.getType().getBukkit(), data.getTitle());

        // Add Border
        if(data.getBorder().isUsed()) {
            // Top
            for (int i = 0; i < 9; i++)
                inventory.setItem(i, data.getBorder().getItem());

            // Bottom
            for (int i = 0; i < 9; i++)
                inventory.setItem(i + ((data.getRows() - 1) * 9), data.getBorder().getItem());

            // Left
            for (int slot = 0; slot < data.getRows() * 9; slot += 9)
                inventory.setItem(slot, data.getBorder().getItem());

            // Right
            for (int slot = 8; slot < data.getRows() * 9; slot += 9)
                inventory.setItem(slot, data.getBorder().getItem());

            // Back Item
            if(data.getBorder().getBackButton().isUsed())
                data.addItem(new InventoryItem()
                        .setSlot((data.getRows() - 1) * 9)
                        .setItem(data.getBorder().getBackButton().getItem())
                        .setCallback(data.getBorder().getBackButton().getCallback())
                );

            // Close Item
            if(data.getBorder().getCloseButton().isUsed())
                data.addItem(new InventoryItem()
                        .setSlot(0)
                        .setItem(data.getBorder().getCloseButton().getItem())
                        .setCallback(data.getBorder().getCloseButton().getCallback())
                );

            // Proceed Item
            if(data.getBorder().getProceedButton().isUsed())
                data.addItem(new InventoryItem()
                        .setSlot(data.getRows() * 9 - 1)
                        .setItem(data.getBorder().getProceedButton().getItem())
                        .setCallback(data.getBorder().getProceedButton().getCallback())
                );
        }

        // Add items
        data.getItems().forEach((slot, object) ->
            inventory.setItem(slot, object.getItem())
        );

        data.setInventory(inventory);

        InventoryController.addInventory(data);

        return inventory;
    }
}
