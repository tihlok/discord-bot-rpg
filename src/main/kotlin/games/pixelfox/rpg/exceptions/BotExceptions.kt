package games.pixelfox.rpg.exceptions

enum class ErrorCodes(val code: Int) {
    MESSAGE_WITHOUT_AUTHOR(1000)
}

class BotExceptions(error: ErrorCodes) : Exception("failed: ${error.code}")
