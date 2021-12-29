package porokhin.exrates.exhange

import java.util.*

interface Exchange {
    val host: String
    val port: Int
    val currencyPairs: MutableList<CurrencyPair>
    val rawStatistic: RawStatistic

}
data class CurrencyPair(val symbol: String)