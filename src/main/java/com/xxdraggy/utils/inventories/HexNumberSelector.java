package com.xxdraggy.utils.inventories;

import com.google.common.base.Preconditions;
import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.Heads;
import com.xxdraggy.utils.builders.InventoryBuilder;
import com.xxdraggy.utils.data.InventoryType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class HexNumberSelector {
    private int count = 5;

    private Function<Player, Void> back = (player -> {
        player.closeInventory();
        return null;
    });

    private BiFunction<Player, String, Void> proceed = (player, nums) -> {
        player.closeInventory();
        return null;
    };

    private String title = Creator.text("Choose your number")
            .gold()
            .bold()
            .underline()
            .toString();

    private Map<Integer, String> numbers = new HashMap<>();
    private String[] defaults = (String[]) Arrays.asList("0", "0", "0", "0", "0", "0", "0").toArray();

    public HexNumberSelector setNumberCount(int count) {
        Preconditions.checkArgument(count > 0 && count <= 7, "Count must be between 0 and 7!");

        this.count = count;

        return this;
    }

    public HexNumberSelector setBackCallback(Function<Player, Void> back) {
        this.back = back;

        return this;
    }

    public HexNumberSelector setProceedCallback(BiFunction<Player, String, Void> proceed) {
        this.proceed = proceed;

        return this;
    }

    public HexNumberSelector setTitle(String title) {
        this.title = title;

        return this;
    }

    public HexNumberSelector setDefaultNumber(String defaultNumber) {
        this.defaults = defaultNumber.split("");

        return this;
    }

    private InventoryBuilder builder = new InventoryBuilder()
            .setTitle(title)
            .setType(InventoryType.Container)
            .setRows(5)
            .setBorder((borderBuilder) -> borderBuilder
                    .setBackButton(backButtonBuilder -> backButtonBuilder
                            .setCallBack(back)
                    )
                    .setProceedButton(proceedButtonBuilder -> proceedButtonBuilder
                            .setCallBack(player -> {
                                StringBuilder number = new StringBuilder();

                                for (int i = 0; i < count; i++)
                                    number.append(numbers.get(number.length() - 1 - i));

                                proceed.apply(player, number.toString());

                                return null;
                            })
                    )
            );

    public InventoryBuilder create() {
        for (int i = 0; i < count; i++) {
            final int count = i;
            final int slot = 26 - count;

            numbers.put(i, "0");

            final Function<Player, Void> upCallback = player -> {
                String nextNumber = add(numbers.get(count));

                numbers.put(count, nextNumber);

                set(player, count, nextNumber, slot);

                return null;
            };
            builder.setItem(itemBuilder -> itemBuilder
                    .setItem(Heads.Letters.Quartz.Arrows.Up.getHead())
                    .setSlot(35 - count)
                    .setCallback(upCallback)
            );

            final Function<Player, Void> downCallback = player -> {
                String nextNumber = subtract(numbers.get(count));

                numbers.put(count, nextNumber);

                set(player, count, nextNumber, slot);

                return null;
            };
            builder.setItem(itemBuilder -> itemBuilder
                    .setItem(Heads.Letters.Quartz.Arrows.Down.getHead())
                    .setSlot(35 - count)
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

            return Heads.Letters.Quartz.Numbers.of("" + num).getHead();
        }
        catch (NumberFormatException error) {
            return Heads.Letters.Quartz.Letter.of("" + num).getHead();
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
