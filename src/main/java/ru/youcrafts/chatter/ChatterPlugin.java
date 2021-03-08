package ru.youcrafts.chatter;

import co.aikar.commands.PaperCommandManager;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.dependency.Dependency;
import org.bukkit.plugin.java.annotation.dependency.SoftDependency;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;
import ru.youcrafts.chatter.commands.PersonalMessageCommand;
import ru.youcrafts.chatter.commands.ReloadCommand;
import ru.youcrafts.chatter.listeners.ChatListener;
import ru.youcrafts.chatter.manager.ChannelManager;

@Plugin(
        name = "ChatterPlugin",
        version = "1.0.0"
)
@Author(value = "oDD1 / Alexander Repin")
@Description(value = "Chat plugin. Channels, personal messages and colors")
@Dependency(value = "Vault")
@SoftDependency(value = "PlaceholderAPI")
public class ChatterPlugin extends JavaPlugin
{


    private static ChatterPlugin plugin;
    private static Config config;
    private static PaperCommandManager commandManager;

    public static ChannelManager channelManager;
    public static boolean hasPAPI = false;

    public Chat chat;


    @Override
    public void onEnable()
    {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            ChatterPlugin.hasPAPI = true;
        }

        ChatterPlugin.plugin = this;
        ChatterPlugin.commandManager = new PaperCommandManager(this);
        ChatterPlugin.config = new Config(this);
        ChatterPlugin.config.init();
        ChatterPlugin.channelManager = new ChannelManager(ChatterPlugin.config);
        ChatterPlugin.channelManager.registerChannels();

        this.registerChat();
        this.registerCommands();
        this.registerListeners();
    }


    public static ChatterPlugin getInstance()
    {
        return ChatterPlugin.plugin;
    }


    private void registerChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        this.chat = rsp.getProvider();
    }


    private void registerCommands()
    {
        ChatterPlugin.commandManager.registerCommand(new PersonalMessageCommand(ChatterPlugin.config));
        ChatterPlugin.commandManager.registerCommand(new ReloadCommand(ChatterPlugin.config, ChatterPlugin.channelManager));
    }


    private void registerListeners()
    {
        Bukkit.getPluginManager().registerEvents(
                new ChatListener(ChatterPlugin.channelManager), this
        );
    }
}
