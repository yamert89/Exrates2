package porokhin.exrates.server

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import porokhin.exrates.core.client.HttpClient
import porokhin.exrates.core.client.ws.WebSocketClient
import porokhin.exrates.core.client.ws.WsEndpoint

fun main(){
    val app = App()
    val httpClient = HttpClient
    val wsClient = WebSocketClient


}




