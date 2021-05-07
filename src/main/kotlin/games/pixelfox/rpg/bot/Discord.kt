package games.pixelfox.rpg.bot

import dev.kord.core.Kord
import dev.kord.core.entity.Message
import dev.kord.core.event.message.MessageCreateEvent
import games.pixelfox.rpg.exceptions.BotExceptions
import games.pixelfox.rpg.players.PlayerCommands
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class Discord(
    @Autowired val playerCommands: PlayerCommands
) {
    @Value("\${bot.discord.token}")
    private val token: String? = null

    private val commands: Map<String, suspend (Message) -> Message> = mapOf(
        "!ping" to { it.channel.createMessage("pong!") },
        "!start" to { playerCommands.start(it) },
    )

    fun run() {
        runBlocking {
            val client = Kord(token!!)
            commands.onEach { command ->
                client.events
                    .filterIsInstance<MessageCreateEvent>()
                    .filter { it.message.author?.isBot == false }
                    .filter { it.message.content == command.key }
                    .onEach {
                        try {
                            command.value(it.message)
                        } catch (e: BotExceptions) {
                            it.message.channel.createMessage(e.message!!)
                        }
                    }
                    .launchIn(client)
            }
            client.login()
        }
    }
}
