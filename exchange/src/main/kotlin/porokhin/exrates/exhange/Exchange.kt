package porokhin.exrates.exhange


interface Exchange<T:EndPoints> {
    val currencyPairs: MutableList<CurrencyPair>
    val rawStatistic: RawStatistic
    val endPoints: T
}
data class CurrencyPair(val symbol: String)

interface EndPoints{
    val wsHost: String
    val httpHost: String
    val port: Int
    val infoEndpoint: String
}