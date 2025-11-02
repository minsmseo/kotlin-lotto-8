package lotto.service

import camp.nextstep.edu.missionutils.Randoms
import lotto.domain.*

class LottoMachine {

    fun buy(money: Int): LottoBundle {
        validateMoney(money)
        val count = money / 1000
        val tickets = (1..count).map { issueTicket() }
        return LottoBundle(tickets)
    }

    fun calculate(bundle: LottoBundle, winning: Winning): Map<Rank, Int> {
        val counts = mutableMapOf<Rank, Int>().withDefault { 0 }
        for (t in bundle.tickets()) {
            val rank = winning.rankOf(t)
            counts[rank] = counts.getValue(rank) + 1
        }
        return counts
    }

    fun yieldRate(result: Map<Rank, Int>, spent: Int): Double {
        if (spent <= 0) return 0.0
        val income = result.entries.sumOf { (rank, cnt) -> rank.prize * cnt }
        // 소수점 둘째 자리 반올림(예: 62.5)
        val raw = income.toDouble() / spent.toDouble() * 100.0
        return kotlin.math.round(raw * 10) / 10.0
    }

    private fun issueTicket(): LottoTicket {
        val nums = Randoms.pickUniqueNumbersInRange(1, 45, 6)
        return LottoTicket.of(nums)
    }

    private fun validateMoney(money: Int) {
        require(money >= 1000 && money % 1000 == 0) {
            "[ERROR] 구입 금액은 1,000원 단위의 양수여야 합니다."
        }
    }
}
