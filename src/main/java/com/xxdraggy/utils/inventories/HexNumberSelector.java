package com.xxdraggy.utils.inventories;

import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.Heads;
import com.xxdraggy.utils.builders.InventoryBuilder;
import com.xxdraggy.utils.data.InventoryType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
    private final Map<Integer, Boolean> set = new HashMap<>();

    public HexNumberSelector setNumberCount(int count) {
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

            numbers.put(i, "0");
            set.put(i, false);

            final Function<Player, Void> upCallback = player -> {
                String nextNumber = add(numbers.get(count));

                numbers.put(count, nextNumber);

                if(nextNumber == "0") {
                    builder.setItem(Heads.Letters.Quartz.Numbers.Zero.getHead(), numberSlot);
                    player.openInventory(builder.build(player));
                }
                else {
                    builder.setItem(getHead(nextNumber), numberSlot);
                    player.openInventory(builder.build(player));
                }

                return null;
            };
            builder.setItem(Heads.Letters.Quartz.Arrows.Up.getHead(), 17 - count, upCallback);

            final Function<Player, Void> downCallback = player -> {
                String nextNumber = subtract(numbers.get(count));

                numbers.put(count, nextNumber);

                if(nextNumber == "0") {
                    builder.setItem(Heads.Letters.Quartz.Letter.F.getHead(), numberSlot);
                    player.openInventory(builder.build(player));
                }
                else {
                    builder.setItem(getHead(nextNumber), numberSlot);
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
                case "0":
                    return "F";
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
                case "A":
                    return "9";
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
}
