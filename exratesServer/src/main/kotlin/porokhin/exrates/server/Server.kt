package porokhin.exrates.server

import io.ktor.server.engine.*
import io.ktor.server.netty.*

object Server {
    fun start(){
        embeddedServer(Netty, port = 8080, host = "0.0.0.0"){

        }.start(true)
    }
}