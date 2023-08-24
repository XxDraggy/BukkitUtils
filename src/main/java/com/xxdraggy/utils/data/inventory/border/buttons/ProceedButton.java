package com.xxdraggy.utils.data.inventory.border.buttons;

import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.Heads;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class ProceedButton {
    private Function<Player, Object> callback = player -> null;
    public Function<Player, Object> getCallback() {
        return callback;
    }
    public ProceedButton setCallback(Function<Player, Object> callback) {
        this.callback = callback;

        return this;
    }

    private ItemStack item = Creator.item(Heads.Colors.Green.Bright.getHead())
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
    public ItemStack getItem() {
        return item;
    }
    public ProceedButton setItem(ItemStack item) {
        this.item = item;

        return this;
    }

    private boolean use = true;
    public boolean isUsed() {
        return use;
    }
    public ProceedButton remove() {
        this.use = false;

        return this;
    }
}
