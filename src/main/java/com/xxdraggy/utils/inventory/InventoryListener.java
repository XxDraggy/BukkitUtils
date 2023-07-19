package com.xxdraggy.utils.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryListener implements Listener {
    public void InventoryClickEvent(InventoryClickEvent event) {
        if(event.getSlotType() == InventoryType.SlotType.OUTSIDE) return;

        InventoryController.inventories.forEach(inventoryData -> {

            if(event.getClickedInventory() == inventoryData.inventory) {
                event.setCancelled(true);

                inventoryData.items.get(event.getRawSlot()).callback.apply((Player) event.getWhoClicked());
            }
        });
    }
}
