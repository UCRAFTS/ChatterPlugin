package ru.youcrafts.chatter.types;

import org.jetbrains.annotations.NotNull;

public enum ConfigType
{

    FORMAT("format"),
    RELOAD_MESSAGE("reloadMessage"),
    COLORS_PERMISSION("colorsPermission"),
    SPACE_AFTER_GROUP("spaceAfterGroup"),
    LOCAL_CHAT_RADIUS("channels.local.radius"),
    LOCAL_CHAT_PREFIX("channels.local.prefix"),
    LOCAL_CHAT_NOTIFY("channels.local.notify"),
    GLOBAL_CHAT_SYMBOL("channels.global.symbol"),
    GLOBAL_CHAT_PREFIX("channels.global.prefix"),
    GLOBAL_CHAT_PERMISSION("channels.global.permission"),
    GLOBAL_CHAT_PERMISSION_MESSAGE("channels.global.permissionMessage"),
    ECONOMY_CHAT_SYMBOL("channels.economy.symbol"),
    ECONOMY_CHAT_PREFIX("channels.economy.prefix"),
    ECONOMY_CHAT_PERMISSION("channels.economy.permission"),
    ECONOMY_CHAT_PERMISSION_MESSAGE("channels.economy.permissionMessage"),
    PERSONAL_MESSAGE_FORMAT("personalMessage.formal"),
    PERSONAL_MESSAGE_PERMISSION("personalMessage.permission"),
    PERSONAL_MESSAGE_PERMISSION_MESSAGE("personalMessage.permissionMessage"),
    PERSONAL_MESSAGE_PLAYER_NOT_FOUND_MESSAGE("personalMessage.playerNotFoundMessage"),
    PERSONAL_MESSAGE_REPLY_EMPTY_MESSAGE("personalMessage.replayEmptyMessage"),
    PERSONAL_MESSAGE_EMPTY_MESSAGE("personalMessage.emptyMessage"),
    PERSONAL_MESSAGE_YOURSELF_MESSAGE("personalMessage.yourselfMessage"),
    SPY_PERMISSION("spy.permission"),
    SPY_PREFIX("spy.prefix");

    private final String name;

    ConfigType(@NotNull final String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
