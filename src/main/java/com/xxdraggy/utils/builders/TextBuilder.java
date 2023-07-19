package com.xxdraggy.utils.builders;

import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.data.TextPart;
import com.xxdraggy.utils.data.color.ArgbColor;
import com.xxdraggy.utils.data.color.ColorObject;
import com.xxdraggy.utils.data.color.ColorType;
import com.xxdraggy.utils.data.color.RgbColor;
import org.bukkit.ChatColor;
import org.bukkit.Color;

public class TextBuilder {
    private TextPart part;

    public TextBuilder() {}
    public TextBuilder(String text) {
        part = new TextPart(text);
    }
    private TextBuilder(TextPart part) {
        this.part = part;
    }

    public TextBuilder clone() {
        return new TextBuilder(part);
    }

    public TextBuilder setText(String text) {
        this.part.text = text;

        return this;
    }

    public TextBuilder bold() {
        this.part.bold = true;

        return this;
    }

    public TextBuilder magic() {
        this.part.magic = true;

        return this;
    }

    public TextBuilder stroke() {
        this.part.stroke = true;

        return this;
    }

    public TextBuilder underline() {
        this.part.underlined = true;

        return this;
    }

    public TextBuilder italic() {
        this.part.italic = true;

        return this;
    }

    public TextBuilder black() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.BLACK.toString();

        return this;
    }

    public TextBuilder darkBlue() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.DARK_BLUE.toString();

        return this;
    }

    public TextBuilder darkGreen() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.DARK_GREEN.toString();

        return this;
    }

    public TextBuilder darkAqua() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.DARK_AQUA.toString();

        return this;
    }

    public TextBuilder darkRed() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.DARK_RED.toString();

        return this;
    }

    public TextBuilder darkPurple() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.DARK_PURPLE.toString();

        return this;
    }

    public TextBuilder gold() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.GOLD.toString();

        return this;
    }

    public TextBuilder gray() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.GRAY.toString();

        return this;
    }

    public TextBuilder darkGray() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.DARK_GRAY.toString();

        return this;
    }

    public TextBuilder blue() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.BLUE.toString();

        return this;
    }

    public TextBuilder green() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.GREEN.toString();

        return this;
    }

    public TextBuilder aqua() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.AQUA.toString();

        return this;
    }

    public TextBuilder red() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.RED.toString();

        return this;
    }

    public TextBuilder lightPurple() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.LIGHT_PURPLE.toString();

        return this;
    }

    public TextBuilder yellow() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.YELLOW.toString();

        return this;
    }

    public TextBuilder white() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = ChatColor.WHITE.toString();

        return this;
    }

    public TextBuilder color(ChatColor color) {
        this.part.colorType = ColorType.OneColor;
        this.part.color = color.toString();

        return this;
    }

    public TextBuilder color(Color color) {
        this.part.colorType = ColorType.OneColor;
        this.part.color = color.toString();

        return this;
    }

    public TextBuilder rgb(int red, int green, int blue) {
        this.part.colorType = ColorType.OneColor;
        this.part.color = Color.fromRGB(red, green, blue).toString();

        return this;
    }

    public TextBuilder argb(int alpha, int red, int green, int blue) {
        this.part.colorType = ColorType.OneColor;
        this.part.color = Color.fromARGB(alpha, red, green, blue).toString();

        return this;
    }

    public TextBuilder hex(String hexColor) {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.hexColor(hexColor).toString();

        return this;
    }

    public TextBuilder gradient(String... gradientColors) {
        this.part.colorType = ColorType.Gradient;
        this.part.gradientColors = gradientColors;

        return this;
    }

    public TextBuilder rainbow() {
        this.part.colorType = ColorType.Rainbow;
        this.part.color = "";

        return this;
    }

    public static ColorObject hexColor(String hexColor) {
        Color object = Color.fromRGB(
                Integer.valueOf(hexColor.substring(1, 3), 16 ),
                Integer.valueOf(hexColor.substring(3, 5), 16 ),
                Integer.valueOf(hexColor.substring(5, 7), 16 )
        );

        return new ColorObject(object.toString(), object);
    }

    public static ColorObject rgbColor(RgbColor rgb) {
        Color object = Color.fromRGB(
                rgb.getRed(),
                rgb.getGreen(),
                rgb.getBlue()
        );

        return new ColorObject(object.toString(), object);
    }

    public static ColorObject argbColor(ArgbColor argb) {
        Color object = Color.fromARGB(
                argb.getAlpha(),
                argb.getRed(),
                argb.getGreen(),
                argb.getBlue()
        );

        return new ColorObject(object.toString(), object);
    }

    public String toString() {
        return TextBuilder.toString(this.part);
    }

    public static String toString(TextPart part) {
        String prefixes = "";

        if(part.magic)
            prefixes += ChatColor.MAGIC;
        if(part.bold)
            prefixes += ChatColor.BOLD;
        if(part.italic)
            prefixes += ChatColor.ITALIC;
        if(part.stroke)
            prefixes += ChatColor.STRIKETHROUGH;
        if(part.underlined)
            prefixes += ChatColor.UNDERLINE;

        if(part.colorType == ColorType.Gradient) {
            return prefixes += Creator.gradient(part.text, part.gradientColors);
        }
        else if(part.colorType == ColorType.Rainbow) {
            return prefixes += Creator.rainbow(part.text);
        }
        else {
            return prefixes += part.color + part.text;
        }
    }
}
