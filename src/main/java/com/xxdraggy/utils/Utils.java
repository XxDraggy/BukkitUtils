package com.xxdraggy.utils;

import com.xxdraggy.utils.input.InputController;
import com.xxdraggy.utils.inventory.InventoryController;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Level;

public class Utils {
    private static JavaPlugin plugin;

    public static void register(JavaPlugin plugin) {
        Utils.plugin = plugin;

        InputController.register(plugin);
        InventoryController.register(plugin);

        Bukkit.getLogger().log(Level.INFO, "[BukkitUtils] Successfully registered listeners!");
    }

    public static boolean playerExists(String name) {
        return Bukkit.getOfflinePlayer(name).getFirstPlayed() != 0;
    }
    public static boolean playerExists(UUID id) {
        return Bukkit.getOfflinePlayer(id).getFirstPlayed() != 0;
    }
    public static OfflinePlayer getPlayer(String name) {
        return Bukkit.getOfflinePlayer(name);
    }
    public static OfflinePlayer getPlayer(UUID id) {
        return Bukkit.getOfflinePlayer(id);
    }
    public static List<OfflinePlayer> getPlayers() {
        List<OfflinePlayer> results = new ArrayList<>();

        results.addAll(Arrays.asList(Bukkit.getOfflinePlayers()));

        return results;
    }
    public static Player getOnlinePlayer(String name) {
        List<Player> players = Utils.getOnlinePlayers();
        Player target = null;

        for (Player player : players) {
            if(Objects.equals(player.getName(), name)) {
                target = player;
            }
        }

        return target;
    }
    public static Player getOnlinePlayer(UUID id) {
        List<Player> players = Utils.getOnlinePlayers();
        Player target = null;

        for (Player player : players) {
            if(player.getUniqueId() == id) {
                target = player;
            }
        }

        return target;
    }
    public static List<Player> getOnlinePlayers() {
        List<Player> results = new ArrayList<>();

        results.addAll(Bukkit.getOnlinePlayers());

        return results;
    }
    public static OfflinePlayer getWhitelistedPlayer(String name) {
        List<OfflinePlayer> players = Utils.getWhitelistedPlayers();
        OfflinePlayer target = null;

        for (OfflinePlayer player : players) {
            if(Objects.equals(player.getName(), name)) {
                target = player;
            }
        }

        return target;
    }
    public static OfflinePlayer getWhitelistedPlayer(UUID id) {
        List<OfflinePlayer> players = Arrays.asList(Bukkit.getWhitelistedPlayers().toArray(new OfflinePlayer[0]));
        OfflinePlayer target = null;

        for (OfflinePlayer player : players) {
            if(player.getUniqueId() == id) {
                target = player;
            }
        }

        return target;
    }
    public static List<OfflinePlayer> getWhitelistedPlayers() {
        List<OfflinePlayer> results = new ArrayList<>();

        results.addAll(Bukkit.getWhitelistedPlayers());

        return results;
    }
    public static OfflinePlayer getOfflinePlayer(String name) {
        List<OfflinePlayer> players = Utils.getOfflinePlayers();
        OfflinePlayer target = null;

        for (OfflinePlayer player : players) {
            if(Objects.equals(player.getName(), name)) {
                target = player;
            }
        }

        return target;
    }
    public static OfflinePlayer getOfflinePlayer(UUID id) {
        List<OfflinePlayer> players = Utils.getOfflinePlayers();
        OfflinePlayer target = null;

        for (OfflinePlayer player : players) {
            if(player.getUniqueId() == id) {
                target = player;
            }
        }

        return target;
    }
    public static List<OfflinePlayer> getOfflinePlayers() {
        List<OfflinePlayer> results = new ArrayList<>();

        for (OfflinePlayer player : Bukkit.getOfflinePlayers())
            if(Bukkit.getPlayer(player.getUniqueId()) == null) results.add(player);

        return results;
    }
}