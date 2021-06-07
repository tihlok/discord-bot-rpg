package games.pixelfox.rpg.helpers

import dev.kord.core.entity.Message

suspend inline fun Message.answer(message: String) = this.channel.createMessage(message)
