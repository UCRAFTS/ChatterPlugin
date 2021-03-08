package ru.youcrafts.chatter.channels;

import me.clip.placeholderapi.PlaceholderAPI;
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

        String formatted = this.config.getConfig().getString(ConfigType.FORMAT.getName());
        String prefix = ChatterPlugin.getInstance().chat.getPlayerPrefix(player);
        String suffix = ChatterPlugin.getInstance().chat.getPlayerSuffix(player);

        if (prefix != null && prefix.length() > 0 && this.config.getConfig().getBoolean(ConfigType.SPACE_AFTER_GROUP.getName())) {
            prefix+= " ";
        }

        formatted = Utils.colorize(
                formatted
                        .replace("{group}", prefix)
                        .replace("{color}", suffix)
                        .replace("{player}", player.getName())
        );

        if (ChatterPlugin.hasPAPI) {
            formatted = PlaceholderAPI.setPlaceholders(player, formatted);
        }

        return formatted + message;
    }
}
