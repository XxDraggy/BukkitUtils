package com.xxdraggy.utils.inventories;

import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.Heads;
import com.xxdraggy.utils.builders.InventoryBuilder;
import com.xxdraggy.utils.data.InventoryType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

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
    private final Map<Integer, Boolean> set = new HashMap<>();

    public NumberSelector setNumberCount(int count) {
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

    private InventoryBuilder builder = new InventoryBuilder()
            .setTitle(title)
            .setType(InventoryType.Container)
            .setRows(5)
            .setBorder((borderBuilder) -> borderBuilder
                    .setBackButton(backButtonBuilder -> backButtonBuilder
                            .setCallBack(back)
                            .build()
                    )
                    .setProceedButton(proceedButtonBuilder -> proceedButtonBuilder
                            .setCallBack(player -> {
                                String nums = "";

                                for (int i = 0; i < count; i++) {
                                    nums += numbers.get(i);
                                }

                                proceed.apply(player, nums);

                                return null;
                            })
                            .build()
                    )
                    .build()
            );

    public InventoryBuilder create() {
        for (int i = 0; i < count; i++) {
            final int count = i;
            final int numberSlot = 26 - count;

            numbers.put(i, 0);
            set.put(i, false);

            final Function<Player, Void> upCallback = player -> {
                int currentNumber = numbers.get(count);

                if(currentNumber == 9) {
                    builder.setItem(Heads.Letters.Quartz.Numbers.Zero.getHead(), numberSlot);
                    numbers.put(count, 0);
                    player.openInventory(builder.build(player));
                }
                else {
                    builder.setItem(Heads.Letters.Quartz.Numbers.of("" + count).getHead(), numberSlot);
                    numbers.put(count, currentNumber + 1);
                    player.openInventory(builder.build(player));
                }

                return null;
            };
            builder.setItem(Heads.Letters.Quartz.Arrows.Up.getHead(), 17 - count, upCallback);

            Function<Player, Void> downCallback = player -> {
                int currentNumber = numbers.get(count);

                if(currentNumber == 0) {
                    builder.setItem(Heads.Letters.Quartz.Numbers.Nine.getHead(), numberSlot);
                    numbers.put(count, 9);
                    player.openInventory(builder.build(player));
                }
                else {
                    builder.setItem(Heads.Letters.Quartz.Numbers.of("" + count).getHead(), numberSlot);
                    numbers.put(count, currentNumber - 1);
                    player.openInventory(builder.build(player));
                }

                return null;
            };
            builder.setItem(Heads.Letters.Quartz.Arrows.Down.getHead(), 35 - count, downCallback);

            builder.setItem(Heads.Letters.Quartz.Numbers.Zero.getHead(), 26 - count, player -> {
                final boolean isSet = set.get(count);

                if(isSet) {
                    builder.setItem(Creator.item(Material.AIR), 17 - count);
                    builder.setItem(Creator.item(Material.AIR), 35 - count);
                }
                else {
                    builder.setItem(Heads.Letters.Quartz.Arrows.Up.getHead(), 17 - count, upCallback);
                    builder.setItem(Heads.Letters.Quartz.Arrows.Down.getHead(), 26 - count, downCallback);
                }

                return null;
            });
        }

        return builder;
    }
}
