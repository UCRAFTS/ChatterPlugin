package ru.youcrafts.chatter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{


    public static String colorize(@NotNull final String message)
    {
        return Utils.translateHexColorCodes(
                ChatColor.translateAlternateColorCodes('&', message)
        );
    }


    public static String translateHexColorCodes(@NotNull final String message)
    {
        final Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
        final char colorChar = ChatColor.COLOR_CHAR;
        final Matcher matcher = hexPattern.matcher(message);
        final StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);

        while (matcher.find()) {
            final String group = matcher.group(1);

            matcher.appendReplacement(buffer, colorChar + "x"
                    + colorChar + group.charAt(0) + colorChar + group.charAt(1)
                    + colorChar + group.charAt(2) + colorChar + group.charAt(3)
                    + colorChar + group.charAt(4) + colorChar + group.charAt(5));
        }

        return matcher.appendTail(buffer).toString();
    }


    public static void log(@NotNull CommandSender sender, @NotNull String message)
    {
        if (sender instanceof Player) {
            Utils.log((Player) sender, message);
        } else {
            Bukkit.getLogger().info(message);
        }
    }


    public static void log(@NotNull Player player, @NotNull String message)
    {
        Location location = player.getLocation();
        String log = String.format(
                "[%s][%s,%s,%s] %s: %s",
                location.getWorld().getName(),
                (int) location.getX(),
                (int) location.getY(),
                (int) location.getZ(),
                player.getName(),
                message
        );

        Bukkit.getLogger().info(log);
    }
}
