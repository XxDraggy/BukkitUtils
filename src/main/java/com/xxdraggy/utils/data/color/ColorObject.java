package com.xxdraggy.utils.data.color;

import org.bukkit.Color;

public class ColorObject {
    private String color;
    public String getColor() {
        return color;
    }

    private String hex;
    public String getHex() {
        return hex;
    }

    private java.awt.Color java;
    public java.awt.Color getJavaObject() {
        return java;
    }

    private Color bukkit;
    public Color getBukkitObject() {
        return bukkit;
    }

    public ColorObject(String color, String hex, Color bukkit, java.awt.Color java) {
        this.color = color;
        this.hex = hex;
        this.bukkit = bukkit;
        this.java = java;
    }
}
