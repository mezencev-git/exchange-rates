package com.mezencevsem.exchangerates.ui.screen.exchangerates.element

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mezencevsem.domain.model.Currency
import com.mezencevsem.exchangerates.R
import com.mezencevsem.exchangerates.ui.theme.ExchangeRatesTheme
import com.mezencevsem.exchangerates.ui.theme.Gold
import com.mezencevsem.exchangerates.ui.theme.Gray

@Composable
internal fun CurrencyItem(
    modifier: Modifier = Modifier,
    currency: Currency,
    onClick: (Currency) -> Unit,
    onClickFavorite: (Currency) -> Unit
) = Row(
    modifier = modifier
        .clickable(onClick = { onClick(currency) })
        .padding(all = 4.dp)
        .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically
) {
    Column(
        modifier = Modifier
            .weight(1f)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = currency.code,
                modifier = Modifier
                    .weight(1f),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground
            )

            currency.rate?.let {
                Text(
                    text = stringResource(R.string.rate_format, it),
                    style = MaterialTheme.typography.h5,
                    maxLines = 1,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = currency.name,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onBackground
            )
        }
    }

    Spacer(modifier = Modifier.width(8.dp))

    IconButton(
        onClick = { onClickFavorite(currency) },
        modifier = Modifier
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = stringResource(
                if (currency.isFavorite) R.string.remove_currency_fom_favorite
                else R.string.add_currency_to_favorite,
                currency.code,
                currency.name
            ),
            modifier = Modifier
                .size(42.dp),
            tint = if (currency.isFavorite) Gold else Gray
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun CurrencyItemPreview() {
    ExchangeRatesTheme {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        ) {
            CurrencyItem(
                modifier = Modifier
                    .padding(
                        vertical = 8.dp
                    ),
                currency = Currency(
                    code = "AED",
                    name = "United Arab Emirates Dirham",
                    rate = 56789.12345f,
                    isFavorite = true
                ),
                onClick = {},
                onClickFavorite = {}
            )

            CurrencyItem(
                modifier = Modifier
                    .padding(
                        vertical = 8.dp
                    ),
                currency = Currency(
                    code = "AFN",
                    name = "Afghan Afghani",
                    rate = 0.12345f,
                    isFavorite = false
                ),
                onClick = {},
                onClickFavorite = {}
            )
        }
    }
}