package com.mezencevsem.domain

import com.mezencevsem.domain.model.Currency

interface ExchangeRatesInteractor {

    /**
     * Returns all available currencies
     */
    @Throws(Exception::class)
    suspend fun getAllCurrencies(): List<Currency>

    suspend fun changeFavoriteCurrency(currency: Currency)
}