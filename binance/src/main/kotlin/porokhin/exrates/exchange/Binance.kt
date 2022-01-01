package porokhin.exrates.exchange

import porokhin.exrates.exhange.*

object Binance : Exchange<BinanceEndpoints>{

    override val currencyPairs: MutableList<CurrencyPair> = mutableListOf()
    override val rawStatistic: RawStatistic = RawStatistic().apply {
        kLine.putAll(listOf("1m", "3m", "5m", "15m", "30m", "1h", "2h",
            "4h", "6h", "8h", "12h", "1d", "3d", "1w", "1M").associateWith { "" })
    }

    override val endPoints = BinanceEndpoints()
}

class BinanceEndpoints: EndPoints{
    override val wsHost = "stream.binance.com/"
    override val httpHost = "https://api.binance.com"
    override val port = 9443
    override val infoEndpoint = "$httpHost/api/v3/exchangeInfo"
    private val rawSuffix = "ws/"
    fun trades(symbol: String) = "$rawSuffix$symbol@trade"
    fun ticker(symbol: String) = "$rawSuffix$symbol@ticker"
    fun kLine(symbol: String, interval: String) = "$rawSuffix$symbol@kline_$interval"
}

/*
* {
  "timezone": "UTC",
  "serverTime": 1565246363776,
  "rateLimits": [
    {
      //These are defined in the `ENUM definitions` section under `Rate Limiters (rateLimitType)`.
      //All limits are optional
    }
  ],
  "exchangeFilters": [
    //These are the defined filters in the `Filters` section.
    //All filters are optional.
  ],
  "symbols": [
    {
      "symbol": "ETHBTC",
      "status": "TRADING",
      "baseAsset": "ETH",
      "baseAssetPrecision": 8,
      "quoteAsset": "BTC",
      "quotePrecision": 8,
      "quoteAssetPrecision": 8,
      "orderTypes": [
        "LIMIT",
        "LIMIT_MAKER",
        "MARKET",
        "STOP_LOSS",
        "STOP_LOSS_LIMIT",
        "TAKE_PROFIT",
        "TAKE_PROFIT_LIMIT"
      ],
      "icebergAllowed": true,
      "ocoAllowed": true,
      "isSpotTradingAllowed": true,
      "isMarginTradingAllowed": true,
      "filters": [
        //These are defined in the Filters section.
        //All filters are optional
      ],
      "permissions": [
         "SPOT",
         "MARGIN"
      ]
    }
  ]
}*/