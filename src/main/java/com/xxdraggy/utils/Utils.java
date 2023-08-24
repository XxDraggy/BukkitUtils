package com.xxdraggy.utils;

import com.xxdraggy.utils.input.InputController;
import com.xxdraggy.utils.inventory.InventoryController;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Utils {
    private static JavaPlugin plugin;

    public static void register(JavaPlugin plugin) {
        Utils.plugin = plugin;

        InputController.register(plugin);
        InventoryController.register(plugin);

        Bukkit.getLogger().log(Level.INFO, "[BukkitUtils] Successfully registered listeners!");
    }
}