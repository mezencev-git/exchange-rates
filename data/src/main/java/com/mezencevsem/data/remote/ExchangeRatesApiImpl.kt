package com.mezencevsem.data.remote

import com.mezencevsem.data.remote.dto.AllCurrenciesResponse
import com.mezencevsem.data.remote.dto.ExchangeRatesResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter

private const val BASE_ENDPOINT = "https://api.apilayer.com/exchangerates_data"
private const val API_KEY_HEADER_KEY = "apikey"
private const val API_KEY_VALUE = "mHiYBPjxsejaNklwcaA28rPXZFcKHuOT"

internal class ExchangeRatesApiImpl(
    private val client: HttpClient
) : ExchangeRatesApi {

    override suspend fun getAllCurrencies(): AllCurrenciesResponse {
        return client.get("$BASE_ENDPOINT/symbols") {
            header(API_KEY_HEADER_KEY, API_KEY_VALUE)
        }
    }

    override suspend fun getExchangeRates(
        currencyCode: String,
        currencies: List<String>
    ): ExchangeRatesResponse {
        return client.get("$BASE_ENDPOINT/latest") {
            header(API_KEY_HEADER_KEY, API_KEY_VALUE)
            parameter("base", currencyCode)
            parameter("symbols", currencies)
        }
    }
}