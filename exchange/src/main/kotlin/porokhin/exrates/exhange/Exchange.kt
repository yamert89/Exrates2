package porokhin.exrates.exhange

import java.util.*

interface Exchange {
    abstract val host: String
    abstract val port: Int
    val currencyPairs: List<CurrencyPair>

}
data class CurrencyPair(val symbol: String)