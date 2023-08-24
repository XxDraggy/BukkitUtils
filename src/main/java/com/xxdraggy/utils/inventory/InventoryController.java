package com.xxdraggy.utils.inventory;

import com.xxdraggy.utils.data.inventory.InventoryData;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class InventoryController {
    static Map<Inventory, InventoryData> inventories = new HashMap<>();

    public static void register(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), plugin);

        Bukkit.getLogger().log(Level.INFO, "[BukkitUtils/InventoryController] Registered listener!");
    }

    public static void addInventory(InventoryData builder) {
        InventoryController.inventories.put(builder.getInventory(), builder);
    }
}