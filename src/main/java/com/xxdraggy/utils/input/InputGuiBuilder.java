package com.xxdraggy.utils.input;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public class InputGuiBuilder {
    private InputGui gui = new InputGui();

    public InputGuiBuilder setLines(String ...lines) {
        gui.lines = Arrays.asList(lines);

        return this;
    }

    public InputGuiBuilder setType(SignType type) {
        gui.signType = type;

        return this;
    }

    public InputGuiBuilder setCallback(BiPredicate<Player, String[]> callBack) {
        gui.callBack = callBack;

        return this;
    }

    public InputGuiBuilder setReopen(boolean reopen) {
        gui.reopen = reopen;

        return this;
    }

    public void open(Player player) {
        this.gui.open(player);
    }
}
