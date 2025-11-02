package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.domain.Winning

object InputView {

    fun readPurchaseMoney(): Int {
        println("구입금액을 입력해 주세요.")
        return readRetry {
            Console.readLine().trim().toIntOrNull()
                ?: throw IllegalArgumentException("[ERROR] 숫자를 입력해야 합니다.")
        }
    }

    fun readWinning(): Winning {
        println()
        println("당첨 번호를 입력해 주세요.")
        val main = readRetry {
            val line = Console.readLine().trim()
            val nums = line.split(",").map { it.trim() }
            if (nums.size != 6) throw IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.")
            val ints = nums.map {
                it.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 숫자만 입력해야 합니다.")
            }
            ints.toSet()
        }
        println()
        println("보너스 번호를 입력해 주세요.")
        val bonus = readRetry {
            Console.readLine().trim().toIntOrNull()
                ?: throw IllegalArgumentException("[ERROR] 숫자만 입력해야 합니다.")
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
