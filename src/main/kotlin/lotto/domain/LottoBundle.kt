package lotto.domain

class LottoBundle(private val tickets: List<LottoTicket>) {
    fun size(): Int = tickets.size
    fun tickets():List<LottoTicket> = tickets
}