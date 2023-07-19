package com.xxdraggy.utils.data;

import com.xxdraggy.utils.data.color.ColorType;

public class TextPart {
    public TextPart() {}

    public TextPart(String text) {
        this.text = text;
    }

    public String text;
    public ColorType colorType;
    public String color;
    public String[] gradientColors;

    public boolean magic;
    public boolean underlined;
    public boolean stroke;
    public boolean bold;
    public boolean italic;
}
