package com.xxdraggy.utils.creator.text;

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
    public boolean strikethrough;
    public boolean bold;
    public boolean italic;
}
