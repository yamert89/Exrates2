package porokhin.exrates.core.client.ws

import io.ktor.http.*

class WsEndpoint(
    val host: String,
    val port: Int,
    val path: String = "/",
    val method: HttpMethod = HttpMethod.Get,
    val security: Boolean = true) {
}