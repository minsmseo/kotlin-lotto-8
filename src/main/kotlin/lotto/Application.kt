package lotto

import lotto.service.LottoMachine
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val machine = LottoMachine()

    val money = InputView.readPurchaseMoney()
    val bundle = machine.buy(money)
    OutputView.printBundle(bundle)

    val winning = InputView.readWinning()
    val result = machine.calculate(bundle, winning)
    val yieldPercent = machine.yieldRate(result, money)

    OutputView.printStats(result, yieldPercent)
}
