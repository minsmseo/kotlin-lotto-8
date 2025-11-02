package lotto.domain

class Winning(
    private val winningNumbers: Set<Int>,
    private val bonusNumber: Int
) {
    init {
        require(winningNumbers.size == 6) { "[ERROR] 당첨 번호는 6개여야 합니다." }
        require(bonusNumber in 1..45) { "[ERROR] 보너스 번호는 1~45 사이여야 합니다." }
        require(bonusNumber !in winningNumbers) { "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다." }
    }

    fun matchCount(ticket: LottoTicket): Int =
        ticket.numbers().count { it in winningNumbers }

    fun isBonusMatch(ticket: LottoTicket): Boolean =
        bonusNumber in ticket.numbers()

    fun rankOf(ticket: LottoTicket): Rank {
        val count = matchCount(ticket)
        val bonus = isBonusMatch(ticket)
        return Rank.of(count, bonus)
    }
}
