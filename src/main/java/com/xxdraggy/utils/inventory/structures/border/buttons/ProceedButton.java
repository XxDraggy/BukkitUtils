package com.xxdraggy.utils.inventory.structures.border.buttons;

import com.xxdraggy.utils.Creator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class ProceedButton {
    public Function<Player, Void> callBack = player -> {
        player.closeInventory();
        return null;
    };

    public ItemStack item = Creator.item()
        .setMaterial(Material.GREEN_STAINED_GLASS_PANE)
        .setName(
            Creator.text("Proceed")
                .green()
                .bold()
                .toString())
        .setLore(
            Creator.text("Click to proceed.")
                .gray()
                .italic()
                .toString()
        )
        .build();

    public boolean use = true;
}
