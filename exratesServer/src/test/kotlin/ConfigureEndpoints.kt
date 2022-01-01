import kotlinx.coroutines.runBlocking
import org.junit.Test
import porokhin.exrates.exchange.Binance
import porokhin.exrates.server.App
import kotlin.test.assertEquals

class ConfigureEndpoints {
    @Test
    fun configureBinance() {
        val app = App()
        runBlocking {
           val endPoints = app.configureBinanceEndPoints()
            assertEquals(17 * Binance.currencyPairs.size , endPoints.size,
                "size of Binance endpoints ${endPoints.size}")
        }

    }
}