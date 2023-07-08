package com.xxdraggy.utils.input;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

public class InputGui {
    SignType signType = SignType.Birch;
    List<String> lines;

    PacketContainer openSign = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.OPEN_SIGN_EDITOR);
    PacketContainer signData = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.TILE_ENTITY_DATA);

    BiPredicate<Player, String[]> callBack;
    boolean reopen;

    public BlockPosition blockPosition;

    public boolean force;

    InputGui() {}

    InputGui(List<String> lines, boolean reopen, BiPredicate<Player, String[]> callback) {
        this.lines = lines;
        this.reopen = reopen;
        this.callBack = callback;
    }

    InputGui(List<String> lines, boolean reopen, BiPredicate<Player, String[]> callback, SignType type) {
        this.lines = lines;
        this.reopen = reopen;
        this.callBack = callback;
        this.signType = type;
    }

    public void open(Player player) {
        Objects.requireNonNull(player, "Player must be given!");

        if (!player.isOnline())
            return;

        Location playerLocation = player.getLocation();

        this.blockPosition = new BlockPosition(
                playerLocation.getBlockX(),
                playerLocation.getBlockY()
                        +
                        (255 - playerLocation.getBlockY()),
                playerLocation.getBlockZ());

        player.sendBlockChange(this.blockPosition.toLocation(playerLocation.getWorld()), signType.block.createBlockData());

        openSign.getBlockPositionModifier().write(0, this.blockPosition);

        NbtCompound signNBT = (NbtCompound) signData.getNbtModifier().read(0);

        for (int line = 0; line < 4; line++) {
            signNBT.put(
                    "text"
                    +
                    (line + 1),
                    this.lines.size() > line
                    ?
                    String.format("{\"text\":\"%s\"}", ChatColor.translateAlternateColorCodes('&', this.lines.get(line)))
                    :
                    ""
            );
        }

        signNBT.put("x", this.blockPosition.getX());
        signNBT.put("y", this.blockPosition.getY());
        signNBT.put("z", this.blockPosition.getZ());
        signNBT.put("id", signType.id);

        signData.getBlockPositionModifier().write(0, this.blockPosition);
        signData.getIntegers().write(0, 9);
        signData.getNbtModifier().write(0, signNBT);

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, signData);
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, openSign);
        } catch (InvocationTargetException exception) {
            exception.printStackTrace();
        }

        InputController.currentInputs.put(player, this);
    }

    public void close(Player player) {
        close(player, false);
    }

    public void close(Player player, boolean force) {
        this.force = force;

        if (player.isOnline())
            player.closeInventory();
    }
}