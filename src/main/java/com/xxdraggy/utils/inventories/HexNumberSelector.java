package com.xxdraggy.utils.inventories;

import com.google.common.base.Preconditions;
import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.Heads;
import com.xxdraggy.utils.builders.inventory.InventoryBuilder;
import com.xxdraggy.utils.data.inventory.InventoryType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class HexNumberSelector {
    private int count = 5;

    private Function<Player, Object> backCallback = player -> null;

    private BiFunction<Player, String, Object> callback = (player, nums) -> null;

    private String title = "Choose your number";

    private Map<Integer, String> numbers = new HashMap<>();
    private String[] defaults = new String[] {
            "0",
            "0",
            "0",
            "0",
            "0",
            "0",
            "0"
    };

    public HexNumberSelector setNumberCount(int count) {
        Preconditions.checkArgument(count > 0 && count <= 7, "Count must be between 0 and 7!");

        this.count = count;

        return this;
    }
    public HexNumberSelector setBackCallback(Function<Player, Object> backCallback) {
        this.backCallback = backCallback;

        return this;
    }
    public HexNumberSelector setCallback(BiFunction<Player, String, Object> callback) {
        this.callback = callback;

        return this;
    }
    public HexNumberSelector setTitle(String title) {
        this.title = title;

        return this;
    }
    public HexNumberSelector setDefaultNumber(Function<Object, String> defaultNumber) {
        this.defaults = defaultNumber.apply("").split("");

        return this;
    }

    private InventoryBuilder builder = new InventoryBuilder()
            .setType(InventoryType.Container)
            .setRows(5)
            .setBorder((borderBuilder) -> borderBuilder
                    .setBackButton(backButton -> backButton
                            .setCallback(player -> backCallback.apply(player))
                    )
                    .setProceedButton(proceedButton -> proceedButton
                            .setCallback(player -> {
                                StringBuilder number = new StringBuilder();

                                for (int i = 0; i < count; i++)
                                    number.append(numbers.get(i));

                                callback.apply(player, number.toString());

                                return null;
                            })
                    )
            );

    public InventoryBuilder create() {
        builder.setTitle(title);

        for (int i = 0; i < count; i++) {
            final int count = i;
            final int slot = 19 + count;

            numbers.put(i, defaults[i]);

            final Function<Player, Object> upCallback = player -> {
                String nextNumber = add(numbers.get(count));

                set(player, count, nextNumber, slot);

                return null;
            };
            builder.setItem(itemBuilder -> itemBuilder
                    .setItem(
                            Creator.item(Heads.Letters.Quartz.Arrows.Up.getHead())
                                    .setName(Creator.text("Plus", ChatColor.GRAY))
                                    .build()
                    )
                    .setSlot(10 + count)
                    .setCallback(upCallback)
            );

            builder.setItem(inventoryItemBuilder -> inventoryItemBuilder
                    .setItem(getHead(numbers.get(count)))
                    .setSlot(slot)
            );

            final Function<Player, Object> downCallback = player -> {
                String nextNumber = subtract(numbers.get(count));

                set(player, count, nextNumber, slot);

                return null;
            };
            builder.setItem(itemBuilder -> itemBuilder
                    .setItem(
                            Creator.item(Heads.Letters.Quartz.Arrows.Down.getHead())
                                    .setName(Creator.text("Minus", ChatColor.GRAY))
                                    .build()
                    )
                    .setSlot(28 + count)
                    .setCallback(downCallback)
            );
        }

        return builder;
    }

    private String add(String num) {
        try {
            Integer.parseInt(num);

            switch (num) {
                case "0":
                    return "1";
                case "1":
                    return "2";
                case "2":
                    return "3";
                case "3":
                    return "4";
                case "4":
                    return "5";
                case "5":
                    return "6";
                case "6":
                    return "7";
                case "7":
                    return "8";
                case "8":
                    return "9";
                case "9":
                    return "A";
                default:
                    return "0";
            }
        }
        catch (NumberFormatException error) {
            switch (num) {
                case "A":
                    return "B";
                case "B":
                    return "C";
                case "C":
                    return "D";
                case "D":
                    return "E";
                case "E":
                    return "F";
                case "F":
                    return "0";
                default:
                    return "A";
            }
        }
    }
    private String subtract(String num) {
        try {
            Integer.parseInt(num);

            switch (num) {
                case "1":
                    return "0";
                case "2":
                    return "1";
                case "3":
                    return "2";
                case "4":
                    return "3";
                case "5":
                    return "4";
                case "6":
                    return "5";
                case "7":
                    return "6";
                case "8":
                    return "7";
                case "9":
                    return "8";
                default:
                    return "F";
            }
        }
        catch (NumberFormatException error) {
            switch (num) {
                case "B":
                    return "A";
                case "C":
                    return "B";
                case "D":
                    return "C";
                case "E":
                    return "D";
                case "F":
                    return "E";
                default:
                    return "9";
            }
        }
    }
    private ItemStack getHead(String num) {
        try {
            Integer.parseInt(num);

            return Creator.item(Heads.Letters.Quartz.Numbers.of("" + num).getHead())
                    .setName(Creator.text(Heads.Letters.Quartz.Numbers.of("" + num).name(), ChatColor.GRAY))
                    .build();
        }
        catch (NumberFormatException error) {
            return Creator.item(Heads.Letters.Quartz.Letter.of("" + num).getHead())
                    .setName(Creator.text(Heads.Letters.Quartz.Letter.of("" + num).name(), ChatColor.GRAY))
                    .build();
        }
    }
    private void set(Player player, int index, String value, int slot) {
        builder.setItem(inventoryItemBuilder -> inventoryItemBuilder
                .setItem(getHead(value))
                .setSlot(slot)
        );

        numbers.put(index, value);

        player.openInventory(builder.build(player));
    }
}
