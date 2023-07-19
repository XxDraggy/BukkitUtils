package com.xxdraggy.utils.inventory.structures;

import com.xxdraggy.utils.data.InventoryType;
import com.xxdraggy.utils.inventory.structures.border.InventoryBorder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryData {
    public int rows = 1;
    public Player owner;
    public String title = "";
    public InventoryType type = InventoryType.Container;
    public List<InventoryItem> items = new ArrayList<>();
    public List<InventoryData> pages = new ArrayList<>();
    public InventoryBorder border = new InventoryBorder();
    public Inventory inventory;
}