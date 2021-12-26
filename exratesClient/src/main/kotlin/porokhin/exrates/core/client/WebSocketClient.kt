package porokhin.exrates.core.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.utils.io.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.Exception

fun main() {
    WebSocketClient().start()
}

class WebSocketClient {
    fun start(){
        val client = HttpClient(CIO) {
            install(WebSockets)
        }
        runBlocking {


            client.wss(HttpMethod.Get, "stream.binance.com", 9443, "ws/btcusdt@depth"){
                println((this.incoming.receive() as Frame.Text).readText())

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
    }
}