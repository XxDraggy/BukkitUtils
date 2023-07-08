package com.xxdraggy.utils.inventory.builders.border;

import com.xxdraggy.utils.inventory.builders.border.buttons.BackButtonBuilder;
import com.xxdraggy.utils.inventory.builders.border.buttons.CloseButtonBuilder;
import com.xxdraggy.utils.inventory.builders.border.buttons.ProceedButtonBuilder;
import com.xxdraggy.utils.inventory.structures.border.buttons.BackButton;
import com.xxdraggy.utils.inventory.structures.border.buttons.CloseButton;
import com.xxdraggy.utils.inventory.structures.border.InventoryBorder;
import com.xxdraggy.utils.inventory.structures.border.buttons.ProceedButton;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class InventoryBorderBuilder {
    private InventoryBorder data = new InventoryBorder();

    public InventoryBorderBuilder useBorder() {
        this.data.useBorder = true;

        return this;
    }

    public InventoryBorderBuilder setBorderItem(ItemStack item) {
        this.data.item = item;

        return this;
    }

    public InventoryBorderBuilder setExtraWidth(int extraWidth) {
        this.data.extraWidth = extraWidth;

        return this;
    }

    public InventoryBorderBuilder setProceedButton(Function<ProceedButtonBuilder, ProceedButton> proceedButton) {
        this.data.proceedButton = proceedButton.apply(new ProceedButtonBuilder());

        return this;
    }

    public InventoryBorderBuilder setBackButton(Function<BackButtonBuilder, BackButton> backButton) {
        this.data.backButton = backButton.apply(new BackButtonBuilder());

        return this;
    }

    public InventoryBorderBuilder setCloseButton(Function<CloseButtonBuilder, CloseButton> closeButton) {
        this.data.closeButton = closeButton.apply(new CloseButtonBuilder());

        return this;
    }

    public InventoryBorder build() {
        return this.data;
    }
}
