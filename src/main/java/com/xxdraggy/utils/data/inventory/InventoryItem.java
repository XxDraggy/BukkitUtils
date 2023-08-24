package com.xxdraggy.utils.data.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class InventoryItem {
    private ItemStack item;
    public ItemStack getItem() {
        return item;
    }
    public InventoryItem setItem(ItemStack item) {
        this.item = item;

        return this;
    }

    private Map<InventoryClickType, Function<Player, Object>> callbacks = new HashMap<>();
    public Map<InventoryClickType, Function<Player, Object>> getCallbacks() {
        return callbacks;
    }
    public InventoryItem setCallback(Function<Player, Object> function) {
        this.callbacks.put(InventoryClickType.Any, function);

        return this;
    }
    public InventoryItem addCallback(InventoryClickType type, Function<Player, Object> function) {
        this.callbacks.put(type, function);

        return this;
    }

    private int slot;
    public int getSlot() {
        return slot;
    }
    public InventoryItem setSlot(int slot) {
        this.slot = slot;

        return this;
    }

    public String toString() {
        String itemString = item.toString() + "\n";

        return itemString + slot;
    }

    public Object call(ClickType type, Player player) {
        getCallbacks().forEach((key, value) -> {
            switch (key) {
                case Drop:
                    if(type == ClickType.DROP)
                        value.apply(player);
                    break;
                case StackDrop:
                    if(type == ClickType.CONTROL_DROP)
                        value.apply(player);
                    break;

                case AnyLeftClick:
                    if(type.isLeftClick())
                        value.apply(player);
                    break;
                case LeftClick:
                    if(type == ClickType.LEFT)
                        value.apply(player);
                    break;
                case ShiftAndLeftClick:
                    if(type == ClickType.SHIFT_LEFT)
                        value.apply(player);
                    break;

                case AnyRightClick:
                    if(type.isRightClick())
                        value.apply(player);
                    break;
                case RightClick:
                    if(type == ClickType.RIGHT)
                        value.apply(player);
                    break;
                case ShiftAndRightClick:
                    if(type == ClickType.SHIFT_RIGHT)
                        value.apply(player);
                    break;

                case AnyShiftClick:
                    if(type.isShiftClick())
                        value.apply(player);
                    break;
                case DoubleClick:
                    if(type == ClickType.DOUBLE_CLICK)
                        value.apply(player);
                    break;
                case MouseWheel:
                    if(type == ClickType.MIDDLE)
                        value.apply(player);
                    break;
                case Any:
                    value.apply(player);
                    break;
            }
        });

        return null;
    }
}
