package com.xxdraggy.utils.data.text;

import com.xxdraggy.utils.data.color.ColorObject;
import com.xxdraggy.utils.data.color.ColorType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextPart {
    public TextPart() {}

    public TextPart(String text) {
        this.text = text;
    }

    public String toString() {
        String textString =  "TEXT: " + text + "\n\n";
        String colorString =
                "COLOR" +
                        "\nType: " + colorType.name() +
                        "\n" + color.getColor() + "Color" +
                        "\nHex: " + color.getHex() +
                        "\nGradientColors: " + Arrays.toString(Arrays.stream(gradientColors).map(o -> o.getColor() + o.getHex()).toArray()) +
                        "\n\n";
        String decoString =
                "DECO" +
                        "\nMagic: " + magic +
                        "\nUnderlined: " + underlined +
                        "\nStroke: " + stroke +
                        "\nBold: " + bold +
                        "\nItalic: " + italic +
                        "\n\n";
        String eventString =
                "EVENTS" +
                        "\nHover: " + hover.toString() +
                        "\nClick: " + click.toString() +
                        "\n\n";


        return (textString + colorString + decoString + eventString).trim();
    }

    public String text = "";
    public ColorType colorType = ColorType.OneColor;
    public ColorObject color = null;
    public ColorObject[] gradientColors;

    public HoverEvent hover;
    public ClickEvent click;

    public List<TextPart> extras = new ArrayList<>();

    public boolean magic = false;
    public boolean underlined = false;
    public boolean stroke = false;
    public boolean bold = false;
    public boolean italic = false;
}
