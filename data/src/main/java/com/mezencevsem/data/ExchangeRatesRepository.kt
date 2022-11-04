package com.mezencevsem.data

import com.mezencevsem.domain.model.Currency

interface ExchangeRatesRepository {

    /**
     * Returns all available currencies
     */
    @Throws(Exception::class)
    suspend fun getAllCurrencies(): List<Currency>

    suspend fun changeFavoriteCurrency(currency: Currency)
}