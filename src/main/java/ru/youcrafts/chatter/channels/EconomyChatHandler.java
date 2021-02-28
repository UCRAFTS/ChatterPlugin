package ru.youcrafts.chatter.channels;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.youcrafts.chatter.ChatterPlugin;
import ru.youcrafts.chatter.Config;
import ru.youcrafts.chatter.Utils;
import ru.youcrafts.chatter.types.ConfigType;

import java.util.Collection;

public class EconomyChatHandler extends AbstractChatHandler implements ChatHandlerInterface
{


    private final String permission = this.config.getConfig().getString(ConfigType.ECONOMY_CHAT_PERMISSION.getName());
    private final String permissionMessage = this.config.getConfig().getString(ConfigType.ECONOMY_CHAT_PERMISSION_MESSAGE.getName());
    private final String prefix = this.config.getConfig().getString(ConfigType.ECONOMY_CHAT_PREFIX.getName());


    public EconomyChatHandler(Config config)
    {
        super(config);

        this.name = "economy";
        this.symbol = this.config.getConfig().getString(ConfigType.ECONOMY_CHAT_SYMBOL.getName());
    }


    @Override
    public boolean isSupportBySymbol(String symbol)
    {
        return symbol.equals(this.symbol);
    }


    @Override
    public void handle(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();

        if (!player.hasPermission(this.permission)) {
            player.sendMessage(Utils.colorize(this.permissionMessage));
            return;
        }

        String message = event.getMessage().substring(1);

        if (message.length() == 0) {
            return;
        }

        String formatter = Utils.colorize(this.prefix) + super.makeFormat(message, player);

        Utils.log(player, message);
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        Bukkit.getScheduler().runTaskAsynchronously(ChatterPlugin.getInstance(), () -> {
            for (Player p : players) {
                p.sendMessage(formatter);
            }
        });
    }
}
