package com.mezencevsem.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Exchange rate response
 *
 * @property isSuccess response success
 * @property baseCurrencyCode three-letter currency code of preferred base currency, ex: "USD"
 * @property date date of response
 * @property timestamp timestamp of response
 * @property rates map of rates (code, rate), ex: EUR, 0.813399
 */
@Serializable
internal class ExchangeRatesResponse(
    @SerialName("success")
    val isSuccess: Boolean? = null,
    @SerialName("base")
    val baseCurrencyCode: String? = null,
    //TODO add serializer
    @SerialName("date")
    val date: String? = null,
    @SerialName("timestamp")
    val timestamp: Long? = null,
    @SerialName("rates")
    val rates: Map<String, Float>? = null
)