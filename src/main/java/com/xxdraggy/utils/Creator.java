package com.xxdraggy.utils;

import com.xxdraggy.utils.creator.InventoryBuilder;
import com.xxdraggy.utils.creator.ItemBuilder;
import com.xxdraggy.utils.creator.text.TextCreator;
import com.xxdraggy.utils.enums.BannerBaseColor;
import com.xxdraggy.utils.gradient.GradientCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class Creator {
    public static @NotNull ItemBuilder item() {
        return new ItemBuilder();
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
    public static @NotNull ItemStack item(Material type, String name) {
        ItemStack item = Creator.item(type, 1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        item.setItemMeta(meta);

        return item;
    }
    public static @NotNull ItemStack item(Material type, String name, int amount) {
        ItemStack item = Creator.item(type, amount);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        item.setItemMeta(meta);

        return item;
    }

    public static @NotNull InventoryBuilder inventory() {
        return new InventoryBuilder();
    }
    public static @NotNull Inventory inventory(Function<InventoryBuilder, Inventory> creator) {
        return creator.apply(new InventoryBuilder());
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

    public static @NotNull TextCreator text(String text) {
        return new TextCreator(text);
    }
    public static @NotNull TextCreator text() {
        return new TextCreator();
    }
    public static @NotNull String text(Function<TextCreator, TextCreator> creator) {
        return creator.apply(new TextCreator()).toString();
    }
    public static @NotNull String text(String text, ChatColor color) {
        return new TextCreator(text)
                .color(color)
                .toString();
    }
    public static @NotNull String text(String text, String hexColor) {
        return new TextCreator(text)
                .hex(hexColor)
                .toString();
    }
    public static @NotNull String text(String text, int red, int green, int blue) {
        return new TextCreator(text)
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
