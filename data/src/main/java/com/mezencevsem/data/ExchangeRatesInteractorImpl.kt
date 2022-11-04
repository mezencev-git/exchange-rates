package com.mezencevsem.data

import com.mezencevsem.domain.ExchangeRatesInteractor
import com.mezencevsem.domain.model.Currency

class ExchangeRatesInteractorImpl(
    private val repository: ExchangeRatesRepository
) : ExchangeRatesInteractor {

    override suspend fun getAllCurrencies(): List<Currency> {
        return repository.getAllCurrencies()
    }

    override suspend fun changeFavoriteCurrency(currency: Currency) {
        repository.changeFavoriteCurrency(currency)
    }

    override suspend fun getCurrenciesWithRates(
        baseCurrency: Currency
    ): List<Currency> {
        return repository.getCurrenciesWithRates(baseCurrency)
    }
}