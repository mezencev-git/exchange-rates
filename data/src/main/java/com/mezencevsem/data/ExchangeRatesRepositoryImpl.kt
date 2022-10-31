package com.mezencevsem.data

import com.mezencevsem.data.local.CurrenciesDatabase
import com.mezencevsem.data.remote.ExchangeRatesApi

internal class ExchangeRatesRepositoryImpl(
    private val database: CurrenciesDatabase,
    private val api: ExchangeRatesApi
) : ExchangeRatesRepository {

}