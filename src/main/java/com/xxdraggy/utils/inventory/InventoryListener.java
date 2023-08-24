package com.xxdraggy.utils.inventory;

import com.xxdraggy.utils.data.inventory.InventoryData;
import com.xxdraggy.utils.data.inventory.InventoryItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryListener implements Listener {
    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        if(event.getSlotType() == InventoryType.SlotType.OUTSIDE) return;

        InventoryData inventory = InventoryController.inventories.get(event.getClickedInventory());

        if(inventory == null) return;

        event.setCancelled(true);

        InventoryItem item = inventory.getItems().get(event.getRawSlot());

        if(item == null) return;

        item.call(event.getClick(), (Player) event.getWhoClicked());
    }
}
