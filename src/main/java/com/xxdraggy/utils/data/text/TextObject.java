package com.xxdraggy.utils.data.text;

import com.xxdraggy.utils.builders.text.TextBuilder;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextObject {
    private final TextPart data;
    public TextPart getData() {
        return data;
    }

    private final TextBuilder builder;
    public TextBuilder getBuilder() {
        return builder;
    }

    private final TextComponent component;
    public TextComponent getComponent() {
        return component;
    }

    private String text;
    public String getText() {
        return text;
    }

    public TextObject(TextPart data, TextBuilder builder, TextComponent component, String text) {
        this.data = data;
        this.builder = builder;
        this.component = component;
        this.text = text;
    }

    public static TextObject combine(TextObject... objects) {
        List<TextObject> list = new ArrayList<>(Arrays.asList(objects));

        TextObject combined = list.remove(0);

        for (TextObject object : list)
            TextObject.combine(combined, object);

        return combined;
    }
    public static TextObject combine(TextObject first, TextObject second) {
        first.data.extras.add(second.data);
        first.builder.append(second.builder);
        first.component.addExtra(second.component);
        first.text = first.text + second.text;

        return first;
    }
}
