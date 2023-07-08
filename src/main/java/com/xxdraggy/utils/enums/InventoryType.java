package com.xxdraggy.utils.enums;

public enum InventoryType {
    Chest(org.bukkit.event.inventory.InventoryType.CHEST),
    Dispenser(org.bukkit.event.inventory.InventoryType.DISPENSER),
    Dropper(org.bukkit.event.inventory.InventoryType.DROPPER),
    Furnace(org.bukkit.event.inventory.InventoryType.FURNACE),
    Workbench(org.bukkit.event.inventory.InventoryType.WORKBENCH),
    Crafting(org.bukkit.event.inventory.InventoryType.CRAFTING),
    Enchanting(org.bukkit.event.inventory.InventoryType.ENCHANTING),
    Brewing(org.bukkit.event.inventory.InventoryType.BREWING),
    Player(org.bukkit.event.inventory.InventoryType.PLAYER),
    Creative(org.bukkit.event.inventory.InventoryType.CREATIVE),
    Merchant(org.bukkit.event.inventory.InventoryType.MERCHANT),
    EnderChest(org.bukkit.event.inventory.InventoryType.ENDER_CHEST),
    Anvil(org.bukkit.event.inventory.InventoryType.ANVIL),
    SmithingTable(org.bukkit.event.inventory.InventoryType.SMITHING),
    Beacon(org.bukkit.event.inventory.InventoryType.BEACON),
    Hopper(org.bukkit.event.inventory.InventoryType.HOPPER),
    ShulkerBox(org.bukkit.event.inventory.InventoryType.SHULKER_BOX),
    Barrel(org.bukkit.event.inventory.InventoryType.BARREL),
    BlastFurnace(org.bukkit.event.inventory.InventoryType.BARREL),
    Lectern(org.bukkit.event.inventory.InventoryType.LECTERN),
    Smoker(org.bukkit.event.inventory.InventoryType.SMOKER),
    Loom(org.bukkit.event.inventory.InventoryType.LOOM),
    CartographyTable(org.bukkit.event.inventory.InventoryType.CARTOGRAPHY),
    Gindstone(org.bukkit.event.inventory.InventoryType.GRINDSTONE),
    Stonecutter(org.bukkit.event.inventory.InventoryType.STONECUTTER),
    Composter(org.bukkit.event.inventory.InventoryType.COMPOSTER),
    ChiseledBookshelf(org.bukkit.event.inventory.InventoryType.CHISELED_BOOKSHELF),
    Jukebox(org.bukkit.event.inventory.InventoryType.JUKEBOX),
    SmithingTableNew(org.bukkit.event.inventory.InventoryType.SMITHING_NEW),
    Container();

    InventoryType(org.bukkit.event.inventory.InventoryType type) {
        this.type = type;
    }

    InventoryType() {}

    public org.bukkit.event.inventory.InventoryType type;
}
