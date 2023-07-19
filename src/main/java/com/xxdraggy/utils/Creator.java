package com.xxdraggy.utils;

import com.xxdraggy.utils.builders.InventoryBuilder;
import com.xxdraggy.utils.builders.ItemBuilder;
import com.xxdraggy.utils.builders.PagedInventoryBuilder;
import com.xxdraggy.utils.builders.TextBuilder;
import com.xxdraggy.utils.data.color.BannerBaseColor;
import com.xxdraggy.utils.gradient.GradientCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class Creator {
    public static @NotNull ItemBuilder item() {
        return new ItemBuilder();
    }
    public static @NotNull ItemBuilder item(ItemStack item) {
        return new ItemBuilder(item);
    }
    public static @NotNull ItemStack item(Function<ItemBuilder, ItemBuilder> creator) {
        return creator.apply(new ItemBuilder()).build();
    }
    public static @NotNull ItemStack item(Material type) {
        return Creator.item(type, 1);
    }
    public static @NotNull ItemStack item(Material type, int amount) {
        return new ItemStack(type, amount);
    }
    public static @NotNull ItemStack item(Material material, String name) {
        return Creator.item()
                .setMaterial(material)
                .setName(name)
                .build();
    }
    public static @NotNull ItemStack item(Material material, String name, int amount) {
        return Creator.item()
                .setMaterial(material)
                .setName(name)
                .setAmount(amount)
                .build();
    }

    public static @NotNull InventoryBuilder inventory() {
        return new InventoryBuilder();
    }
    public static @NotNull Inventory inventory(Function<InventoryBuilder, Inventory> builder) {
        return builder.apply(new InventoryBuilder());
    }
    public static @NotNull Inventory inventory(Player holder, InventoryType type) {
        return Bukkit.createInventory(holder, type);
    }
    public static @NotNull Inventory inventory(Player holder, String name, InventoryType type) {
        return Bukkit.createInventory(holder, type, name);
    }
    public static @NotNull Inventory inventory(Player holder, int rows) {
        return Bukkit.createInventory(holder, rows * 9);
    }
    public static @NotNull Inventory inventory(Player holder, String name, int rows) {
        return Bukkit.createInventory(holder, rows*9, name);
    }

    public static @NotNull PagedInventoryBuilder pagedInventory() {
        return new PagedInventoryBuilder();
    }
    public static @NotNull Inventory pagedInventory(Function<PagedInventoryBuilder, Inventory> builder) {
        return builder.apply(new PagedInventoryBuilder());
    }
    public static @NotNull PagedInventoryBuilder pagedInventory(InventoryBuilder... builders) {
        return new PagedInventoryBuilder();
    }

    public static @NotNull String gradient(String text, String ...colors) {
        return GradientCreator.generateGradient(text, colors);
    }

    public static @NotNull String rainbow(String text) {
        ArrayList<ChatColor> rainbowColors = new ArrayList<>();
        rainbowColors.add(ChatColor.DARK_RED);
        rainbowColors.add(ChatColor.RED);
        rainbowColors.add(ChatColor.GOLD);
        rainbowColors.add(ChatColor.YELLOW);
        rainbowColors.add(ChatColor.GREEN);
        rainbowColors.add(ChatColor.DARK_GREEN);
        rainbowColors.add(ChatColor.DARK_AQUA);
        rainbowColors.add(ChatColor.AQUA);
        rainbowColors.add(ChatColor.DARK_BLUE);
        rainbowColors.add(ChatColor.DARK_PURPLE);
        rainbowColors.add(ChatColor.LIGHT_PURPLE);

        StringBuilder finalText = new StringBuilder();

        int colorIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);

            if (colorIndex >= rainbowColors.toArray().length) {
                colorIndex = 0;
            }

            String color = rainbowColors.get(colorIndex).toString();

            finalText.append(color).append(letter);

            colorIndex++;
        }

        return finalText.toString();
    }

    public static @NotNull TextBuilder text(String text) {
        return new TextBuilder(text);
    }
    public static @NotNull TextBuilder text() {
        return new TextBuilder();
    }
    public static @NotNull String text(Function<TextBuilder, TextBuilder> creator) {
        return creator.apply(new TextBuilder()).toString();
    }
    public static @NotNull String text(String text, ChatColor color) {
        return new TextBuilder(text)
                .color(color)
                .toString();
    }
    public static @NotNull String text(String text, String hexColor) {
        return new TextBuilder(text)
                .hex(hexColor)
                .toString();
    }
    public static @NotNull String text(String text, Color color) {
        return new TextBuilder(text)
                .color(color)
                .toString();
    }
    public static @NotNull String text(String text, int red, int green, int blue) {
        return new TextBuilder(text)
                .rgb(red, green, blue)
                .toString();
    }


    public static @NotNull ItemStack banner(BannerBaseColor baseColor) {
        return Creator.banner(baseColor, (Pattern) null);
    }
    public static @NotNull ItemStack banner(@NotNull BannerBaseColor baseColor, Pattern...patterns) {
        ItemStack banner;

        switch (baseColor) {
            case BLACK:
                banner = Creator.item(Material.RED_BANNER);
            case BLUE:
                banner = Creator.item(Material.BLUE_BANNER);
            case BROWN:
                banner = Creator.item(Material.BROWN_BANNER);
            case CYAN:
                banner = Creator.item(Material.CYAN_BANNER);
            case GRAY:
                banner = Creator.item(Material.GRAY_BANNER);
            case GREEN:
                banner = Creator.item(Material.GREEN_BANNER);
            case LIME:
                banner = Creator.item(Material.LIME_BANNER);
            case MAGENTA:
                banner = Creator.item(Material.MAGENTA_BANNER);
            case ORANGE:
                banner = Creator.item(Material.ORANGE_BANNER);
            case PINK:
                banner = Creator.item(Material.PINK_BANNER);
            case PURPLE:
                banner = Creator.item(Material.PURPLE_BANNER);
            case RED:
                banner = Creator.item(Material.RED_BANNER);
            case WHITE:
                banner = Creator.item(Material.WHITE_BANNER);
            case YELLOW:
                banner = Creator.item(Material.YELLOW_BANNER);
            default:
                banner = Creator.item(Material.WHITE_BANNER);
        }

        BannerMeta bannerMeta = (BannerMeta) banner.getItemMeta();

        bannerMeta.setPatterns(Arrays.asList(patterns));

        banner.setItemMeta(bannerMeta);

        return banner;
    }
}

