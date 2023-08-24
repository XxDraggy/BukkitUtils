package com.xxdraggy.utils.data.inventory.border.buttons;

import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.Heads;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class CloseButton {
    private Function<Player, Object> callback = player -> null;
    public Function<Player, Object> getCallback() {
        return callback;
    }
    public CloseButton setCallback(Function<Player, Object> callback) {
        this.callback = callback;

        return this;
    }

    private ItemStack item = Creator.item(Heads.Colors.Red.Dark.getHead())
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
    public ItemStack getItem() {
        return item;
    }
    public CloseButton setItem(ItemStack item) {
        this.item = item;

        return this;
    }

    private boolean use = true;
    public boolean isUsed() {
        return use;
    }
    public CloseButton remove() {
        this.use = false;

        return this;
    }
}
