package com.mezencevsem.exchangerates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.mezencevsem.exchangerates.ui.screen.exchangerates.ExchangeRatesScreen
import com.mezencevsem.exchangerates.ui.theme.ExchangeRatesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangeRatesTheme {
                ExchangeRatesScreen(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}