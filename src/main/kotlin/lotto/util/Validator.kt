package lotto.util

object Validator {
    fun requireMoneyUnit(money: Int) {
        require(money >= 1000 && money % 1000 == 0) {
            "[ERROR] 구입 금액은 1,000원 단위의 양수여야 합니다."
        }
    }

    // 도메인에서도 동일 검증을 하므로 “사전방어용” 보조 검증 (최종 권위는 도메인)
    fun requireDistinct6InRange(nums: List<Int>) {
        require(nums.size == 6) { "[ERROR] 로또 번호는 6개여야 합니다." }
        require(nums.distinct().size == 6) { "[ERROR] 로또 번호는 중복될 수 없습니다." }
        require(nums.all { it in 1..45 }) { "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다." }
    }

    fun requireBonusNotDuplicated(main: Set<Int>, bonus: Int) {
        require(bonus in 1..45) { "[ERROR] 보너스 번호는 1~45 사이여야 합니다." }
        require(bonus !in main) { "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다." }
    }
}
