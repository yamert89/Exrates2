package porokhin.exrates.server

import porokhin.exrates.core.client.ws.WsEndpoint
import porokhin.exrates.exchange.Binance
import porokhin.exrates.exhange.Exchange

class App {
    val exchanges: List<Exchange<*>> = listOf(Binance)

    suspend fun configureEndpoints(){
        exchanges.forEach {

        }
    }


    fun configureBinanceEndPoints(): List<WsEndpoint>{
        val bEndpoints = Binance.endPoints
        return listOf(
            //WsEndpoint(bEndpoints.host, bEndpoints.port, bEndpoints.trades())
        )
    }

}