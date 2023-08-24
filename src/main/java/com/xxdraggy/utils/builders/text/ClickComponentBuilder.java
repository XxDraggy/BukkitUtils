package com.xxdraggy.utils.builders.text;

import net.md_5.bungee.api.chat.ClickEvent;

public class ClickComponentBuilder {
    public static ClickEvent url(String url) {
        return new ClickEvent(ClickEvent.Action.OPEN_URL, url);
    }

    public static ClickEvent command(String command) {
        return new ClickEvent(ClickEvent.Action.RUN_COMMAND, command);
    }

    public static ClickEvent suggestCommand(String command) {
        return new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command);
    }

    public static ClickEvent bookPage(int page) {
        return new ClickEvent(ClickEvent.Action.CHANGE_PAGE, page + "");
    }

    public static ClickEvent copy(String text) {
        return new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, text);
    }
}
