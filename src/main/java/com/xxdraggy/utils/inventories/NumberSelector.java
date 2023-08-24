package com.xxdraggy.utils.inventories;

import com.google.common.base.Preconditions;
import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.Heads;
import com.xxdraggy.utils.builders.inventory.InventoryBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class NumberSelector {
    private int count = 5;

    private Function<Player, Object> backCallback = player -> null;
    private BiFunction<Player, String, Object> callback = (player1, s) -> null;

    private String title = "Choose your number";

    private Map<Integer, Integer> numbers = new HashMap<>();
    private String maximum = "9999999";
    private int[] defaults = new int[] {
            0,
            0,
            0,
            0,
            0,
            0,
            0
    };

    public NumberSelector setNumberCount(int count) {
        Preconditions.checkArgument(count > 0 && count <= 7, "Count must be between 0 and 7!");

        this.count = count;

        return this;
    }
    public NumberSelector setBackCallback(Function<Player, Object> backCallback) {
        this.backCallback = backCallback;

        return this;
    }
    public NumberSelector setCallback(BiFunction<Player, String, Object> callback) {
        this.callback = callback;

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
    public NumberSelector setDefaultNumber(Function<String, String> defaultNumber) {
        String number = defaultNumber.apply("");

        for (int i = 0; i < number.length(); i++) {
            defaults[i] = Integer.parseInt(number.split("")[i]);
        }

        return this;
    }

    private InventoryBuilder builder = new InventoryBuilder()
            .setRows(5)
            .setBorder((borderBuilder) -> borderBuilder
                    .setBackButton(backButton -> backButton
                            .setCallback(player -> {
                                backCallback.apply(player);

                                return null;
                            })
                    )
                    .setProceedButton(proceedButton -> proceedButton
                            .setCallback(player -> {
                                StringBuilder number = new StringBuilder();

                                for (int i = 0; i < count; i++)
                                    number.append(numbers.get(i));


                                if(Integer.parseInt(number.toString()) > Integer.parseInt(maximum)) {
                                    player.sendMessage(Creator.text("Number too large!! " + maximum + " is the maximum", ChatColor.RED));

                                    for (int j = 0; j < count; j++)
                                        set(player, j, maximum.split("")[j] + "", 19 + j);
                                }
                                else
                                    callback.apply(player, number.toString());

                                return null;
                            })
                    )
            );

    public InventoryBuilder create() {
        builder.setTitle(title);

        for (int i = 0; i < count; i++) {
            final int index = i;
            final int slot = 19 + index;

            numbers.put(index, defaults[index]);

            final Function<Player, Object> upCallback = player -> {
                int currentNumber = numbers.get(index);

                if(currentNumber == 9) {
                    set(player, index, "0", slot);
                }
                else {
                    set(player, index, "" + (currentNumber + 1), slot);
                }

                return null;
            };
            builder.setItem(inventoryItemBuilder -> inventoryItemBuilder
                    .setItem(
                            Creator.item(Heads.Letters.Quartz.Arrows.Up.getHead())
                                    .setName(Creator.text("Plus", ChatColor.GRAY))
                                    .build()
                    )
                    .setSlot(10 + index)
                    .setCallback(upCallback)
            );

            builder.setItem(inventoryItemBuilder -> inventoryItemBuilder
                    .setItem(Heads.Letters.Quartz.Numbers.of(numbers.get(index) + "").getHead())
                    .setSlot(19 + index)
            );

            Function<Player, Object> downCallback = player -> {
                int currentNumber = numbers.get(index);

                if(currentNumber == 0) {
                    set(player, index, "9", slot);
                }
                else {
                    set(player, index, "" + (currentNumber - 1), slot);
                }

                return null;
            };
            builder.setItem(inventoryItemBuilder -> inventoryItemBuilder
                    .setItem(
                            Creator.item(Heads.Letters.Quartz.Arrows.Down.getHead())
                                    .setName(Creator.text("Minus", ChatColor.GRAY))
                                    .build()
                    )
                    .setSlot(28 + index)
                    .setCallback(downCallback)
            );
        }

        return builder;
    }

    private Object set(Player player, int index, String value, int slot) {
        builder.setItem(itemBuilder -> itemBuilder
                .setItem(
                        Creator.item(Heads.Letters.Quartz.Numbers.of(value).getHead())
                                .setName(Creator.text(Heads.Letters.Quartz.Numbers.of(value).name(), ChatColor.GRAY))
                                .build()
                )
                .setSlot(slot)
        );

        numbers.put(index, Integer.valueOf(value));

        player.openInventory(builder.build(player));

        return null;
    }
}
