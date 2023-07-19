package com.xxdraggy.utils.inventories;

import com.google.common.base.Preconditions;
import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.Heads;
import com.xxdraggy.utils.builders.InventoryBuilder;
import com.xxdraggy.utils.data.InventoryType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class NumberSelector {
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

    private Map<Integer, Integer> numbers = new HashMap<>();
    private String maximum = "9999999";
    private String[] defaults = (String[]) Arrays.asList("0", "0", "0", "0", "0", "0", "0").toArray();
    private Player player;

    public NumberSelector setNumberCount(int count) {
        Preconditions.checkArgument(count > 0 && count <= 7, "Count must be between 0 and 7!");

        this.count = count;

        return this;
    }

    public NumberSelector setBackCallback(Function<Player, Void> back) {
        this.back = back;

        return this;
    }

    public NumberSelector setProceedCallback(BiFunction<Player, String, Void> proceed) {
        this.proceed = proceed;

        return this;
    }

    public NumberSelector setTitle(String title) {
        this.title = title;

        return this;
    }

    public NumberSelector setMaximum(String maximum) {
        this.maximum = maximum;

        return this;
    }

    public NumberSelector setDefaultNumber(String defaultNumber) {
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

                                for (int i = 0; i < count; i++) {
                                    number.append(numbers.get(i));
                                }

                                if(Integer.parseInt(number.toString()) > Integer.parseInt(maximum)) {
                                    player.sendMessage(Creator.text("Number too large!! " + maximum + " is the maximum", ChatColor.RED));

                                    for (int i = 0; i < count; i++)
                                        set(player, i, maximum.split("")[i], 26 - count);
                                }
                                else
                                    proceed.apply(player, number.toString());

                                return null;
                            })
                    )
            );

    public InventoryBuilder create() {
        for (int i = 0; i < count; i++) {
            final int count = i;
            final int slot = 26 - count;

            numbers.put(i, 0);

            final Function<Player, Void> upCallback = player -> {
                int currentNumber = numbers.get(count);

                if(currentNumber == 9) {
                    set(player, count, "0", slot);
                }
                else {
                    set(player, count, "" + (currentNumber + 1), slot);
                }

                return null;
            };
            builder.setItem(inventoryItemBuilder -> inventoryItemBuilder
                    .setItem(Heads.Letters.Quartz.Arrows.Up.getHead())
                    .setSlot(17 - count)
                    .setCallback(upCallback)
            );

            Function<Player, Void> downCallback = player -> {
                int currentNumber = numbers.get(count);

                if(currentNumber == 0) {
                    set(player, count, "9", slot);
                }
                else {
                    set(player, count, "" + (currentNumber - 1), slot);
                }

                return null;
            };
            builder.setItem(inventoryItemBuilder -> inventoryItemBuilder
                    .setItem(Heads.Letters.Quartz.Arrows.Down.getHead())
                    .setSlot(35 - count)
                    .setCallback(downCallback)
            );
        }

        return builder;
    }

    private Void set(Player player, int index, String value, int slot) {
        builder.setItem(itemBuilder -> itemBuilder
                .setItem(Heads.Letters.Quartz.Numbers.of(value).getHead())
                .setSlot(slot)
        );

        numbers.put(index, Integer.valueOf(value));

        player.openInventory(builder.build(player));

        return null;
    }
}
