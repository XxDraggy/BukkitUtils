package com.xxdraggy.utils;

import com.xxdraggy.utils.builders.CommandBuilder;
import com.xxdraggy.utils.builders.InputBuilder;
import com.xxdraggy.utils.builders.ItemBuilder;
import com.xxdraggy.utils.builders.inventory.InventoryBuilder;
import com.xxdraggy.utils.builders.inventory.PagedInventoryBuilder;
import com.xxdraggy.utils.builders.text.TextBuilder;
import com.xxdraggy.utils.data.color.BannerBaseColor;
import com.xxdraggy.utils.data.color.ColorObject;
import com.xxdraggy.utils.gradient.GradientCreator;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
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
        return new ItemStack(type);
    }
    public static @NotNull ItemStack item(Material type, int amount) {
        return new ItemStack(type, amount);
    }
    public static @NotNull ItemStack item(Material type, String name) {
        return Creator.item(Creator.item(type))
                .setName(name)
                .build();
    }
    public static @NotNull ItemStack item(Material type, String name, int amount) {
        return Creator.item(Creator.item(type, amount))
                .setName(name)
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

    public static @NotNull TextComponent gradient(String text, TextComponent component, String ...colors) {
        return GradientCreator.generateGradient(text, component, colors);
    }
    public static @NotNull TextComponent gradient(String text, String ...colors) {
        return GradientCreator.generateGradient(text, colors);
    }
    public static @NotNull TextComponent gradient(String text, TextComponent component, ColorObject...colors) {
        String[] hexColors = new String[colors.length];

        for (int i = 0; i < colors.length; i++) {
            hexColors[i] = colors[i].getHex();
        }

        return GradientCreator.generateGradient(text, component, hexColors);
    }
    public static @NotNull TextComponent gradient(String text, ColorObject ...colors) {
        String[] hexColors = new String[colors.length];

        for (int i = 0; i < colors.length; i++) {
            hexColors[i] = colors[i].getHex();
        }

        return GradientCreator.generateGradient(text, hexColors);
    }

    public static @NotNull TextComponent rainbow(String text, TextComponent component) {
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

        int colorIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            TextComponent newComponent = new TextComponent();

            if (colorIndex >= rainbowColors.toArray().length)
                colorIndex = 0;

            newComponent.setText(text.charAt(i) + "");
            newComponent.setColor(rainbowColors.get(colorIndex));

            component.addExtra(newComponent);

            colorIndex++;
        }

        return component;
    }
    public static @NotNull TextComponent rainbow(String text) {
        ArrayList<ChatColor> rainbowColors = new ArrayList<>();
        TextComponent component = new TextComponent();
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

        int colorIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            TextComponent newComponent = new TextComponent();

            if (colorIndex >= rainbowColors.toArray().length)
                colorIndex = 0;

            newComponent.setText(text.charAt(i) + "");
            newComponent.setColor(rainbowColors.get(colorIndex));

            component.addExtra(newComponent);

            colorIndex++;
        }

        return component;
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
        return color + text;
    }
    public static @NotNull String text(String text, org.bukkit.ChatColor color) {
        return color + text;
    }
    public static @NotNull String text(String text, String hexColor) {
        return new TextBuilder(text)
                .hex(hexColor)
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

    public static @NotNull InputBuilder input() {
        return new InputBuilder();
    }
    public static @NotNull InputBuilder input(Function<InputBuilder, InputBuilder> builder) {
        return builder.apply(new InputBuilder());
    }

    public static @NotNull CommandBuilder command() {
        return new CommandBuilder();
    }
    public static @NotNull CommandBuilder command(Function<CommandBuilder, CommandBuilder> builder) {
        return builder.apply(new CommandBuilder());
    }
    public static @NotNull Void command(String command, BiFunction<CommandSender, String[], Boolean> executor) {
        Creator.command().setCommand(command).setExecutor(executor).register();

        return null;
    }
}

