package porokhin.exrates.server

import org.slf4j.LoggerFactory

object AppPreferences {
    private val logger = LoggerFactory.getLogger(this::class.java)
    const val ticker = 3600000L
    const val tradesPeriod = 5000L
    private const val kLine1mPeriod = 60000L
    private var kLineStartCoefficient = 0.5f
    val kLinePeriods = mapOf<String, Long>(
        "1m" to kLine1mPeriod,
        "3m" to (kLine1mPeriod * 3 * kLineCoefficient()).toLong(),
        "5m" to (kLine1mPeriod * 5 * kLineCoefficient()).toLong(),
        "15m" to (kLine1mPeriod * 15 * kLineCoefficient()).toLong(),
        "30m" to (kLine1mPeriod * 30 * kLineCoefficient()).toLong(),
        "1h" to (kLine1mPeriod * 60 * kLineCoefficient()).toLong(),
        "2h" to (kLine1mPeriod * 60 * 2 * kLineCoefficient()).toLong(),
        "4h" to (kLine1mPeriod * 60 * 4 * kLineCoefficient()).toLong(),
        "6h" to (kLine1mPeriod * 60 * 6 * kLineCoefficient()).toLong(),
        "8h" to (kLine1mPeriod * 60 * 8 * kLineCoefficient()).toLong(),
        "12h" to (kLine1mPeriod * 60 * 12 * kLineCoefficient()).toLong(),
        "1d" to (kLine1mPeriod * 60 * 24 * kLineCoefficient()).toLong(),
        "3d" to (kLine1mPeriod * 60 * 24 * 3 * kLineCoefficient()).toLong(),
        "1w" to (kLine1mPeriod * 60 * 24 * 7 * kLineCoefficient()).toLong(),
        "1M" to (kLine1mPeriod * 60 * 24 * 30 * kLineCoefficient().toLong())
    )
    init {
        kLinePeriods.forEach{logger.debug("kline period for ${it.key} = ${it.value}")}
    }
    private fun kLineCoefficient(): Float {
        kLineStartCoefficient -= ((kLineStartCoefficient - 0.1f) / 15)
        return kLineStartCoefficient
    }
}