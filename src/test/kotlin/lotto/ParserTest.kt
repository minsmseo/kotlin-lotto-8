package lotto

import lotto.util.Parser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParserTest {
    @Test
    fun `csvToIntList 정상 파싱`() {
        val res = Parser.csvToIntList("1, 2,3, 4,5,6")
        assertThat(res).containsExactly(1,2,3,4,5,6)
    }

    @Test
    fun `csvToIntList 숫자 외 입력시 예외`() {
        assertThrows<IllegalArgumentException> { Parser.csvToIntList("1, a, 3, 4, 5, 6") }
    }

    @Test
    fun `parseIntStrict 정상 파싱`() {
        val v = Parser.parseIntStrict(" 8000 ")
        assertThat(v).isEqualTo(8000)
    }

    @Test
    fun `parseIntStrict 숫자 외 입력시 예외`() {
        assertThrows<IllegalArgumentException> { Parser.parseIntStrict("eight thousand") }
    }
}
