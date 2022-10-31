package com.mezencevsem.data.remote

import com.mezencevsem.data.remote.dto.AllCurrenciesResponse
import com.mezencevsem.data.remote.dto.ExchangeRatesResponse
import io.ktor.client.HttpClient

internal class ExchangeRatesApiImpl(
    private val client: HttpClient
) : ExchangeRatesApi {

    override suspend fun getAllCurrencies(): AllCurrenciesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getExchangeRates(currencyCode: String): ExchangeRatesResponse {
        TODO("Not yet implemented")
    }
}