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
        this.config.setDefault(ConfigType.FORMAT.getName(), "{group} &r{color}{player} &8➟ &7");
        this.config.setDefault(ConfigType.COLORS_PERMISSION.getName(), "chatter.colors");
        this.config.setDefault(ConfigType.LOCAL_CHAT_RADIUS.getName(), 100);
        this.config.setDefault(ConfigType.LOCAL_CHAT_PREFIX.getName(), " &8&l• &r");
        this.config.setDefault(ConfigType.LOCAL_CHAT_NOTIFY.getName(), " &l&c• &cВаше сообщение видят игроки в радиусе &7%s &cблоков от Вас.\n &l&c• &cДобавьте в начале сообщения &8«&l&7%s&8» &cдабы его видели все!");
        this.config.setDefault(ConfigType.GLOBAL_CHAT_SYMBOL.getName(), "!");
        this.config.setDefault(ConfigType.GLOBAL_CHAT_PREFIX.getName(), " &a&l• &r");
        this.config.setDefault(ConfigType.GLOBAL_CHAT_PERMISSION.getName(), "chatter.channels");
        this.config.setDefault(ConfigType.GLOBAL_CHAT_PERMISSION_MESSAGE.getName(), " &l&c• &cИспользование всех возможностей чата доступно с &b1 ранга&c! Напиши &7/ranks &cдабы получить справку!");
        this.config.setDefault(ConfigType.ECONOMY_CHAT_SYMBOL.getName(), "$");
        this.config.setDefault(ConfigType.ECONOMY_CHAT_PREFIX.getName(), " &6&l• &r");
        this.config.setDefault(ConfigType.ECONOMY_CHAT_PERMISSION.getName(), "chatter.channels");
        this.config.setDefault(ConfigType.ECONOMY_CHAT_PERMISSION_MESSAGE.getName(), " &l&c• &cИспользование всех возможностей чата доступно с &b1 ранга&c! Напиши &7/ranks &cдабы получить справку!");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_FORMAT.getName(), " &e&lЛС &f%s &8➟ &f%s &b➟ &e");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_PERMISSION.getName(), "chatter.channels");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_PERMISSION_MESSAGE.getName(), " &l&c• &cИспользование всех возможностей чата доступно с &b1 ранга&c! Напиши &7/ranks &cдабы получить справку!");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_REPLY_EMPTY_MESSAGE.getName(), " &l&c• &cДля быстрого ответа напишите: &7/r &8<сообщение>");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_EMPTY_MESSAGE.getName(), " &l&c• &cДля отправки личного сообщения напишите: &7/m &8<игрок> &8<сообщение>");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_PLAYER_NOT_FOUND_MESSAGE.getName(), " &l&c• &cНевозможно отправить личное сообщение: игрок отсутствует на сервере!");
        this.config.setDefault(ConfigType.PERSONAL_MESSAGE_YOURSELF_MESSAGE.getName(), " &l&c• &cНевозможно отправить личное сообщение самому себе!");
        this.config.setDefault(ConfigType.SPY_PERMISSION.getName(), "chatter.spy");
        this.config.setDefault(ConfigType.SPY_PREFIX.getName(), "[@]");
        this.config.setDefault(ConfigType.RELOAD_MESSAGE.getName(), "&aКонфигурация плагина успешно перезагружена!");
    }
}
