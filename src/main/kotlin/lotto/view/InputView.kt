package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.domain.Winning
import lotto.util.Parser
import lotto.util.Validator

object InputView {

    fun readPurchaseMoney(): Int {
        println("구입금액을 입력해 주세요.")
        return readRetry {
            val money= Parser.parseIntStrict(Console.readLine())
            Validator.requireMoneyUnit(money)
            money
        }
    }

    fun readWinning(): Winning {
        println()
        println("당첨 번호를 입력해 주세요.")
        val main = readRetry {
            val nums = Parser.csvToIntList(Console.readLine())  //  "1,2,3,4,5,6" → [1..6]
            Validator.requireDistinct6InRange(nums)
            nums.toSet()
        }
        println()
        println("보너스 번호를 입력해 주세요.")
        val bonus = readRetry {
            val b = Parser.parseIntStrict(Console.readLine())
            Validator.requireBonusNotDuplicated(main, b)
            b
        }
        return Winning(main, bonus)
    }

    private inline fun <T> readRetry(block: () -> T): T {
        while (true) {
            try {
                return block()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}
