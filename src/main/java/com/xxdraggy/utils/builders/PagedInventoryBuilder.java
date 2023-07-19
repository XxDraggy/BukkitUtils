package com.xxdraggy.utils.builders;

import com.xxdraggy.utils.inventory.builders.PageDataBuilder;
import com.xxdraggy.utils.inventory.structures.PageData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.function.Function;

public class PagedInventoryBuilder {
    private PageDataBuilder builder = new PageDataBuilder();

    public PagedInventoryBuilder() {

    }
    private PagedInventoryBuilder(PageDataBuilder builder) {
        this.builder = builder;
    }

    public PagedInventoryBuilder clone() {
        return new PagedInventoryBuilder(builder);
    }

    public PagedInventoryBuilder setGoFirstItem(ItemStack item) {
        this.builder.setGoFirstItem(item);

        return this;
    }

    public PagedInventoryBuilder setNextPageItem(ItemStack item) {
        this.builder.setNextPageItem(item);

        return this;
    }

    public PagedInventoryBuilder setLastPageItem(ItemStack item) {
        this.builder.setLastPageItem(item);

        return this;
    }

    public PagedInventoryBuilder addInventory(Function<InventoryBuilder, InventoryBuilder> builder) {
        this.builder.addInventory(builder.apply(new InventoryBuilder()));

        return this;
    }
    public PagedInventoryBuilder addInventory(InventoryBuilder builder) {
        this.builder.addInventory(builder);

        return this;
    }
    public PagedInventoryBuilder addInventories(Function<InventoryBuilder, InventoryBuilder>... builder) {
        Arrays.asList(builder).forEach(function -> this.builder.addInventory(function.apply(new InventoryBuilder())));

        return this;
    }
    public PagedInventoryBuilder addInventory(InventoryBuilder... builders) {
        Arrays.asList(builders).forEach(builder -> this.builder.addInventory(builder));

        return this;
    }

    public Inventory build(Player player) {
        builder.setHolder(player);

        return PagedInventoryBuilder.build(builder.build());
    }

    public static Inventory build(PageData data) {
        for (int i = 0; i < data.inventories.size(); i++) {
            InventoryBuilder inventoryBuilder = data.inventories.get(i);
            int index = i;

            // Next
            inventoryBuilder.setItem(inventoryItemBuilder -> inventoryItemBuilder
                    .setSlot(inventoryBuilder.getData().rows * 8 + 4)
                    .setItem(data.lastItem)
                    .setCallback(player -> {
                        player.openInventory(data.inventories.get(index + 1).build(player));

                        return null;
                    })
            );

            // First
            inventoryBuilder.setItem(inventoryItemBuilder -> inventoryItemBuilder
                    .setSlot(inventoryBuilder.getData().rows * 9 - 4)
                    .setItem(data.lastItem)
                    .setCallback(player -> {
                        player.openInventory(data.inventories.get(0).build(player));

                        return null;
                    })
            );

            // Last
            inventoryBuilder.setItem(inventoryItemBuilder -> inventoryItemBuilder
                    .setSlot(inventoryBuilder.getData().rows * 9 - 3)
                    .setItem(data.lastItem)
                    .setCallback(player -> {
                        player.openInventory(data.inventories.get(index - 1).build(player));

                        return null;
                    })
            );
        };

        return data.inventories.get(0).build(data.holder);
    }
}