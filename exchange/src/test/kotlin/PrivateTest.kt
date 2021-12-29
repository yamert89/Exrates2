import org.junit.Test
import porokhin.exrates.exhange.RawStatistic
import kotlin.test.assertEquals

class PrivateTest{
    @Test
    fun extractPeriod(){
        val inputStrings = listOf("3h", "3m", "15d", "2h", "1M")
        val kline = RawStatistic().kLine.apply { putAll(inputStrings.associateWith { "" })}
        assertEquals("3m", kline.firstKey(), "First Kline key is ${kline.firstKey()}")
        assertEquals("1M", kline.lastKey(), "Last kline key is ${kline.lastKey()}")
    }
}


