package porokhin.exrates.exchange

import porokhin.exrates.exhange.*

class Binance : Exchange<BinanceEndpoints>{

    override val currencyPairs: MutableList<CurrencyPair> = mutableListOf()
    override val rawStatistic: RawStatistic = RawStatistic()
    override val endPoints = BinanceEndpoints()
}
class BinanceEndpoints: EndPoints{
    override val host = "stream.binance.com"
    override val port = 9443
    fun trades(symbol: String) = "$symbol@trade"
    fun ticker(symbol: String) = "$symbol@ticker"
    fun kLine(symbol: String, interval: String) = "$symbol@kline_$interval"
}