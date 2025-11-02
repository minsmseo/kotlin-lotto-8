package lotto.util

import kotlin.math.round

object MathUtil {
    // (총당첨금 / 지출) * 100, 소수점 첫째 자리 반올림 (예: 166.7)
    fun yieldPercent(totalPrize: Long, spent: Int): Double {
        if (spent <= 0) return 0.0
        val raw = totalPrize.toDouble() / spent.toDouble() * 100.0
        return round(raw * 10) / 10.0
    }
}
