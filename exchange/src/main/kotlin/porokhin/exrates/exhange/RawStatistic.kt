package porokhin.exrates.exhange

import java.util.*

class RawStatistic {
    val trades: Map<CurrencyPair, List<String>> = mutableMapOf()
    val ticker: Map<CurrencyPair, String> = mutableMapOf()
    val kLine: SortedMap<String, String> = TreeMap{o1, o2 ->
        if (o1 == o2) 0
        else {
            val p1 = o1.extractPeriod()
            val p2 = o2.extractPeriod()
            val times = "mhdwM"
            val idx1 = times.indexOf(p1.second)
            val idx2 = times.indexOf(p2.second)
            if (idx1 != idx2) idx1 - idx2
            else p1.first - p2.first
        }
    }


    private fun String.extractPeriod(): Pair<Int, String>{
        val regex = """(\d{1,2})(\w)""".toRegex()
        val values = regex.matchEntire(this)!!.groupValues
        return values[1].toInt() to values[2]
    }
}