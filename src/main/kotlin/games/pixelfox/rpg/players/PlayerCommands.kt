package games.pixelfox.rpg.players

import dev.kord.core.entity.Message
import games.pixelfox.rpg.exceptions.BotExceptions
import games.pixelfox.rpg.exceptions.ErrorCodes
import games.pixelfox.rpg.helpers.answer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlayerCommands(
    @Autowired val playerService: PlayerService
) {
    @Throws(BotExceptions::class)
    suspend fun start(message: Message): Message {
        message.author?.let {
            playerService.findByID(it.id.asString)?.let { player ->
                message.answer("Welcome back, ${player.name}")
            }

            val player = playerService.save(Player(id = it.id.asString, name = it.username))
            return message.answer("Hello, ${player.name}")
        } ?: throw BotExceptions(ErrorCodes.MESSAGE_WITHOUT_AUTHOR)
    }
}


