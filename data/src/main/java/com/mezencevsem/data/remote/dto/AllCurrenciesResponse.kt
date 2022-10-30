package com.mezencevsem.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * All currencies response
 *
 * @property isSuccess response success
 * @property currencies map of currencies (code, full name), ex: AED, United Arab Emirates Dirham
 */
@Serializable
internal class AllCurrenciesResponse(
    @SerialName("success")
    val isSuccess: Boolean? = null,
    @SerialName("symbols")
    val currencies: Map<String, String>? = null
)