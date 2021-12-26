package porokhin.exrates.core.client.ws

import io.ktor.http.*

class WsEndpoint(
    val host: String,
    val port: Int,
    val path: String = "/",
    val timeInterval: Long = 0,
    val method: HttpMethod = HttpMethod.Get,
    val security: Boolean = true) {
}