package com.xxdraggy.utils.builders.inventory;

import com.xxdraggy.utils.data.inventory.InventoryItem;
import com.xxdraggy.utils.data.inventory.PageData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class PagedInventoryBuilder {
    private PageData builder = new PageData();

    public PagedInventoryBuilder() {

    }
    private PagedInventoryBuilder(PageData builder) {
        this.builder = builder;
    }

    public PagedInventoryBuilder clone() {
        return new PagedInventoryBuilder(builder);
    }

    public PagedInventoryBuilder setGoFirstItem(ItemStack item) {
        this.builder.setFirstItem(item);

        return this;
    }

    public PagedInventoryBuilder setNextPageItem(ItemStack item) {
        this.builder.setNextItem(item);

        return this;
    }

    public PagedInventoryBuilder setLastPageItem(ItemStack item) {
        this.builder.setLastItem(item);

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
    public PagedInventoryBuilder addInventories(InventoryBuilder... builders) {
        Arrays.asList(builders).forEach(builder -> this.builder.addInventory(builder));

        return this;
    }

    public PagedInventoryBuilder setBaseInventory(InventoryBuilder builder) {
        this.builder.setBaseInventory(builder);

        return this;
    }
    public PagedInventoryBuilder setBaseInventory(Function<InventoryBuilder, InventoryBuilder> builder) {
        this.builder.setBaseInventory(builder.apply(new InventoryBuilder()));

        return this;
    }

    public PagedInventoryBuilder addPageItems(int inventory, Function<InventoryItem, InventoryItem>... builders) {
        List<InventoryItem> items = new ArrayList<>();

        Arrays.asList(builders).forEach(function -> items.add(function.apply(new InventoryItem())));

        this.builder.addPageItems(inventory, items);

        return this;
    }
    public PagedInventoryBuilder addPageItems(int inventory, InventoryItem... builders) {
        Arrays.asList(builders).forEach(builder -> this.builder.addPageItems(inventory, Arrays.asList(builder)));

        return this;
    }

    public Inventory build(Player player) {
        builder.setHolder(player);

        return PagedInventoryBuilder.build(builder);
    }

    public static Inventory build(PageData data) {
        for (int i = 0; i < data.getInventories().size(); i++) {
            InventoryBuilder inventoryBuilder;
            int index = i;

            if(data.getBaseInventory() == null)
                inventoryBuilder = data.getInventories().get(i);
            else {
                inventoryBuilder = data.getBaseInventory();

                data.getPageItems().get(i).forEach(inventoryBuilder::setItem);
            }

            inventoryBuilder.setTitle(inventoryBuilder.getData().getTitle() + " (" + i + 1 + "/" + data.getInventories().size() + ")");

            // Next
            inventoryBuilder.setItem(inventoryItemBuilder -> inventoryItemBuilder
                    .setSlot(inventoryBuilder.getData().getRows() * 8 + 4)
                    .setItem(data.getLastItem())
                    .setCallback(player -> {
                        data.getInventories().get(index + 1).open(player);

                        if(data.getInventories().size() == (index + 1)) {
                            inventoryBuilder.setItem(inventoryItemBuilder1 -> inventoryItemBuilder1
                                    .setSlot(inventoryBuilder.getData().getRows() * 8 + 4)
                                    .setItem(data.getNotFurtherItem())
                            );
                        }

                        return null;
                    })
            );

            // First
            inventoryBuilder.setItem(inventoryItemBuilder -> inventoryItemBuilder
                    .setSlot(inventoryBuilder.getData().getRows() * 9 - 4)
                    .setItem(data.getLastItem())
                    .setCallback(player -> {
                        data.getInventories().get(0).open(player);

                        return null;
                    })
            );

            // Last
            inventoryBuilder.setItem(inventoryItemBuilder -> inventoryItemBuilder
                    .setSlot(inventoryBuilder.getData().getRows() * 9 - 3)
                    .setItem(data.getLastItem())
                    .setCallback(player -> {
                        data.getInventories().get(index - 1).open(player);

                        if((index - 1) == 0) {
                            inventoryBuilder.setItem(inventoryItemBuilder1 -> inventoryItemBuilder1
                                    .setSlot(inventoryBuilder.getData().getRows() * 9 - 3)
                                    .setItem(data.getNotFurtherItem())
                            );
                        }

                        return null;
                    })
            );
        };

        return data.getInventories().get(0).build(data.getHolder());
    }
}