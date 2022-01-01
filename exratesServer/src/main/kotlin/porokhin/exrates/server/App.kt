package porokhin.exrates.server

import kotlinx.serialization.json.*
import porokhin.exrates.core.client.HttpClient
import porokhin.exrates.core.client.ws.WsEndpoint
import porokhin.exrates.exchange.Binance
import porokhin.exrates.exhange.CurrencyPair
import porokhin.exrates.exhange.Exchange

class App {
    val exchanges: List<Exchange<*>> = listOf(Binance)

    suspend fun configureEndpoints(){
        exchanges.forEach {

        }
    }


    suspend fun configureBinanceEndPoints(): List<WsEndpoint>{
        val bEndpoints = Binance.endPoints
        val response = HttpClient.get(bEndpoints.infoEndpoint)
        val pairs = Json.parseToJsonElement(response).jsonObject["symbols"]!!.jsonArray
        Binance.currencyPairs.addAll(pairs.map { CurrencyPair(it.jsonObject["symbol"]!!.jsonPrimitive.content) })
        val pref = AppPreferences

        return Binance.currencyPairs.flatMap { pair -> listOf(
            WsEndpoint(bEndpoints.wsHost, bEndpoints.port, bEndpoints.trades(pair.symbol), pref.tradesPeriod),
            WsEndpoint(bEndpoints.wsHost, bEndpoints.port, bEndpoints.ticker(pair.symbol), pref.ticker)).plus(
            Binance.rawStatistic.kLine.keys.map {
                WsEndpoint(bEndpoints.wsHost, bEndpoints.port, bEndpoints.kLine(pair.symbol, it), pref.kLinePeriods[it]!! )
            })
         }
    }

}