package games.pixelfox.rpg.players

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlayerService(
    @Autowired val playerRepository: PlayerRepository
) {
    fun findByID(id: String): Player? {
        val player = playerRepository.findById(id)
        return if (player.isPresent) player.get() else null
    }

    fun save(player: Player): Player = playerRepository.save(player)
}
