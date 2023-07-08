package com.xxdraggy.utils.input;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public final class InputController {
    static Plugin plugin;

    static Map<Player, InputGui> currentInputs;

    public static void register(Plugin plugin) {
        InputController.plugin = plugin;

        currentInputs = new HashMap<>();

        InputController.listen();
    }

    public static void open(Player player, List<String> defaultLines, boolean reopenOnFail, BiPredicate<Player, String[]> callback) {
        new InputGui(defaultLines, reopenOnFail, callback)
                .open(player);
    }
    public static void open(Player player, boolean reopenOnFail, BiPredicate<Player, String[]> callback) {
        new InputGui(Arrays.asList(""), reopenOnFail, callback)
                .open(player);
    }

    @Contract(" -> new")
    public static @NotNull InputGuiBuilder createGui() {
        return new InputGuiBuilder();
    }

    @Contract("_, _, _ -> new")
    public static @NotNull InputGui createGui(List<String> defaultLines, boolean reopenOnFail, BiPredicate<Player, String[]> callback) {
        return new InputGui(defaultLines, reopenOnFail, callback);
    }
    @Contract("_, _ -> new")
    public static @NotNull InputGui createGui(boolean reopenOnFail, BiPredicate<Player, String[]> callback) {
        return new InputGui(Arrays.asList(""), reopenOnFail, callback);
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
