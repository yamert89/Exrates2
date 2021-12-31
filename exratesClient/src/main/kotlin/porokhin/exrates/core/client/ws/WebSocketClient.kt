package porokhin.exrates.core.client.ws

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

object WebSocketClient {
    private val endpoints: MutableList<WsEndpoint> = mutableListOf()
    init {

    }
    suspend fun run(){
        val client = HttpClient(CIO) {
            install(WebSockets)
        }
        runBlocking {
            endpoints.forEach {
                with(it){
                    val block: suspend DefaultClientWebSocketSession.() -> Unit = {
                        var lastResponse = 0L
                        while (true){
                            val frame = incoming.receive() as Frame.Text
                            if (System.currentTimeMillis() - timeInterval > lastResponse) {
                                println(frame.readText())
                                lastResponse = System.currentTimeMillis()
                            }
                        }
                        
                    }
                    // todo val request =
                    if (security) client.wss(method, host, port, path, block = block)
                    else client.webSocket(method, host, port, path, block = block)
                }
            }
        }
        client.close()
    }

    fun addEndpoints(vararg endpoint: WsEndpoint) {

        endpoints.addAll(endpoint)
    }
    fun removeEndpoint(host: String) = endpoints.removeIf { it.host == host }

}