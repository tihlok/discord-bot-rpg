package games.pixelfox.rpg.players

import org.springframework.stereotype.Service

@Service
class PlayerService(private val players: PlayerRepository) {
    fun save(player: Player): Player = players.save(player)

    fun all(): List<Player> = players.findAll()
}
