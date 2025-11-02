package lotto

import lotto.domain.LottoTicket
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTicketTest {

    @Test
    fun `of로 생성 시 번호는 오름차순으로 정렬되어 노출된다`() {
        val ticket = LottoTicket.of(listOf(45, 1, 30, 10, 5, 20))
        assertThat(ticket.numbers()).containsExactly(1, 5, 10, 20, 30, 45)
    }

    @Test
    fun `6개가 아니면 예외`() {
        assertThrows<IllegalArgumentException> {
            LottoTicket.of(listOf(1, 2, 3, 4, 5))
        }
    }

    @Test
    fun `중복이 있으면 예외`() {
        assertThrows<IllegalArgumentException> {
            LottoTicket.of(listOf(1, 1, 2, 3, 4, 5))
        }
    }

    @Test
    fun `범위를 벗어나면 예외`() {
        assertThrows<IllegalArgumentException> {
            LottoTicket.of(listOf(0, 2, 3, 4, 5, 6))
        }
        assertThrows<IllegalArgumentException> {
            LottoTicket.of(listOf(1, 2, 3, 4, 5, 46))
        }
    }
}
