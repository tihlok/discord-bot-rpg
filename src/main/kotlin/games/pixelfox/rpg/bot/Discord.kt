package games.pixelfox.rpg.bot

import dev.kord.core.Kord
import dev.kord.core.entity.Message
import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class Discord {
    @Value("\${bot.discord.token}")
    private val token: String? = null

    private val commands: Map<String, suspend (Message) -> Message> = mapOf(
        "!ping" to { this.ping(it) },
        "!pong" to { this.pong(it) }
    )

    fun run() {
        runBlocking {
            val client = Kord(token!!)
            commands.onEach { command ->
                client.events
                    .filterIsInstance<MessageCreateEvent>()
                    .filter { it.message.author?.isBot == false }
                    .filter { it.message.content == command.key }
                    .onEach { command.value(it.message) }
                    .launchIn(client)
            }
            client.login()
        }
    }

    suspend fun ping(message: Message): Message {
        return message.channel.createMessage("pong!")
    }

    suspend fun pong(message: Message): Message {
        return message.channel.createMessage("ping!")
    }
}
