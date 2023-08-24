package com.xxdraggy.utils.builders.text;

import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.data.color.ColorObject;
import com.xxdraggy.utils.data.color.ColorType;
import com.xxdraggy.utils.data.color.RgbColor;
import com.xxdraggy.utils.data.text.TextObject;
import com.xxdraggy.utils.data.text.TextPart;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.awt.*;

public class TextBuilder {
    private TextPart part;
    public TextPart getPart() {
        return part;
    }

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
        this.part.color = TextBuilder.getColorObject(ChatColor.BLACK);

        return this;
    }
    public TextBuilder darkBlue() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.DARK_BLUE);

        return this;
    }
    public TextBuilder darkGreen() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.DARK_GREEN);

        return this;
    }
    public TextBuilder darkAqua() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.DARK_AQUA);

        return this;
    }
    public TextBuilder darkRed() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.DARK_RED);

        return this;
    }
    public TextBuilder darkPurple() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.DARK_PURPLE);

        return this;
    }
    public TextBuilder gold() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.GOLD);

        return this;
    }
    public TextBuilder gray() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.GRAY);

        return this;
    }
    public TextBuilder darkGray() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.DARK_GRAY);

        return this;
    }
    public TextBuilder blue() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.BLUE);

        return this;
    }
    public TextBuilder green() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.GREEN);

        return this;
    }
    public TextBuilder aqua() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.AQUA);

        return this;
    }
    public TextBuilder red() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.RED);

        return this;
    }
    public TextBuilder lightPurple() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.LIGHT_PURPLE);

        return this;
    }
    public TextBuilder yellow() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.YELLOW);

        return this;
    }
    public TextBuilder white() {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(ChatColor.WHITE);

        return this;
    }

    public TextBuilder color(ChatColor color) {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.getColorObject(color);

        return this;
    }
    public TextBuilder rgb(int red, int green, int blue) {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.rgbColor(red, green, blue);

        return this;
    }
    public TextBuilder hex(String hexColor) {
        this.part.colorType = ColorType.OneColor;
        this.part.color = TextBuilder.hexColor(hexColor);

        return this;
    }
    public TextBuilder gradient(ColorObject... gradientColors) {
        this.part.colorType = ColorType.Gradient;
        this.part.gradientColors = gradientColors;

        return this;
    }
    public TextBuilder rainbow() {
        this.part.colorType = ColorType.Rainbow;
        this.part.color = null;

        return this;
    }

    public TextBuilder onHover(HoverEvent event) {
        part.hover = event;

        return this;
    }
    public TextBuilder onClick(ClickEvent event) {
        part.click = event;

        return this;
    }

    public TextBuilder append(String text) {
        return append(new TextPart(text));
    }
    public TextBuilder append(TextBuilder builder) {
        return append(builder.getPart());
    }
    public TextBuilder append(TextPart part) {
        this.part.extras.add(part);

        return this;
    }

    public String toString() {
        return TextBuilder.toString(this);
    }
    public TextComponent getComponent() {
        return TextBuilder.getTextComponent(this);
    }
    public TextObject getObject() {
        return TextBuilder.getTextObject(this);
    }

    public static ColorObject hexColor(String hexColor) {
        return getColorObject(
                ChatColor.of(
                        new Color(
                                Integer.valueOf(hexColor.substring(0, 2), 16 ),
                                Integer.valueOf(hexColor.substring(2, 4), 16 ),
                                Integer.valueOf(hexColor.substring(4, 6), 16 )
                        )
                )
        );
    }
    public static ColorObject rgbColor(RgbColor rgb) {
        return getColorObject(
                ChatColor.of(
                        new Color(
                                rgb.getRed(),
                                rgb.getGreen(),
                                rgb.getBlue()
                        )
                )
        );
    }
    public static ColorObject rgbColor(int red, int green, int blue) {
        return TextBuilder.rgbColor(new RgbColor(red, green, blue));
    }

    public static String toString(TextBuilder builder) {
        return TextBuilder.getTextObject(builder).getText();
    }
    public static String toString(TextPart part) {
        return TextBuilder.getTextObject(new TextBuilder(part)).getText();
    }

    public static TextComponent getTextComponent(TextPart part) {
        return getTextComponent(new TextBuilder(part));
    }
    public static TextComponent getTextComponent(TextBuilder builder) {
        TextPart part = builder.part;
        TextComponent component = new TextComponent();

        component.setObfuscated(part.magic);
        component.setBold(part.bold);
        component.setItalic(part.italic);
        component.setStrikethrough(part.stroke);
        component.setUnderlined(part.underlined);

        if(part.color != null) {
            if(part.colorType == ColorType.Gradient)
                Creator.gradient(part.text, part.gradientColors);
            else if(part.colorType == ColorType.Rainbow) {
                Creator.rainbow(part.text, component);
            } else {
                component.setColor(net.md_5.bungee.api.ChatColor.of(part.color.getJavaObject()));
                component.setText(part.text);
            }
        }
        else
            component.setText(part.text);

        if(part.hover != null)
            component.setHoverEvent(part.hover);
        if(part.click != null)
            component.setClickEvent(part.click);

        for (TextPart localComponent : part.extras)
            component.addExtra(new TextBuilder(localComponent).getComponent());

        return component;
    }

    public static TextObject getTextObject(TextPart part) {
        return getTextObject(new TextBuilder(part));
    }
    public static TextObject getTextObject(TextBuilder builder) {
        TextPart part = builder.part;
        TextComponent component = new TextComponent();

        component.setObfuscated(part.magic);
        component.setBold(part.bold);
        component.setItalic(part.italic);
        component.setStrikethrough(part.stroke);
        component.setUnderlined(part.underlined);

        if(part.color != null) {
            if(part.colorType == ColorType.Gradient)
                Creator.gradient(part.text, part.gradientColors);
            else if(part.colorType == ColorType.Rainbow) {
                Creator.rainbow(part.text, component);
            } else {
                component.setColor(net.md_5.bungee.api.ChatColor.of(part.color.getJavaObject()));
                component.setText(part.text);
            }
        }
        else
            component.setText(part.text);

        if(part.hover != null)
            component.setHoverEvent(part.hover);
        if(part.click != null)
            component.setClickEvent(part.click);

        TextObject[] objects = new TextObject[part.extras.size() + 1];

        objects[0] = new TextObject(part, builder, component, component.toLegacyText() + ChatColor.RESET);

        for (int index = 0; index < part.extras.size(); index++) {
            TextPart localPart = part.extras.get(index);
            TextBuilder localBuilder = new TextBuilder(localPart);
            TextComponent localComponent = localBuilder.getComponent();

            objects[index + 1] = new TextObject(localPart, localBuilder, localComponent, localComponent.toLegacyText() + ChatColor.RESET);
        }

        return TextObject.combine(objects);
    }

    public static ColorObject getColorObject(ChatColor color) {
        return new ColorObject(
                color.toString(),
                Integer.toHexString(color.getColor().getRGB()).substring(2).toUpperCase(),
                org.bukkit.Color.fromRGB(
                        color.getColor().getRed(),
                        color.getColor().getGreen(),
                        color.getColor().getBlue()
                ),
                color.getColor()
        );
    }
    public static ColorObject getColorObject(org.bukkit.ChatColor color) {
        return TextBuilder.getColorObject(color.asBungee());
    }
}
