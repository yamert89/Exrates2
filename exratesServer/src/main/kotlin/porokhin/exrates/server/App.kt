package porokhin.exrates.server

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.*
import porokhin.exrates.core.client.HttpClient
import porokhin.exrates.core.client.ws.WsEndpoint
import porokhin.exrates.exchange.Binance
import porokhin.exrates.exhange.CurrencyPair
import porokhin.exrates.exhange.Exchange
import java.util.ArrayDeque

class App {
    val exchanges: List<Exchange<*>> = listOf(Binance)

    suspend fun configureEndpoints(){
        coroutineScope {
            launch { configureBinanceEndPoints() }
        }
    }


    suspend fun configureBinanceEndPoints(): List<WsEndpoint>{
        val bEndpoints = Binance.endPoints
        val response = HttpClient.get(bEndpoints.infoEndpoint)
        val pairs = Json.parseToJsonElement(response).jsonObject["symbols"]!!.jsonArray
        Binance.currencyPairs.addAll(pairs.map { CurrencyPair(it.jsonObject["symbol"]!!.jsonPrimitive.content) })
        val pref = AppPreferences
        return Binance.currencyPairs.flatMap { pair -> listOf(
            WsEndpoint(bEndpoints.wsHost, bEndpoints.port, bEndpoints.trades(pair.symbol), pref.tradesPeriod){
                Binance.rawStatistic.trades[pair]?.addFirst(it) ?: run {
                    Binance.rawStatistic.trades[pair] = ArrayDeque<String>(AppPreferences.tradesInitCapacity).apply {
                        addFirst(it)
                    }
                }
            },

            WsEndpoint(bEndpoints.wsHost, bEndpoints.port, bEndpoints.ticker(pair.symbol), pref.ticker){
                Binance.rawStatistic.ticker[pair] = it
            }).plus(

            Binance.rawStatistic.kLine.keys.map { key ->
                WsEndpoint(bEndpoints.wsHost, bEndpoints.port, bEndpoints.kLine(pair.symbol, key), pref.kLinePeriods[key]!!){
                    Binance.rawStatistic.kLine[key] = it
                }
            })
         }
    }

}