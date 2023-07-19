package com.xxdraggy.utils.data.color;

public class ArgbColor {
    private final int alpha;
    public int getAlpha() {
        return alpha;
    }

    private final int red;
    public int getRed() {
        return red;
    }

    private final int green;
    public int getGreen() {
        return green;
    }

    private final int blue;
    public int getBlue() {
        return blue;
    }

    public ArgbColor(int alpha, int red, int green, int blue) {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}
