package com.xxdraggy.utils.inventory.structures;

import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.Heads;
import com.xxdraggy.utils.builders.InventoryBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PageData {
    public ItemStack nextItem = Creator.item(Heads.Letters.Quartz.Arrows.Right.getHead())
            .setName(Creator.text("Next Page", ChatColor.DARK_GREEN))
            .build();

    public ItemStack lastItem = Creator.item(Heads.Letters.Quartz.Arrows.Right.getHead())
            .setName(Creator.text("Last Page", ChatColor.DARK_RED))
            .build();

    public ItemStack firstItem = Creator.item()
            .setMaterial(Material.ITEM_FRAME)
            .setName(Creator.text("First Page", ChatColor.DARK_GRAY))
            .build();

    public Player holder;

    public List<InventoryBuilder> inventories = new ArrayList<>();
}
