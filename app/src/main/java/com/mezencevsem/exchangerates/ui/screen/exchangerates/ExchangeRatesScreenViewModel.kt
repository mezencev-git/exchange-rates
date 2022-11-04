package com.mezencevsem.exchangerates.ui.screen.exchangerates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mezencevsem.domain.ExchangeRatesInteractor
import com.mezencevsem.domain.model.Currency
import com.mezencevsem.exchangerates.ui.screen.exchangerates.model.ExchangeRatesFilter
import com.mezencevsem.exchangerates.ui.screen.exchangerates.model.ExchangeRatesScreenDialogState
import com.mezencevsem.exchangerates.ui.screen.exchangerates.model.ExchangeRatesScreenEvent
import com.mezencevsem.exchangerates.ui.screen.exchangerates.model.ExchangeRatesScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ExchangeRatesScreenViewModel
@Inject constructor(
    private val interactor: ExchangeRatesInteractor
) : ViewModel() {

    val state by lazy { _state }
    private val _state: MutableStateFlow<ExchangeRatesScreenState> =
        MutableStateFlow(ExchangeRatesScreenState.Loading)

    val dialogState by lazy { _dialogState }
    private val _dialogState: MutableStateFlow<ExchangeRatesScreenDialogState?> =
        MutableStateFlow(null)

    init {
        viewModelScope.launch {
            val currencies = interactor.getAllCurrencies()

            _state.value = ExchangeRatesScreenState.Content(
                baseCurrency = null,
                currencies = currencies,
                filterState = ExchangeRatesFilter.AlphabeticalAsc
            )
        }
    }

    fun onEvent(event: ExchangeRatesScreenEvent) {
        when (event) {
            is ExchangeRatesScreenEvent.CurrencyClicked ->
                handleCurrencyClicked(event.currency)
            is ExchangeRatesScreenEvent.CurrencyFavoriteClicked ->
                handleCurrencyFavoriteClicked(event.currency)
            ExchangeRatesScreenEvent.CloseDialog ->
                _dialogState.value = null
            ExchangeRatesScreenEvent.FilterClicked ->
                _dialogState.value = ExchangeRatesScreenDialogState.Filter
            is ExchangeRatesScreenEvent.FilterStateChanged ->
                handleFilterStateChanged(event.value)
        }
    }

    private fun handleCurrencyClicked(currency: Currency) = viewModelScope.launch {
        val currentState = _state.value
        if (currentState !is ExchangeRatesScreenState.Content) return@launch

        if (currentState.baseCurrency == currency) return@launch

        //TODO request

        _state.value = currentState.copy(
            baseCurrency = currency,
            currencies = currentState.currencies
        )
    }

    private fun handleCurrencyFavoriteClicked(currency: Currency) = viewModelScope.launch {
        val currentState = _state.value
        if (currentState !is ExchangeRatesScreenState.Content) return@launch

        interactor.changeFavoriteCurrency(currency)

        _state.value = currentState.copy(
            baseCurrency = if (currentState.baseCurrency == currency) {
                currentState.baseCurrency.changeFavoriteState()
            } else currentState.baseCurrency,
            currencies = currentState.currencies.map {
                if (it == currency) it.changeFavoriteState() else it
            }
        )
    }

    private fun Currency.changeFavoriteState() =
        this.copy(isFavorite = !this.isFavorite)

    private fun handleFilterStateChanged(value: ExchangeRatesFilter) {
        val currentState = _state.value
        if (currentState !is ExchangeRatesScreenState.Content) return
        if (currentState.filterState == value) return

        val currencies = when (value) {
            ExchangeRatesFilter.AlphabeticalAsc -> {
                currentState.currencies.sortedBy {
                    it.code
                }
            }
            ExchangeRatesFilter.AlphabeticalDesc -> {
                currentState.currencies.sortedByDescending {
                    it.code
                }
            }
            ExchangeRatesFilter.ByValueAsc -> {
                currentState.currencies.sortedBy {
                    it.rate
                }
            }
            ExchangeRatesFilter.ByValueDesc -> {
                currentState.currencies.sortedByDescending {
                    it.rate
                }
            }
        }

        _state.value = currentState.copy(
            filterState = value,
            currencies = currencies
        )
    }
}