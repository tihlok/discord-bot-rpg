package games.pixelfox.rpg.configs

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "bot.discord")
class BotDiscordProperties {
    var token: String? = null
}
