package porokhin.exrates.exchange

import porokhin.exrates.exhange.CurrencyPair
import porokhin.exrates.exhange.Exchange
import porokhin.exrates.exhange.RawStatistic

class Binance : Exchange{
    override val host = "stream.binance.com"
    override val port = 9443
    override val currencyPairs: MutableList<CurrencyPair> = mutableListOf()
    override val rawStatistic: RawStatistic = RawStatistic()
}