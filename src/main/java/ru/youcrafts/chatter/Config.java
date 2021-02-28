package ru.youcrafts.chatter;

import de.leonhard.storage.Json;
import de.leonhard.storage.internal.FlatFile;
import ru.youcrafts.chatter.types.ConfigType;

public class Config
{

    private final FlatFile config;

    public Config(ChatterPlugin plugin)
    {
        this.config = new Json("config", plugin.getDataFolder().getPath());
    }

    public FlatFile getConfig()
    {
        return this.config;
    }

    public void init()
    {
        this.config.setDefault(ConfigType.FORMAT.getName(), "%s%s%s: %s");
        this.config.setDefault(ConfigType.COLORS_PERMISSION.getName(), "chatter.colors");
        this.config.setDefault(ConfigType.LOCAL_CHAT_RADIUS.getName(), 100);
        this.config.setDefault(ConfigType.LOCAL_CHAT_PREFIX.getName(), "[L]");
        this.config.setDefault(ConfigType.LOCAL_CHAT_NOTIFY.getName(), "Ваше сообщение видят игроки в радиусе %s блоков от Вас. Добавьте в начале сообщения \"%s\" дабы его видели все!");
        this.config.setDefault(ConfigType.GLOBAL_CHAT_SYMBOL.getName(), "!");
        this.config.setDefault(ConfigType.GLOBAL_CHAT_PREFIX.getName(), "[G]");
        this.config.setDefault(ConfigType.GLOBAL_CHAT_PERMISSION.getName(), "chatter.channels");
        this.config.setDefault(ConfigType.GLOBAL_CHAT_PERMISSION_MESSAGE.getName(), "Использование всех возможностей чата доступно с 1 ранга! Напиши /ranks дабы получить справку!");
        this.config.setDefault(ConfigType.ECONOMY_CHAT_SYMBOL.getName(), "$");
        this.config.setDefault(ConfigType.ECONOMY_CHAT_PREFIX.getName(), "[T]");
        this.config.setDefault(ConfigType.ECONOMY_CHAT_PERMISSION.getName(), "chatter.channels");
        this.config.setDefault(ConfigType.ECONOMY_CHAT_PERMISSION_MESSAGE.getName(), "Использование всех возможностей чата доступно с 1 ранга! Напиши /ranks дабы получить справку!");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_FORMAT.getName(), "[ЛС] %s > %s: %s");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_PERMISSION.getName(), "chatter.channels");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_PERMISSION_MESSAGE.getName(), "Использование всех возможностей чата доступно с 1 ранга! Напиши /ranks дабы получить справку!");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_PLAYER_NOT_FOUND_MESSAGE.getName(), "Невозможно отправить личное сообщение: игрок отсутствует на сервере!");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_REPLY_EMPTY_MESSAGE.getName(), "Для быстрого ответа напишите: /r <сообщение>");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_EMPTY_MESSAGE.getName(), "Для отправки личного сообщения напишите: /m <игрок> <сообщение>");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_PLAYER_NOT_FOUND_MESSAGE.getName(), "Невозможно отправить личное сообщение: игрок отсутствует на сервере!");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_YOURSELF_MESSAGE.getName(), "Невозможно отправить личное сообщение самому себе!");
        this.config.setDefault(ConfigType.SPY_PERMISSION.getName(), "chatter.spy");
        this.config.setDefault(ConfigType.SPY_PREFIX.getName(), "[@]");
    }
}
