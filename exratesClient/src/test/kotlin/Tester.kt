import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.runBlocking
import porokhin.exrates.core.client.ws.WebSocketClient

fun main() {
    val client = HttpClient(CIO){install(WebSockets)}
    runBlocking {
        client.wss(HttpMethod.Get, "stream.binance.com", 9443, "/stream"){
            send("""{
                             "method": "SUBSCRIBE",
                             "params":
                             [
                             "btcusdt@aggTrade",
                             "btcusdt@depth"
                             ],
                             "id": 1
                            }""")
            ponger(outgoing)
            while (true){
                val frame = incoming.receive() as Frame.Text
                val response = frame.readText()
                println(response)
            }
        }
    }


}