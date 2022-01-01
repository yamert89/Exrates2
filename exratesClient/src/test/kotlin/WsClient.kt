import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import porokhin.exrates.core.client.ws.WebSocketClient
import porokhin.exrates.core.client.ws.WsEndpoint
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WsClient {
    @Test
    fun addEndpoints(){
        val endpoints = listOf(
            WsEndpoint("stream.binance.com", 9443, "/ws/bnbbtc@ticker", 300){
                println("input json:$it")
            },
            WsEndpoint("stream.binance.com", 9443, "/ws/etcbtc@ticker", 300){
                println("input json:$it")
            }
        )
        runBlocking {
            val job = launch(Dispatchers.IO) { WebSocketClient.runEndpoints(*endpoints.toTypedArray()) }
            delay(1000)
            val actualSize = WebSocketClient.runningEndpoints.size
            job.cancel()
            assertEquals(2, actualSize, "WebsocketClient running endpoints size: $actualSize")
        }
    }

    @Test
    fun removeEndpoints(){
        val endpoints = listOf(
            WsEndpoint("stream.binance.com", 9443, "/ws/bnbbtc@ticker", 300){
                println("input json:$it")
            },
            WsEndpoint("stream.binance.com", 9443, "/ws/etcbtc@ticker", 300){
                println("input json:$it")
            }
        )
        runBlocking {
            val job = launch(Dispatchers.IO) { WebSocketClient.runEndpoints(*endpoints.toTypedArray()) }
            delay(1000)
            WebSocketClient.removeEndpoint("stream.binance.com")
            job.join()
            assertTrue { WebSocketClient.runningEndpoints.isEmpty() }
        }
    }
}