package com.xxdraggy.utils.inventory.structures;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class InventoryItem {
    public ItemStack item;
    public Function<Player, Void> clickCallBack;
    public int slot;
}
