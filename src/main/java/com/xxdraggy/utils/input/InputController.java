package com.xxdraggy.utils.input;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class InputController {
    static JavaPlugin plugin;

    static Map<Player, InputGui> currentInputs = new HashMap<>();

    public static void register(JavaPlugin plugin) {
        InputController.plugin = plugin;

        if(InputController.isInstalled()) {
            InputController.listen();

            Bukkit.getLogger().log(Level.INFO, "[BukkitUtils/InputController] Registered listener!");
        }
        else
            Bukkit.getLogger().log(Level.SEVERE, "[BukkitUtils/InputController] Could not find ProtocolLib in the plugin folder.\nPlease install it from https://github.com/dmulloy2/ProtocolLib/releases/download/5.0.0/ProtocolLib.jar");
    }

    public static boolean isInstalled() {
        return ProtocolLibrary.getProtocolManager() != null;
    }

    static void listen() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(plugin, PacketType.Play.Client.UPDATE_SIGN) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                Player player = event.getPlayer();

                InputGui menu = currentInputs.remove(player);

                if (menu == null)
                    return;

                event.setCancelled(true);

                boolean success = menu.callBack.test(player, event.getPacket().getStringArrays().read(0));

                if (!success && menu.reopen && !menu.force)
                    Bukkit.getScheduler().runTaskLater(plugin, () -> menu.open(player), 2L);

                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    if (player.isOnline()) {
                        Location location = menu.blockPosition.toLocation(player.getWorld());

                        player.sendBlockChange(location, location.getBlock().getBlockData());
                    }
                }, 2L);
            }
        });
    }
}