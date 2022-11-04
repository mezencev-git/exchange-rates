package com.mezencevsem.exchangerates.ui.screen.exchangerates

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mezencevsem.domain.model.Currency
import com.mezencevsem.exchangerates.R
import com.mezencevsem.exchangerates.ui.dialog.ExchangeRatesFilterDialog
import com.mezencevsem.exchangerates.ui.screen.exchangerates.element.BaseCurrencyItem
import com.mezencevsem.exchangerates.ui.screen.exchangerates.element.CurrencyItem
import com.mezencevsem.exchangerates.ui.screen.exchangerates.model.ExchangeRatesFilter
import com.mezencevsem.exchangerates.ui.screen.exchangerates.model.ExchangeRatesScreenDialogState
import com.mezencevsem.exchangerates.ui.screen.exchangerates.model.ExchangeRatesScreenEvent
import com.mezencevsem.exchangerates.ui.screen.exchangerates.model.ExchangeRatesScreenState
import com.mezencevsem.exchangerates.ui.theme.ExchangeRatesTheme
import com.mezencevsem.exchangerates.ui.theme.Gray

@Composable
internal fun ExchangeRatesScreen(
    modifier: Modifier = Modifier,
    viewModel: ExchangeRatesScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    val dialogState = viewModel.dialogState.collectAsState().value

    when (state) {
        ExchangeRatesScreenState.Loading -> Unit
        is ExchangeRatesScreenState.Content -> {
            ExchangeRatesScreen(
                modifier = modifier,
                content = state,
                onClickCurrency = {
                    viewModel.onEvent(ExchangeRatesScreenEvent.CurrencyClicked(it))
                },
                onClickFavoriteCurrency = {
                    viewModel.onEvent(ExchangeRatesScreenEvent.CurrencyFavoriteClicked(it))
                },
                onClickFilter = {
                    viewModel.onEvent(ExchangeRatesScreenEvent.FilterClicked)
                }
            )

            when (dialogState) {
                ExchangeRatesScreenDialogState.Filter -> {
                    ExchangeRatesFilterDialog(
                        value = state.filterState,
                        onDismissRequest = {
                            viewModel.onEvent(ExchangeRatesScreenEvent.CloseDialog)
                        },
                        onChangeValue = {
                            viewModel.onEvent(ExchangeRatesScreenEvent.FilterStateChanged(it))
                            viewModel.onEvent(ExchangeRatesScreenEvent.CloseDialog)
                        }
                    )
                }
                else -> Unit
            }
        }
        ExchangeRatesScreenState.Error -> Unit
    }
}

@Composable
private fun ExchangeRatesScreen(
    modifier: Modifier,
    content: ExchangeRatesScreenState.Content,
    onClickCurrency: (Currency) -> Unit,
    onClickFavoriteCurrency: (Currency) -> Unit,
    onClickFilter: () -> Unit
) = Column(
    modifier = modifier
        .padding(16.dp)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BaseCurrencyItem(
            modifier = Modifier
                .weight(1f)
                .background(
                    Gray.copy(alpha = 0.5f)
                ),
            currency = content.baseCurrency,
            onClickFavorite = onClickFavoriteCurrency
        )

        IconButton(
            onClick = onClickFilter,
            modifier = Modifier
                .padding(horizontal = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null,
                modifier = Modifier
                    .size(42.dp),
                tint = Gray
            )
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
    }

    var isOnlyFavorite by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = Modifier
            .weight(1f)
    ) {
        itemsIndexed(
            content.currencies
                .filter {
                    if (isOnlyFavorite) {
                        it.isFavorite
                    } else true
                }
                .filter {
                    it != content.baseCurrency
                }
        ) { index, currency ->
            CurrencyItem(
                modifier = Modifier
                    .background(
                        color = if (index % 2 == 0) Gray.copy(alpha = 0.5f)
                        else Color.Transparent
                    ),
                currency = currency,
                onClick = onClickCurrency,
                onClickFavorite = onClickFavoriteCurrency
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp
            )
            .clickable {
                isOnlyFavorite = !isOnlyFavorite
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isOnlyFavorite,
            onCheckedChange = {
                isOnlyFavorite = it
            },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(R.string.show_only_favorite),
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ExchangeRatesScreenPreview() {
    ExchangeRatesTheme {
        ExchangeRatesScreen(
            modifier = Modifier,
            content = ExchangeRatesScreenState.Content(
                baseCurrency = null,
                currencies = listOf(
                    Currency(
                        code = "AAA",
                        name = "Afghan",
                        rate = 0.1f,
                        isFavorite = false
                    ),
                    Currency(
                        code = "BBB",
                        name = "Afghan Afghani",
                        rate = 0.2f,
                        isFavorite = true
                    ),
                    Currency(
                        code = "CCC",
                        name = "Afghan Afghani Afghan",
                        rate = 0.3f,
                        isFavorite = false
                    ),
                    Currency(
                        code = "DDD",
                        name = "Afghan Afghani Afghan Afghani",
                        rate = 0.4f,
                        isFavorite = true
                    ),
                    Currency(
                        code = "EEE",
                        name = "Afghan Afghani Afghan Afghani Afghan",
                        rate = 5f,
                        isFavorite = false
                    )
                ),
                filterState = ExchangeRatesFilter.AlphabeticalAsc
            ),
            onClickCurrency = {},
            onClickFavoriteCurrency = {},
            onClickFilter = {}
        )
    }
}