package porokhin.exrates.server

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import porokhin.exrates.core.client.HttpClient
import porokhin.exrates.core.client.ws.WebSocketClient
import porokhin.exrates.core.client.ws.WsEndpoint
import kotlin.math.log

fun main() = runBlocking{
    val logger = LoggerFactory.getLogger("Main")
    val app = App()
    val binanceEndpoints = app.configureBinanceEndPoints()
    logger.debug("Binance have ${binanceEndpoints.size} ws endpoints")
    val wsClient = WebSocketClient
    wsClient.runEndpoints(*binanceEndpoints.subList(0, 1200).toTypedArray())

}




