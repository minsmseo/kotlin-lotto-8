package lotto.view

import lotto.domain.LottoBundle
import lotto.domain.Rank

object OutputView {

    fun printBundle(bundle: LottoBundle) {
        println()
        println("${bundle.size()}개를 구매했습니다.")
        bundle.tickets().forEach { println(it.numbers()) }
    }

    fun printStats(result: Map<Rank, Int>, yieldPercent: Double) {
        println()
        println("당첨 통계")
        println("---")
        // 출력 순서: 5등 → 4등 → 3등 → 2등 → 1등
        val order = listOf("FIFTH", "FOURTH", "THIRD", "SECOND", "FIRST")
        val lines = mapOf(
            "FIFTH"  to "3개 일치 (5,000원) - %d개",
            "FOURTH" to "4개 일치 (50,000원) - %d개",
            "THIRD"  to "5개 일치 (1,500,000원) - %d개",
            "SECOND" to "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개",
            "FIRST"  to "6개 일치 (2,000,000,000원) - %d개",
        )
        for (name in order) {
            val cnt = result.entries.firstOrNull { it.key.name == name }?.value ?: 0
            println(lines.getValue(name).format(cnt))
        }
        println("총 수익률은 ${"%.1f".format(yieldPercent)}%입니다.")
    }
}
