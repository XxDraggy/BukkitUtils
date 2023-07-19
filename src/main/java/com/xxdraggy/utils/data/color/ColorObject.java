package com.xxdraggy.utils.data.color;

import org.bukkit.Color;

public class ColorObject {
    private final String color;
    public String toString() {
        return color;
    }

    private final Color object;
    public Color getObject() {
        return object;
    }

    public ColorObject(String color, Color object) {
        this.color = color;
        this.object = object;
    }
}
