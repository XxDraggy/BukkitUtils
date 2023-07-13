package com.xxdraggy.utils.inventory;

import com.xxdraggy.utils.builders.InventoryBuilder;
import com.xxdraggy.utils.inventory.builders.InventoryDataBuilder;
import com.xxdraggy.utils.inventory.structures.InventoryData;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class InventoryController {
    static List<InventoryData> inventories;

    public static void register(JavaPlugin plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryListener(), plugin);
    }

    public static Inventory registerInventory(InventoryDataBuilder builder) {
        InventoryController.inventories.add(builder.build());

        return InventoryBuilder.build(builder);
    }
}