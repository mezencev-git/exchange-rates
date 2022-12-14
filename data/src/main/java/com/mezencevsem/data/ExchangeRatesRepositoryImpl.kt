package com.mezencevsem.data

import com.mezencevsem.data.local.CurrenciesDatabase
import com.mezencevsem.data.remote.ExchangeRatesApi
import com.mezencevsem.domain.model.Currency
import com.mezencevsem.data.local.entity.Currency as CurrencyModel

internal class ExchangeRatesRepositoryImpl(
    private val database: CurrenciesDatabase,
    private val api: ExchangeRatesApi
) : ExchangeRatesRepository {

    override suspend fun getAllCurrencies(): List<Currency> {
        val currencies = api.getAllCurrencies().currencies
            ?: throw Exception("Empty currencies map")

        database.currenciesDao().addCurrencies(
            currencies = currencies.map {
                CurrencyModel(
                    code = it.key,
                    name = it.value
                )
            }
        )

        return database.currenciesDao().getCurrencies().map {
            Currency(
                code = it.code,
                name = it.name,
                isFavorite = it.isFavorite
            )
        }
    }

    override suspend fun changeFavoriteCurrency(currency: Currency) {
        database.currenciesDao().updateCurrency(
            currency = CurrencyModel(
                code = currency.code,
                name = currency.name,
                isFavorite = !currency.isFavorite
            )
        )
    }

    override suspend fun getCurrenciesWithRates(
        baseCurrency: Currency
    ): List<Currency> {
        val response = api.getExchangeRates(
            currencyCode = baseCurrency.code,
            currencies = database.currenciesDao().getCurrencies().map { it.code }
        )

        val currencies = database.currenciesDao().getCurrencies()

        if (!response.rates.isNullOrEmpty()) {
            return currencies.map {
                Currency(
                    code = it.code,
                    name = it.name,
                    rate = response.rates[it.code],
                    isFavorite = it.isFavorite
                )
            }.filter {
                it.rate != null
            }
        } else throw Exception("Rates map is empty")
    }
}