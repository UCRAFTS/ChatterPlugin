package ru.youcrafts.chatter.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Syntax;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import ru.youcrafts.chatter.Config;
import ru.youcrafts.chatter.Utils;
import ru.youcrafts.chatter.types.ConfigType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class PersonalMessageCommand extends BaseCommand
{


    private final Config config;
    private final HashMap<UUID, UUID> replyList = new HashMap<>();


    public PersonalMessageCommand(Config config)
    {
        this.config = config;
    }


    @CommandAlias("m|msg|tell|t|whisper")
    @Syntax("<player> <message>")
    @CommandCompletion("@players")
    public void onPersonalMessage(CommandSender sender, String[] args)
    {
        if (!sender.hasPermission(this.config.getConfig().getString(ConfigType.PERSONAL_MESSAGE_PERMISSION.getName()))) {
            sender.sendMessage(Utils.colorize(this.config.getConfig().getString(ConfigType.PERSONAL_MESSAGE_PERMISSION_MESSAGE.getName())));
            return;
        }

        if (args.length < 2) {
            sender.sendMessage(Utils.colorize(this.config.getConfig().getString(ConfigType.PERSONAL_MESSAGE_EMPTY_MESSAGE.getName())));
            return;
        }

        Player recipient = Bukkit.getPlayer(args[0]);
        String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        if (recipient == null || !recipient.isOnline()) {
            sender.sendMessage(Utils.colorize(this.config.getConfig().getString(ConfigType.PERSONAL_MESSAGE_PLAYER_NOT_FOUND_MESSAGE.getName())));
            return;
        }

        this.sendPersonalMessage(sender, recipient, message);
    }


    @CommandAlias("r")
    @Syntax("<message>")
    public void onReply(CommandSender sender, String[] args)
    {
        if (sender instanceof ConsoleCommandSender) {
            return;
        }

        if (!sender.hasPermission(this.config.getConfig().getString(ConfigType.PERSONAL_MESSAGE_PERMISSION.getName()))) {
            sender.sendMessage(Utils.colorize(this.config.getConfig().getString(ConfigType.PERSONAL_MESSAGE_PERMISSION_MESSAGE.getName())));
            return;
        }

        if (args.length == 0) {
            sender.sendMessage(Utils.colorize(this.config.getConfig().getString(ConfigType.PERSONAL_MESSAGE_REPLY_EMPTY_MESSAGE.getName())));
            return;
        }

        String message = String.join(" ", args);
        Player player = (Player) sender;

        if (!this.replyList.containsKey(player.getUniqueId())) {
            sender.sendMessage(Utils.colorize(this.config.getConfig().getString(ConfigType.PERSONAL_MESSAGE_PLAYER_NOT_FOUND_MESSAGE.getName())));
            return;
        }

        Player recipient = Bukkit.getPlayer(this.replyList.get(player.getUniqueId()));

        if (recipient == null || !recipient.isOnline()) {
            sender.sendMessage(Utils.colorize(this.config.getConfig().getString(ConfigType.PERSONAL_MESSAGE_PLAYER_NOT_FOUND_MESSAGE.getName())));
            return;
        }

        this.sendPersonalMessage(sender, recipient, message);
    }


    private void sendPersonalMessage(CommandSender sender, Player recipient, String message)
    {
        String formatter = Utils.colorize(
                String.format(
                    this.config.getConfig().getString(ConfigType.PERSONAL_MESSAGE_FORMAT.getName()), sender.getName(), recipient.getName()
                )
        );

        if (sender.hasPermission(this.config.getConfig().getString(ConfigType.COLORS_PERMISSION.getName()))) {
            message = Utils.colorize(message);
        }

        formatter = formatter + message;

        sender.sendMessage(formatter);
        recipient.sendMessage(formatter);
        recipient.playSound(recipient.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 1);

        Utils.log(sender, formatter);

        if (!(sender instanceof ConsoleCommandSender)) {
            Player player = (Player) sender;
            this.replyList.put(player.getUniqueId(), recipient.getUniqueId());
        }
    }
}
