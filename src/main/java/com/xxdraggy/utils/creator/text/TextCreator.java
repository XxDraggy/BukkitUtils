package com.xxdraggy.utils.creator.text;

import com.xxdraggy.utils.Creator;
import org.bukkit.ChatColor;
import org.bukkit.Color;

public class TextCreator {
    private TextPart part;

    public TextCreator() {}

    public TextCreator(String text) {
        part = new TextPart(text);
    }

    public TextCreator setText(String text) {
        this.part.text = text;

        return this;
    }

    public TextCreator bold() {
        this.part.bold = true;

        return this;
    }

    public TextCreator magic() {
        this.part.magic = true;

        return this;
    }

    public TextCreator strikethrough() {
        this.part.strikethrough = true;

        return this;
    }

    public TextCreator underline() {
        this.part.underlined = true;

        return this;
    }

    public TextCreator italic() {
        this.part.italic = true;

        return this;
    }

    public TextCreator black() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.BLACK.toString();

        return this;
    }

    public TextCreator darkBlue() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.DARK_BLUE.toString();

        return this;
    }

    public TextCreator darkGreen() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.DARK_GREEN.toString();

        return this;
    }

    public TextCreator darkAqua() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.DARK_AQUA.toString();

        return this;
    }

    public TextCreator darkRed() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.DARK_RED.toString();

        return this;
    }

    public TextCreator darkPurple() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.DARK_PURPLE.toString();

        return this;
    }

    public TextCreator gold() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.GOLD.toString();

        return this;
    }

    public TextCreator gray() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.GRAY.toString();

        return this;
    }

    public TextCreator darkGray() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.DARK_GRAY.toString();

        return this;
    }

    public TextCreator blue() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.BLUE.toString();

        return this;
    }

    public TextCreator green() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.GREEN.toString();

        return this;
    }

    public TextCreator aqua() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.AQUA.toString();

        return this;
    }

    public TextCreator red() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.RED.toString();

        return this;
    }

    public TextCreator lightPurple() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.LIGHT_PURPLE.toString();

        return this;
    }

    public TextCreator yellow() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.YELLOW.toString();

        return this;
    }

    public TextCreator white() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.WHITE.toString();

        return this;
    }

    public TextCreator color(ChatColor color) {
        this.part.colorType = ColorType.OneColor;
        this.part.color = color.toString();

        return this;
    }

    public TextCreator rgb(int red, int green, int blue) {
        this.part.colorType = ColorType.OneColor;
        this.part.color = Color.fromRGB(red, green, blue).toString();

        return this;
    }

    public TextCreator argb(int alpha, int red, int green, int blue) {
        this.part.colorType = ColorType.OneColor;
        this.part.color = Color.fromARGB(alpha, red, green, blue).toString();

        return this;
    }

    public TextCreator hex(String hexColor) {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextCreator.hexColor(hexColor);

        return this;
    }

    public TextCreator gradient(String... gradientColors) {
        this.part.colorType = ColorType.Gradient;
        this.part.gradientColors = gradientColors;

        return this;
    }

    public TextCreator rainbow() {
        this.part.colorType = ColorType.Rainbow;
        this.part.color = "";

        return this;
    }

    public static String hexColor(String hexColor) {
        return Color.fromRGB(
                Integer.valueOf(hexColor.substring( 1, 3 ), 16 ),
                Integer.valueOf(hexColor.substring( 3, 5 ), 16 ),
                Integer.valueOf(hexColor.substring( 5, 7 ), 16 )
        ).toString();
    }


    public String toString() {
        return TextCreator.toString(this.part);
    }

    public static String toString(TextPart part) {
        String prefixes = "";

        if(part.magic)
            prefixes += ChatColor.MAGIC;
        if(part.bold)
            prefixes += ChatColor.BOLD;
        if(part.italic)
            prefixes += ChatColor.ITALIC;
        if(part.strikethrough)
            prefixes += ChatColor.STRIKETHROUGH;
        if(part.underlined)
            prefixes += ChatColor.UNDERLINE;

        if(part.colorType == ColorType.Gradient) {
            return prefixes + Creator.gradient(part.text, part.gradientColors);
        }
        else if(part.colorType == ColorType.Rainbow) {
            return prefixes + Creator.rainbow(part.text);
        }
        else {
            return prefixes + part.color + part.text;
        }
    }
}
