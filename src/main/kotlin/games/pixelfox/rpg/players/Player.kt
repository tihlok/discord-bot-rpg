package games.pixelfox.rpg.players

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Player(
    @Id val id: String,
    val name: String
) {
    override fun toString(): String = "Player(id='$id', name='$name')"
}
