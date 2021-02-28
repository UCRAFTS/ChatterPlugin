package ru.youcrafts.chatter.channels;

import org.bukkit.event.player.AsyncPlayerChatEvent;

public interface ChatHandlerInterface
{


    String getName();

    boolean isSupportBySymbol(String symbol);

    boolean isSupportByName(String name);

    void handle(final AsyncPlayerChatEvent event);
}
