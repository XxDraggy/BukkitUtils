package com.xxdraggy.utils.data.inventory.border;

import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.data.inventory.border.buttons.BackButton;
import com.xxdraggy.utils.data.inventory.border.buttons.CloseButton;
import com.xxdraggy.utils.data.inventory.border.buttons.ProceedButton;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class InventoryBorder {
    private ItemStack item = Creator.item(
        Material.BLACK_STAINED_GLASS_PANE,
        Creator.text("a")
            .magic()
            .gray()
            .toString()
    );
    public ItemStack getItem() {
        return item;
    }
    public InventoryBorder setItem(ItemStack item) {
        this.item = item;

        return this;
    }

    private boolean useBorder = true;
    public boolean isUsed() {
        return useBorder;
    }
    public Object remove() {
        this.useBorder = false;

        return null;
    }
    public InventoryBorder setUseBorder(boolean useBorder) {
        this.useBorder = useBorder;

        return this;
    }

    private ProceedButton proceedButton = new ProceedButton();
    public ProceedButton getProceedButton() {
        return proceedButton;
    }
    public InventoryBorder setProceedButton(Function<ProceedButton, ProceedButton> function) {
        this.proceedButton = function.apply(new ProceedButton());

        return this;
    }

    private BackButton backButton = new BackButton();
    public BackButton getBackButton() {
        return backButton;
    }
    public InventoryBorder setBackButton(Function<BackButton, BackButton> function) {
        this.backButton = function.apply(new BackButton());

        return this;
    }

    private CloseButton closeButton = new CloseButton();
    public CloseButton getCloseButton() {
        return closeButton;
    }
    public InventoryBorder setCloseButton(Function<CloseButton, CloseButton> function) {
        this.closeButton = function.apply(new CloseButton());

        return this;
    }
}
