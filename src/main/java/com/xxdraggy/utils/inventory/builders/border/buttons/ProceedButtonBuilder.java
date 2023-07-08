package com.xxdraggy.utils.inventory.builders.border.buttons;

import com.xxdraggy.utils.inventory.structures.border.buttons.ProceedButton;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class ProceedButtonBuilder {
    private ProceedButton data = new ProceedButton();

    public ProceedButton remove() {
        data.use = false;

        return build();
    }

    public ProceedButtonBuilder setItem(ItemStack item) {
        this.data.item = item;

        return this;
    }

    public ProceedButtonBuilder setCallBack(Function<Player, Void> callBack) {
        this.data.callBack = callBack;

        return this;
    }

    public ProceedButton build() {
        return this.data;
    }
}
