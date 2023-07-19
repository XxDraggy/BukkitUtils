package com.xxdraggy.utils.inventory.builders.border.buttons;

import com.xxdraggy.utils.inventory.structures.border.buttons.BackButton;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class BackButtonBuilder {
    private BackButton data = new BackButton();

    public BackButtonBuilder remove() {
        data.use = false;

        return this;
    }

    public BackButtonBuilder setItem(ItemStack item) {
        this.data.item = item;

        return this;
    }

    public BackButtonBuilder setCallBack(Function<Player, Void> callBack) {
        this.data.callBack = callBack;

        return this;
    }

    public BackButton build() {
        return this.data;
    }
}
