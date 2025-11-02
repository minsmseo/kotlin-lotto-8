package lotto

import lotto.domain.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.api.Test

class RankTest {

    @ParameterizedTest
    @CsvSource(
        "6,false,FIRST",
        "5,true,SECOND",
        "5,false,THIRD",
        "4,false,FOURTH",
        "3,false,FIFTH",
        "2,false,MISS"
    )
    @DisplayName("일치 개수와 보너스 여부에 따라 Rank.of가 올바른 등수를 반환한다")
    fun `Rank of는 일치 개수와 보너스 여부에 따라 올바른 등수를 반환한다`(
        matchCount: Int,
        bonusMatch: Boolean,
        expectedName: String
    ) {
        val result = Rank.of(matchCount, bonusMatch)
        assertThat(result.name).isEqualTo(expectedName)
    }

    @Test
    @DisplayName("Rank 상수들의 속성이 올바르게 설정되어 있다")
    fun `Rank 상수 속성 검증`() {
        val first = Rank.FIRST
        assertThat(first.matchCount).isEqualTo(6)
        assertThat(first.prize).isEqualTo(2_000_000_000L)
        assertThat(first.needsBonus).isFalse()

        val second = Rank.SECOND
        assertThat(second.matchCount).isEqualTo(5)
        assertThat(second.prize).isEqualTo(30_000_000L)
        assertThat(second.needsBonus).isTrue()
    }

    @Test
    @DisplayName("values()는 모든 Rank 인스턴스를 반환한다")
    fun `values 함수는 모든 Rank를 반환한다`() {
        val all = Rank.values()
        assertThat(all.map { it.name }).containsExactly(
            "FIRST", "SECOND", "THIRD", "FOURTH", "FIFTH", "MISS"
        )
    }
}
