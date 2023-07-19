package com.xxdraggy.utils.inventory.builders.border;

import com.xxdraggy.utils.inventory.builders.border.buttons.BackButtonBuilder;
import com.xxdraggy.utils.inventory.builders.border.buttons.CloseButtonBuilder;
import com.xxdraggy.utils.inventory.builders.border.buttons.ProceedButtonBuilder;
import com.xxdraggy.utils.inventory.structures.border.InventoryBorder;
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

    public InventoryBorderBuilder setProceedButton(Function<ProceedButtonBuilder, ProceedButtonBuilder> builder) {
        this.data.proceedButton = builder.apply(
                new ProceedButtonBuilder()
        ).build();

        return this;
    }

    public InventoryBorderBuilder setBackButton(Function<BackButtonBuilder, BackButtonBuilder> builder) {
        this.data.backButton = builder.apply(
                new BackButtonBuilder()
        ).build();

        return this;
    }

    public InventoryBorderBuilder setCloseButton(Function<CloseButtonBuilder, CloseButtonBuilder> builder) {
        this.data.closeButton = builder.apply(
                new CloseButtonBuilder()
        ).build();

        return this;
    }

    public InventoryBorder build() {
        return this.data;
    }
}
