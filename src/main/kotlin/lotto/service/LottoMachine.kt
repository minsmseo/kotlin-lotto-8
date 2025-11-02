package lotto.service

import camp.nextstep.edu.missionutils.Randoms
import lotto.domain.*
import lotto.util.MathUtil
import lotto.util.Validator

class LottoMachine {

    fun buy(money: Int): LottoBundle {
        Validator.requireMoneyUnit(money)          // ✅ util 가드
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
        val income: Long = result.entries.sumOf { (rank, cnt) -> rank.prize * cnt }
        return MathUtil.yieldPercent(income, spent)            // ✅ util 위임
    }

    private fun issueTicket(): LottoTicket {
        val nums = Randoms.pickUniqueNumbersInRange(1, 45, 6)
        return LottoTicket.of(nums) // Lotto가 최종 검증/정렬
    }
}
