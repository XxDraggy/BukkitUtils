package com.xxdraggy.utils.data.events;

import net.md_5.bungee.api.chat.ClickEvent;

public enum TextClickAction {
    OpenUrl(ClickEvent.Action.OPEN_URL),
    RunCommand(ClickEvent.Action.RUN_COMMAND),
    SuggestCommand(ClickEvent.Action.SUGGEST_COMMAND),
    ChangeBookPage(ClickEvent.Action.CHANGE_PAGE),
    CopyToClipboard(ClickEvent.Action.COPY_TO_CLIPBOARD);

    TextClickAction(ClickEvent.Action action) {
        this.action = action;
    }

    private ClickEvent.Action action;
    public ClickEvent.Action getAction() {
        return action;
    }
}
