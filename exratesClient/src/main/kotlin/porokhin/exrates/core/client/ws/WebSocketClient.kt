package porokhin.exrates.core.client.ws

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.*

object WebSocketClient {
    private val endpoints: MutableMap<WsEndpoint, Job> = mutableMapOf()
    private val client = HttpClient(CIO) {
        install(WebSockets)
    }
    public val runningEndpoints = endpoints.keys

    suspend fun runEndpoints(vararg endpoint: WsEndpoint) {
        coroutineScope {
            endpoint.forEach { endpoint ->
                endpoints[endpoint] = launch {
                    with(endpoint){
                        val block: suspend DefaultClientWebSocketSession.() -> Unit = {
                            var lastResponse = 0L
                            while (true){
                                val frame = incoming.receive() as Frame.Text
                                if (System.currentTimeMillis() - timeInterval > lastResponse) {
                                    endpoint.block(frame.readText())
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
        }
    }
    fun removeEndpoint(host: String){
        val endpts = endpoints.filter { it.key.host == host }
        endpts.forEach {
            it.value.cancel()
            endpoints.remove(it.key)
        }
    }

}