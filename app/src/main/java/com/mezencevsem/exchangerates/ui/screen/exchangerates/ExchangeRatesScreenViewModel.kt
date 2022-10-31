package com.mezencevsem.exchangerates.ui.screen.exchangerates

import androidx.lifecycle.ViewModel
import com.mezencevsem.domain.ExchangeRatesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ExchangeRatesScreenViewModel
@Inject constructor(
    private val interactor: ExchangeRatesInteractor
) : ViewModel() {

}