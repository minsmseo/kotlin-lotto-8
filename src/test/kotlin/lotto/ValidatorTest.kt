package lotto

import lotto.util.Validator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ValidatorTest {
    @Test
    fun `구입 금액 단위 검증`() {
        Validator.requireMoneyUnit(2000) // OK
        assertThrows<IllegalArgumentException> { Validator.requireMoneyUnit(1500) }
        assertThrows<IllegalArgumentException> { Validator.requireMoneyUnit(0) }
    }

    @Test
    fun `번호 검증`() {
        Validator.requireDistinct6InRange(listOf(1,2,3,4,5,6)) // OK
        assertThrows<IllegalArgumentException> { Validator.requireDistinct6InRange(listOf(1,2,3,4,5)) }
        assertThrows<IllegalArgumentException> { Validator.requireDistinct6InRange(listOf(1,1,2,3,4,5)) }
        assertThrows<IllegalArgumentException> { Validator.requireDistinct6InRange(listOf(0,2,3,4,5,6)) }
    }

    @Test
    fun `보너스 중복, 범위 검증`() {
        Validator.requireBonusNotDuplicated(setOf(1,2,3,4,5,6), 7) // OK
        assertThrows<IllegalArgumentException> { Validator.requireBonusNotDuplicated(setOf(1,2,3,4,5,6), 6) }
        assertThrows<IllegalArgumentException> { Validator.requireBonusNotDuplicated(setOf(1,2,3,4,5,6), 46) }
    }
}
