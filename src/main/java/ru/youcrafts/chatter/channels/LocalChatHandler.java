package ru.youcrafts.chatter.channels;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.youcrafts.chatter.ChatterPlugin;
import ru.youcrafts.chatter.Config;
import ru.youcrafts.chatter.Utils;
import ru.youcrafts.chatter.types.ConfigType;

import java.util.HashMap;

public class LocalChatHandler extends AbstractChatHandler implements ChatHandlerInterface
{


    private final HashMap<Player, Integer> notify = new HashMap<>();
    private final int radius = this.config.getConfig().getInt(ConfigType.LOCAL_CHAT_RADIUS.getName());
    private final String globalChatSymbol = this.config.getConfig().getString(ConfigType.GLOBAL_CHAT_SYMBOL.getName());
    private final String localChatNotify = this.config.getConfig().getString(ConfigType.LOCAL_CHAT_NOTIFY.getName());
    private final String prefix = this.config.getConfig().getString(ConfigType.LOCAL_CHAT_PREFIX.getName());


    public LocalChatHandler(Config config)
    {
        super(config);

        this.name = "local";
    }


    @Override
    public boolean isSupportBySymbol(String symbol)
    {
        return true;
    }


    @Override
    public void handle(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String formatter = Utils.colorize(this.prefix) + super.makeFormat(message, player);

        Utils.log(player, message);
        Bukkit.getScheduler().runTask(ChatterPlugin.getInstance(), () -> {
            Object[] nearbyPlayers = player.getNearbyEntities(radius, radius, radius)
                    .stream()
                    .filter(entity -> entity instanceof Player)
                    .toArray();

            Bukkit.getScheduler().runTaskAsynchronously(ChatterPlugin.getInstance(), () -> {
                player.sendMessage(formatter);

                for (Object nearbyPlayer : nearbyPlayers) {
                    ((Player) nearbyPlayer).sendMessage(formatter);
                }
            });
        });

        if (this.notify.containsKey(player)) {
            int notify = this.notify.get(player);
            notify++;

            if (notify == 1) {
                player.sendMessage(
                        String.format(
                                Utils.colorize(this.localChatNotify),
                                this.radius,
                                this.globalChatSymbol
                        )
                );
            }

            this.notify.put(player, notify >= 100 ? 0 : notify);
        } else {
            this.notify.put(player, 0);
        }
    }
}
