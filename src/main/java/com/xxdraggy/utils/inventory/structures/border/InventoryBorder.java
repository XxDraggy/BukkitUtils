package com.xxdraggy.utils.inventory.structures.border;

import com.xxdraggy.utils.inventory.structures.border.buttons.BackButton;
import com.xxdraggy.utils.inventory.structures.border.buttons.CloseButton;
import com.xxdraggy.utils.inventory.structures.border.buttons.ProceedButton;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import com.xxdraggy.utils.Creator;

public class InventoryBorder {
    public ItemStack item = Creator.item(
        Material.BLACK_STAINED_GLASS_PANE,
        Creator.text("a")
            .gray()
            .magic()
            .toString()
    );
    public boolean useBorder = false;
    public int extraWidth;
    public ProceedButton proceedButton;
    public BackButton backButton;
    public CloseButton closeButton;
}
