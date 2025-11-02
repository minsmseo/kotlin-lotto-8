package lotto.domain

class Rank private constructor(
    val name: String,
    val matchCount: Int,
    val prize: Long,
    val needsBonus: Boolean = false
) {

    companion object {
        val FIRST = Rank("FIRST", 6, 2_000_000_000L)
        val SECOND = Rank("SECOND", 5, 30_000_000L, true)
        val THIRD = Rank("THIRD", 5, 1_500_000L)
        val FOURTH = Rank("FOURTH", 4, 50_000L)
        val FIFTH = Rank("FIFTH", 3, 5_000L)
        val MISS = Rank("MISS", 0, 0L)

        private val allRanks = listOf(FIRST, SECOND, THIRD, FOURTH, FIFTH, MISS)

        fun of(matchCount: Int, bonusMatch: Boolean): Rank {
            return when {
                matchCount == 6 -> FIRST
                matchCount == 5 && bonusMatch -> SECOND
                matchCount == 5 -> THIRD
                matchCount == 4 -> FOURTH
                matchCount == 3 -> FIFTH
                else -> MISS
            }
        }

        fun values(): List<Rank> = allRanks
    }

    override fun toString(): String = name
}
