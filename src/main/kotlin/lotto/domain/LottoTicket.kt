package lotto.domain

class LottoTicket (private val lotto: Lotto){
    fun numbers(): List<Int> = lotto.numbers()
    companion object{
        fun of(numbers: List<Int>): LottoTicket{
            val sorted = numbers.sorted()
            return LottoTicket(Lotto(sorted))
        }
    }
}