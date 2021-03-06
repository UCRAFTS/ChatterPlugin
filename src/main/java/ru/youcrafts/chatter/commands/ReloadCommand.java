package ru.youcrafts.chatter.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.command.CommandSender;
import ru.youcrafts.chatter.Config;
import ru.youcrafts.chatter.Utils;
import ru.youcrafts.chatter.manager.ChannelManager;
import ru.youcrafts.chatter.types.ConfigType;

@CommandAlias("chatter|chr")
public class ReloadCommand extends BaseCommand
{


    private final Config config;

    private final ChannelManager channelManager;


    public ReloadCommand(Config config, ChannelManager channelManager)
    {
        this.config = config;
        this.channelManager = channelManager;
    }


    @Subcommand("reload")
    public void onReload(CommandSender sender)
    {
        if (sender.isOp()) {
            this.config.init();
            this.channelManager.registerChannels();

            sender.sendMessage(Utils.colorize(this.config.getConfig().getString(ConfigType.RELOAD_MESSAGE.getName())));
        }
    }
}
