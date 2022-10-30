package com.mezencevsem.exchangerates.ui.screen.exchangerates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mezencevsem.exchangerates.ui.theme.ExchangeRatesTheme

@Composable
internal fun ExchangeRatesScreen(
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier
        .background(Color.Blue)
) {

}

@Composable
@Preview(showBackground = true)
private fun ExchangeRatesScreenPreview() {
    ExchangeRatesTheme {
        ExchangeRatesScreen()
    }
}