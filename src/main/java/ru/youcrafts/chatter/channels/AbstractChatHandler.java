package ru.youcrafts.chatter.channels;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.youcrafts.chatter.ChatterPlugin;
import ru.youcrafts.chatter.Config;
import ru.youcrafts.chatter.Utils;
import ru.youcrafts.chatter.types.ConfigType;

public abstract class AbstractChatHandler
{


    protected Config config;
    protected String name;
    protected String symbol;


    public AbstractChatHandler(@NotNull Config config)
    {
        this.config = config;
    }


    public String getName()
    {
        return this.name;
    }


    public boolean isSupportBySymbol(String symbol)
    {
        return symbol.equals(this.symbol);
    }


    public boolean isSupportByName(@NotNull String name)
    {
        return this.getName().equals(name);
    }


    protected String makeFormat(String message, Player player)
    {
        if (player.hasPermission(this.config.getConfig().getString(ConfigType.COLORS_PERMISSION.getName()))) {
            message = Utils.colorize(message);
        }

        String prefix = ChatterPlugin.getInstance().chat.getPlayerPrefix(player);
        String suffix = ChatterPlugin.getInstance().chat.getPlayerSuffix(player);

        return String.format(
                this.config.getConfig().getString(ConfigType.FORMAT.getName()),
                prefix,
                suffix,
                player.getName(),
                message
        );
    }
}
