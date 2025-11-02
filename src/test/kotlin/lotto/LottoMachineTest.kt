package lotto

import lotto.service.LottoMachine
import lotto.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {

    @Test
    fun `구입 금액에 따라 로또가 N장 발행된다`() {
        val machine = LottoMachine()
        val bundle = machine.buy(8000)
        assertThat(bundle.size()).isEqualTo(8)
    }

    @Test
    fun `당첨 결과를 등수별로 집계할 수 있다`() {
        val machine = LottoMachine()
        val tickets = listOf(
            LottoTicket.of(listOf(1,2,3,4,5,6)), // 1등
            LottoTicket.of(listOf(1,2,3,4,5,7)), // 2등
            LottoTicket.of(listOf(1,2,3,4,5,8)), // 3등
            LottoTicket.of(listOf(1,2,3,4,9,10)), // 4등
            LottoTicket.of(listOf(1,2,3,11,12,13)), // 5등
            LottoTicket.of(listOf(10,11,12,13,14,15)) // 꽝
        )
        val bundle = LottoBundle(tickets)
        val winning = Winning(setOf(1,2,3,4,5,6), 7)

        val result = machine.calculate(bundle, winning)

        // Rank가 클래스 상수라면 name 또는 참조로 확인
        assertThat(result[Rank.FIRST]).isEqualTo(1)
        assertThat(result[Rank.SECOND]).isEqualTo(1)
        assertThat(result[Rank.THIRD]).isEqualTo(1)
        assertThat(result[Rank.FOURTH]).isEqualTo(1)
        assertThat(result[Rank.FIFTH]).isEqualTo(1)
        assertThat(result[Rank.MISS]).isEqualTo(1)
    }

    @Test
    fun `수익률을 소수점 첫째 자리까지 반올림한다`() {
        val machine = LottoMachine()
        val result = mapOf(
            Rank.FIFTH to 1, // 5,000
            Rank.MISS to 2
        )
        val rate = machine.yieldRate(result, 3000) // 5000/3000*100 = 166.666... -> 166.7
        assertThat(rate).isEqualTo(166.7)
    }
}
