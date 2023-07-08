package com.xxdraggy.utils.inventory.structures.border.buttons;

import com.xxdraggy.utils.Creator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class CloseButton {
    public Function<Player, Void> callBack = player -> {
        player.closeInventory();
        return null;
    };

    public ItemStack item = Creator.item()
        .setMaterial(Material.RED_STAINED_GLASS_PANE)
        .setName(
            Creator.text("Close")
                .darkRed()
                .bold()
                .toString()
        )
        .setLore(
            Creator.text("Click to close this window.")
                .gray()
                .italic()
                .toString()
        )
        .build();

    public boolean use = true;
}
