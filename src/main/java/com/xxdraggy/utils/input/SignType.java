package com.xxdraggy.utils.input;

import org.bukkit.Material;

public enum SignType {
    Oak("minecraft:oak_sign", Material.OAK_SIGN),
    Acacia("minecraft:acacia_sign", Material.ACACIA_SIGN),
    Bamboo("minecraft:bamboo_sign", Material.BAMBOO_SIGN),
    Birch("minecraft:birch_sign", Material.BIRCH_SIGN),
    Cherry("minecraft:cherry_sign", Material.CHERRY_SIGN),
    Crimson("minecraft:crimson_sign", Material.CRIMSON_SIGN),
    DarkOak("minecraft:dark_oak_sign", Material.DARK_OAK_SIGN),
    Jungle("minecraft:jungle_sign", Material.JUNGLE_SIGN),
    Mangrove("minecraft:mangrove_sign", Material.MANGROVE_SIGN),
    Spruce("minecraft:spruce_sign", Material.SPRUCE_SIGN),
    Warped("minecraft:warped_sign", Material.WARPED_SIGN);

    SignType(String blockId, Material block) {
        this.id = blockId;
        this.block = block;
    }

    Material block;
    String id;
}
