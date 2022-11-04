package com.mezencevsem.exchangerates.ui.screen.exchangerates.model

import com.mezencevsem.domain.model.Currency

internal sealed class ExchangeRatesScreenEvent {

    class CurrencyClicked(
        val currency: Currency
    ) : ExchangeRatesScreenEvent()

    class CurrencyFavoriteClicked(
        val currency: Currency
    ) : ExchangeRatesScreenEvent()

    object CloseDialog : ExchangeRatesScreenEvent()

    object FilterClicked : ExchangeRatesScreenEvent()

    class FilterStateChanged(
        val value: ExchangeRatesFilter
    ) : ExchangeRatesScreenEvent()

    object Reload : ExchangeRatesScreenEvent()
}