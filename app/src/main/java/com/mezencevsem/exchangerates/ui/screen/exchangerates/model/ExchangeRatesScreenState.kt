package com.mezencevsem.exchangerates.ui.screen.exchangerates.model

import com.mezencevsem.domain.model.Currency

internal sealed class ExchangeRatesScreenState {

    object Loading : ExchangeRatesScreenState()

    data class Content(
        val baseCurrency: Currency?,
        val currencies: List<Currency>,
        val filterState: ExchangeRatesFilter,
    ) : ExchangeRatesScreenState()

    object Error : ExchangeRatesScreenState()
}