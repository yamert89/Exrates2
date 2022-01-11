package porokhin.exrates.core.client.ws

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.websocket.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicInteger

object WebSocketClient {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val endpoints: MutableMap<WsEndpoint, Job> = mutableMapOf()
    private val client = HttpClient(CIO) { install(WebSockets) }
    val runningEndpoints = endpoints.keys
    private val counter = AtomicInteger(0)

    suspend fun runEndpoints(vararg endpoint: WsEndpoint) {
        coroutineScope {
            endpoint.forEach { endpoint ->
                endpoints[endpoint] = launch(Dispatchers.Default) {
                    with(endpoint){
                        val block: suspend DefaultClientWebSocketSession.() -> Unit = {
                            var lastResponse = 0L
                            logger.debug("start ${counter.incrementAndGet()} - ${endpoint.path} coroutine")
                            ponger(outgoing)
                            while (true){
                                val frame = incoming.receive() as Frame.Text
                                //logger.debug("frame received with ${frame.data.size}")
                                if (System.currentTimeMillis() - timeInterval > lastResponse) {
                                    val response = frame.readText()
                                    logger.debug("Response of url: ${this.call.request.url}\n$response")
                                    endpoint.block(response)
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
            logger.debug("${endpoint.size} endpoints added to WSClient")
        }

    }
    fun removeEndpoint(host: String){
        val someHostEndpoints = endpoints.filter { it.key.host == host }
        someHostEndpoints.forEach {
            it.value.cancel()
            endpoints.remove(it.key)
        }
    }

}