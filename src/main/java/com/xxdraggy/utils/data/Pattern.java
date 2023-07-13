package com.xxdraggy.utils.data;

import org.bukkit.DyeColor;
import org.bukkit.block.banner.PatternType;

public enum Pattern {
    SQUARE_BOTTOM_LEFT(PatternType.SQUARE_BOTTOM_LEFT),
    SQUARE_BOTTOM_RIGHT(PatternType.SQUARE_BOTTOM_RIGHT),
    SQUARE_TOP_LEFT(PatternType.SQUARE_TOP_LEFT),
    SQUARE_TOP_RIGHT(PatternType.SQUARE_TOP_RIGHT),
    STRIPE_BOTTOM(PatternType.STRIPE_BOTTOM),
    STRIPE_TOP(PatternType.STRIPE_TOP),
    STRIPE_LEFT(PatternType.STRIPE_LEFT),
    STRIPE_RIGHT(PatternType.STRIPE_RIGHT),
    STRIPE_CENTER(PatternType.STRIPE_CENTER),
    STRIPE_MIDDLE(PatternType.STRIPE_MIDDLE),
    STRIPE_DOWNRIGHT(PatternType.STRIPE_DOWNRIGHT),
    STRIPE_DOWNLEFT(PatternType.STRIPE_DOWNLEFT),
    STRIPE_SMALL(PatternType.STRIPE_SMALL),
    CROSS(PatternType.CROSS),
    STRAIGHT_CROSS(PatternType.STRAIGHT_CROSS),
    TRIANGLE_BOTTOM(PatternType.TRIANGLE_BOTTOM),
    TRIANGLE_TOP(PatternType.TRIANGLE_TOP),
    TRIANGLES_BOTTOM(PatternType.TRIANGLE_BOTTOM),
    TRIANGLES_TOP(PatternType.TRIANGLES_TOP),
    DIAGONAL_LEFT(PatternType.DIAGONAL_LEFT),
    DIAGONAL_RIGHT(PatternType.DIAGONAL_RIGHT),
    DIAGONAL_LEFT_MIRROR(PatternType.DIAGONAL_LEFT_MIRROR),
    DIAGONAL_RIGHT_MIRROR(PatternType.DIAGONAL_RIGHT_MIRROR),
    CIRCLE_MIDDLE(PatternType.CIRCLE_MIDDLE),
    RHOMBUS_MIDDLE(PatternType.RHOMBUS_MIDDLE),
    HALF_VERTICAL(PatternType.HALF_VERTICAL),
    HALF_HORIZONTAL(PatternType.HALF_HORIZONTAL),
    HALF_VERTICAL_MIRROR(PatternType.HALF_VERTICAL_MIRROR),
    HALF_HORIZONTAL_MIRROR(PatternType.HALF_HORIZONTAL_MIRROR),
    BORDER(PatternType.BORDER),
    CURLY_BORDER(PatternType.CURLY_BORDER),
    CREEPER(PatternType.CREEPER),
    GRADIENT_DOWN(PatternType.GRADIENT),
    GRADIENT_UP(PatternType.GRADIENT_UP),
    BRICKS(PatternType.BRICKS),
    SKULL(PatternType.SKULL),
    FLOWER(PatternType.FLOWER),
    MOJANG(PatternType.MOJANG),
    GLOBE(PatternType.GLOBE),
    PIGLIN(PatternType.PIGLIN);

    Pattern(PatternType type) {
        this.WHITE = new org.bukkit.block.banner.Pattern(DyeColor.WHITE, type);
        this.ORANGE = new org.bukkit.block.banner.Pattern(DyeColor.ORANGE, type);
        this.MAGENTA = new org.bukkit.block.banner.Pattern(DyeColor.MAGENTA, type);
        this.LIGHT_BLUE = new org.bukkit.block.banner.Pattern(DyeColor.LIGHT_BLUE, type);
        this.YELLOW = new org.bukkit.block.banner.Pattern(DyeColor.YELLOW, type);
        this.LIME = new org.bukkit.block.banner.Pattern(DyeColor.LIME, type);
        this.PINK = new org.bukkit.block.banner.Pattern(DyeColor.PINK, type);
        this.GRAY = new org.bukkit.block.banner.Pattern(DyeColor.GRAY, type);
        this.LIGHT_GRAY = new org.bukkit.block.banner.Pattern(DyeColor.LIGHT_GRAY, type);
        this.CYAN = new org.bukkit.block.banner.Pattern(DyeColor.CYAN, type);
        this.PURPLE = new org.bukkit.block.banner.Pattern(DyeColor.PURPLE, type);
        this.BLUE = new org.bukkit.block.banner.Pattern(DyeColor.BLUE, type);
        this.BROWN = new org.bukkit.block.banner.Pattern(DyeColor.BROWN, type);
        this.GREEN = new org.bukkit.block.banner.Pattern(DyeColor.GREEN, type);
        this.RED = new org.bukkit.block.banner.Pattern(DyeColor.RED, type);
        this.BLACK = new org.bukkit.block.banner.Pattern(DyeColor.BLACK, type);
    }

    public final org.bukkit.block.banner.Pattern WHITE,
            ORANGE,
            MAGENTA,
            LIGHT_BLUE,
            YELLOW,
            LIME,
            PINK,
            GRAY,
            LIGHT_GRAY,
            CYAN,
            PURPLE,
            BLUE,
            BROWN,
            GREEN,
            RED,
            BLACK;
}
