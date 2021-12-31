package porokhin.exrates.exchange

import porokhin.exrates.exhange.*

object Binance : Exchange<BinanceEndpoints>{

    override val currencyPairs: MutableList<CurrencyPair> = mutableListOf()
    override val rawStatistic: RawStatistic = RawStatistic()
    override val endPoints = BinanceEndpoints()
}

class BinanceEndpoints: EndPoints{
    override val wsHost = "stream.binance.com"
    override val httpHost = "api.binance.com"
    override val port = 9443
    override val infoEndpoint = "$httpHost/api/v3/exchangeInfo"
    fun trades(symbol: String) = "$symbol@trade"
    fun ticker(symbol: String) = "$symbol@ticker"
    fun kLine(symbol: String, interval: String) = "$symbol@kline_$interval"
}