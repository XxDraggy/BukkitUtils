package com.xxdraggy.utils.data.inventory;

import com.xxdraggy.utils.data.inventory.border.InventoryBorder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryData {
    private int rows = 1;
    public int getRows() {
        return rows;
    }
    public InventoryData setRows(int rows) {
        this.rows = rows;

        return this;
    }

    private Player owner;
    public Player getOwner() {
        return owner;
    }
    public InventoryData setOwner(Player owner) {
        this.owner = owner;

        return this;
    }

    private String title = "";
    public String getTitle() {
        return title;
    }
    public InventoryData setTitle(String title) {
        this.title = title;

        return this;
    }

    private InventoryType type = InventoryType.Container;
    public InventoryType getType() {
        return type;
    }
    public InventoryData setType(InventoryType type) {
        this.type = type;

        return this;
    }

    private Map<Integer, InventoryItem> items = new HashMap<>();
    public Map<Integer, InventoryItem> getItems() {
        return items;
    }
    public InventoryData setItems(Map<Integer, InventoryItem> items) {
        this.items = items;

        return this;
    }
    public InventoryData addItem(InventoryItem item) {
        this.items.put(item.getSlot(), item);

        return this;
    }

    private InventoryBorder border = new InventoryBorder();
    public InventoryBorder getBorder() {
        return border;
    }
    public InventoryData setBorder(InventoryBorder border) {
        this.border = border;

        return this;
    }

    private Inventory inventory;
    public Inventory getInventory() {
        return inventory;
    }
    public InventoryData setInventory(Inventory inventory) {
        this.inventory = inventory;

        return this;
    }
}