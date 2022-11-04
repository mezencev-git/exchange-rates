package com.mezencevsem.exchangerates.ui.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mezencevsem.exchangerates.R
import com.mezencevsem.exchangerates.ui.screen.exchangerates.model.ExchangeRatesFilter

@Composable
internal fun ExchangeRatesFilterDialog(
    value: ExchangeRatesFilter,
    onDismissRequest: () -> Unit,
    onChangeValue: (ExchangeRatesFilter) -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.filter_dialog_title),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = stringResource(R.string.filter_dialog_alphabetical_title),
                    modifier = Modifier,
                    style = MaterialTheme.typography.h6
                )

                FilterRow(
                    currentValue = value,
                    value = ExchangeRatesFilter.AlphabeticalAsc,
                    onClick = onChangeValue
                )

                FilterRow(
                    currentValue = value,
                    value = ExchangeRatesFilter.AlphabeticalDesc,
                    onClick = onChangeValue
                )

                Text(
                    text = stringResource(R.string.filter_dialog_by_value_title),
                    modifier = Modifier,
                    style = MaterialTheme.typography.h6
                )

                FilterRow(
                    currentValue = value,
                    value = ExchangeRatesFilter.ByValueAsc,
                    onClick = onChangeValue
                )

                FilterRow(
                    currentValue = value,
                    value = ExchangeRatesFilter.ByValueDesc,
                    onClick = onChangeValue
                )
            }
        }
    }
}

@Composable
private fun FilterRow(
    currentValue: ExchangeRatesFilter,
    value: ExchangeRatesFilter,
    onClick: (ExchangeRatesFilter) -> Unit
) = Row(
    modifier = Modifier
        .clickable {
            onClick(value)
        },
    verticalAlignment = Alignment.CenterVertically
) {
    RadioButton(
        selected = value == currentValue,
        onClick = {
            onClick(value)
        }
    )

    Text(
        text = stringResource(
            when (value) {
                ExchangeRatesFilter.AlphabeticalAsc,
                ExchangeRatesFilter.ByValueAsc -> R.string.filter_dialog_ascending_title
                ExchangeRatesFilter.AlphabeticalDesc,
                ExchangeRatesFilter.ByValueDesc -> R.string.filter_dialog_descending_title
            }
        ),
        modifier = Modifier,
        style = MaterialTheme.typography.body1
    )
}