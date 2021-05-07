package games.pixelfox.rpg

import games.pixelfox.rpg.bot.Discord
import games.pixelfox.rpg.configs.BotDiscordProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(BotDiscordProperties::class)
@SpringBootApplication
class RpgApplication : CommandLineRunner {
    @Autowired
    private lateinit var discord: Discord

    override fun run(vararg args: String?) = discord.run()
}

fun main(args: Array<String>) {
    runApplication<RpgApplication>(*args)
}
