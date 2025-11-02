package lotto.util

object Parser {
    fun parseIntStrict(line: String): Int =
        line.trim().toIntOrNull()
            ?: throw IllegalArgumentException("[ERROR] 숫자를 입력해야 합니다.")

    fun csvToIntList(line: String): List<Int> {
        val tokens = line.split(",").map { it.trim() }
        if (tokens.any { it.isEmpty() }) throw IllegalArgumentException("[ERROR] 비어 있는 값이 있습니다.")
        return tokens.map {
            it.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 숫자만 입력해야 합니다.")
        }
    }
}
