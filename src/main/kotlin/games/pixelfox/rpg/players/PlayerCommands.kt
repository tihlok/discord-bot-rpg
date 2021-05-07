package games.pixelfox.rpg.players

import dev.kord.core.entity.Message
import games.pixelfox.rpg.exceptions.BotExceptions
import games.pixelfox.rpg.exceptions.ErrorCodes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlayerCommands(
    @Autowired val playerRepository: PlayerRepository
) {
    @Throws(BotExceptions::class)
    suspend fun start(message: Message): Message {
        return message.author?.let {
            val id = it.id.asString
            val find = playerRepository.findById(id)
            if (find.isPresent) {
                message.channel.createMessage("Welcome Back, ${find.get().name}")
            } else {
                val player = playerRepository.save(Player(id = id, name = it.username))
                message.channel.createMessage("Hello, ${player.name}")
            }
        } ?: throw BotExceptions(ErrorCodes.MESSAGE_WITHOUT_AUTHOR)
    }
}
