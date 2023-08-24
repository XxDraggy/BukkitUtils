package com.xxdraggy.utils.data.inventory;

import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.Heads;
import com.xxdraggy.utils.builders.inventory.InventoryBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageData {
    private ItemStack nextItem = Creator.item(Heads.Letters.Quartz.Arrows.Right.getHead())
            .setName(Creator.text("Next Page", ChatColor.DARK_GREEN))
            .build();
    public ItemStack getNextItem() {
        return nextItem;
    }
    public void setNextItem(ItemStack nextItem) {
        this.nextItem = nextItem;
    }

    private ItemStack lastItem = Creator.item(Heads.Letters.Quartz.Arrows.Right.getHead())
            .setName(Creator.text("Last Page", ChatColor.DARK_RED))
            .build();
    public ItemStack getLastItem() {
        return lastItem;
    }
    public void setLastItem(ItemStack lastItem) {
        this.lastItem = lastItem;
    }

    private ItemStack notFurtherItem = Creator.item(Heads.Letters.Quartz.Blank.getHead())
            .setName(Creator.text("Can not go any further", ChatColor.GOLD))
            .build();
    public ItemStack getNotFurtherItem() {
        return notFurtherItem;
    }
    public void setNotFurtherItem(ItemStack notFurtherItem) {
        this.notFurtherItem = notFurtherItem;
    }

    private ItemStack firstItem = Creator.item()
            .setMaterial(Material.ITEM_FRAME)
            .setName(Creator.text("First Page", ChatColor.DARK_GRAY))
            .build();
    public ItemStack getFirstItem() {
        return firstItem;
    }
    public void setFirstItem(ItemStack firstItem) {
        this.firstItem = firstItem;
    }

    private Player holder;
    public Player getHolder() {
        return holder;
    }
    public void setHolder(Player holder) {
        this.holder = holder;
    }

    private InventoryBuilder baseInventory;
    public InventoryBuilder getBaseInventory() {
        return baseInventory;
    }
    public void setBaseInventory(InventoryBuilder baseInventory) {
        this.baseInventory = baseInventory;
    }

    private Map<Integer, List<InventoryItem>> pageItems;
    public Map<Integer, List<InventoryItem>> getPageItems() {
        return pageItems;
    }
    public void addPageItems(Integer inventoryId, List<InventoryItem> pageItems) {
        this.pageItems.put(inventoryId, pageItems);
    }

    private List<InventoryBuilder> inventories = new ArrayList<>();
    public List<InventoryBuilder> getInventories() {
        return inventories;
    }
    public void addInventory(InventoryBuilder inventory) {
        this.inventories.add(inventory);
    }
}
