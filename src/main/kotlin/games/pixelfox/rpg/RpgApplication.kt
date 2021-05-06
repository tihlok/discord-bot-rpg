package games.pixelfox.rpg

import dev.kord.core.Kord
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import games.pixelfox.rpg.configs.BotDiscordProperties
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(BotDiscordProperties::class)
@SpringBootApplication
class RpgApplication : CommandLineRunner {
    @Value("\${bot.discord.token}")
    private val token: String? = null

    override fun run(vararg args: String?) {
        runBlocking {
            val client = Kord(token!!)
            val pingPong = ReactionEmoji.Unicode("\uD83C\uDFD3")

            client.on<MessageCreateEvent> {
                if (message.content != "!ping") return@on
                val response = message.channel.createMessage("Pong!")
                response.addReaction(pingPong)
            }

            client.login()
        }
    }
}

fun main(args: Array<String>) {
    runApplication<RpgApplication>(*args)
}
