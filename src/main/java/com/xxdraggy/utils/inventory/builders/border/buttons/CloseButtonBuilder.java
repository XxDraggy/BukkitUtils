package com.xxdraggy.utils.inventory.builders.border.buttons;

import com.xxdraggy.utils.inventory.structures.border.buttons.CloseButton;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class CloseButtonBuilder {
    private CloseButton data = new CloseButton();

    public CloseButton remove() {
        data.use = false;

        return build();
    }

    public CloseButtonBuilder setItem(ItemStack item) {
        this.data.item = item;

        return this;
    }

    public CloseButtonBuilder setCallBack(Function<Player, Void> callBack) {
        this.data.callBack = callBack;

        return this;
    }

    public CloseButton build() {
        return this.data;
    }
}
