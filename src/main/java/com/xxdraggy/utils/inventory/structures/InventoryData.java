package com.xxdraggy.utils.inventory.structures;

import com.xxdraggy.utils.data.InventoryType;
import com.xxdraggy.utils.inventory.structures.border.InventoryBorder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class InventoryData {
    public int rows;
    public Player owner;
    public String title;
    public InventoryType type;
    public List<InventoryItem> items;
    public InventoryBorder border;
    public Inventory inventory;
}