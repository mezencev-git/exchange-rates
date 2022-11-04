package com.mezencevsem.data.remote

import com.mezencevsem.data.remote.dto.AllCurrenciesResponse
import com.mezencevsem.data.remote.dto.ExchangeRatesResponse

internal interface ExchangeRatesApi {

    /**
     * Returns all available currencies
     */
    suspend fun getAllCurrencies(): AllCurrenciesResponse

    /**
     * Returns real-time exchange rate data
     *
     * @param currencyCode three-letter currency code of preferred base currency, ex: "USD"
     * @param currencies list of currency codes to limit output currencies
     */
    suspend fun getExchangeRates(
        currencyCode: String,
        currencies: List<String>
    ): ExchangeRatesResponse
}