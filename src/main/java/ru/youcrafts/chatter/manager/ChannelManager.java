package ru.youcrafts.chatter.manager;

import org.bukkit.Bukkit;
import org.reflections.Reflections;
import ru.youcrafts.chatter.Config;
import ru.youcrafts.chatter.channels.ChatHandlerInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;

public class ChannelManager
{


    private final HashMap<String, ChatHandlerInterface> channels = new HashMap<>();
    private final Config config;


    public ChannelManager(Config config)
    {
        this.config = config;
    }


    public HashMap<String, ChatHandlerInterface> getChannels()
    {
        return this.channels;
    }


    public void registerChannels()
    {
        Reflections reflections = new Reflections("ru.youcrafts.chatter.channels");
        Set<Class<? extends ChatHandlerInterface>> channelClasses = reflections.getSubTypesOf(ChatHandlerInterface.class);

        for (Class<?> channelClass : channelClasses) {
            try {
                Class<?> _class = Class.forName(channelClass.getName());
                Constructor constructor = _class.getConstructor(this.config.getClass());
                ChatHandlerInterface handler = (ChatHandlerInterface) constructor.newInstance(this.config);

                this.channels.put(handler.getName(), handler);
            } catch (ClassNotFoundException
                            | InstantiationException
                            | IllegalAccessException
                            | InvocationTargetException
                            | NoSuchMethodException e
            ) {
                System.out.println();
                Bukkit.getLogger().info(e.getMessage());
            }
        }
    }
}
