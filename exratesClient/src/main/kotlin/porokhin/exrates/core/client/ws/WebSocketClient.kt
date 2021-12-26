package porokhin.exrates.core.client.ws

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.runBlocking

fun main() {
    WebSocketClient(mutableListOf(WsEndpoint("stream.binance.com", 9443, "ws/btcusdt@depth"))).start()
}


class WebSocketClient(private val endpoints: MutableList<WsEndpoint>) {
    fun start(){
        val client = HttpClient(CIO) {
            install(WebSockets)
        }
        runBlocking {
            endpoints.forEach {
                with(it){
                    val block: suspend DefaultClientWebSocketSession.() -> Unit = {
                        while (true){
                            println((incoming.receive() as Frame.Text).readText())
                        }
                        
                    }
                    // todo val request =
                    if (security) client.wss(method, host, port, path, block = block)
                    else client.webSocket(method, host, port, path, block = block)
                }
            }




            /*client.webSocket(method = HttpMethod.Get, host = "wss://stream.binance.com", port = 9443, path = "/ws/btcusdt@depth" ){
                *//*while (true){
                    try {
                        val frame = incoming.receive() as Frame.Text
                        println(frame.readText())
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }*//*
            }*/
        }
        client.close()
    }

    fun addEndpoint(endpoint: WsEndpoint) = endpoints.add(endpoint)
    fun removeEndpoint(host: String) = endpoints.removeIf { it.host == host }

}