package porokhin.exrates.core.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.request.*
import io.ktor.client.statement.*

object HttpClient {
    private val client = HttpClient(CIO)
    suspend fun get(url: String):HttpResponse = client.get(url)

    fun close() = client.close()
}