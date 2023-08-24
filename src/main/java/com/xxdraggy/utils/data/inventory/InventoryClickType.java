package com.xxdraggy.utils.data.inventory;

import org.bukkit.event.inventory.ClickType;

public enum InventoryClickType {
    Any(),
    AnyShiftClick(),
    MouseWheel(ClickType.MIDDLE),
    DoubleClick(ClickType.DOUBLE_CLICK),

    Drop(ClickType.DROP),
    StackDrop(ClickType.CONTROL_DROP),

    AnyLeftClick(),
    LeftClick(ClickType.LEFT),
    ShiftAndLeftClick(ClickType.SHIFT_LEFT),

    AnyRightClick(),
    RightClick(ClickType.RIGHT),
    ShiftAndRightClick(ClickType.SHIFT_RIGHT);

    InventoryClickType() {}

    InventoryClickType(ClickType type) {
        this.type = type;
    }

    ClickType type;

    public ClickType getType() {
        return type;
    }
}
