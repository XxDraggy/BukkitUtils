package com.xxdraggy.utils.builders;

import com.xxdraggy.utils.data.SignType;
import com.xxdraggy.utils.input.InputGui;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public class InputBuilder {
    private InputGui gui = new InputGui();

    public InputBuilder setLines(String... lines) {
        gui.lines = Arrays.asList(lines);

        return this;
    }

     public InputBuilder setType(SignType type) {
        gui.signType = type;

        return this;
    }

    public InputBuilder setCallback(BiPredicate<Player, String[]> callBack) {
        gui.callBack = callBack;

        return this;
    }

    public InputBuilder reopenOnFail() {
        gui.reopen = true;

        return this;
    }
    public InputBuilder setReopenOnFail(boolean reopen) {
        gui.reopen = reopen;

        return this;
    }

    public Object open(Player player) {
        this.gui.open(player);

        return null;
    }
}