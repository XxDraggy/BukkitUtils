package com.xxdraggy.utils.builders.text;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ItemTag;
import net.md_5.bungee.api.chat.hover.content.Entity;
import net.md_5.bungee.api.chat.hover.content.Item;
import net.md_5.bungee.api.chat.hover.content.Text;

public class HoverComponentBuilder {
    public static HoverEvent item(String id, int count, ItemTag tag) {
        return new HoverEvent(HoverEvent.Action.SHOW_ITEM, new Item(id, count, tag));
    }

    public static HoverEvent text(String text) {
        return new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(text));
    }

    public static HoverEvent entity(String type,  String id, BaseComponent name) {
        return new HoverEvent(HoverEvent.Action.SHOW_ENTITY, new Entity(type, id, name));
    }
}
