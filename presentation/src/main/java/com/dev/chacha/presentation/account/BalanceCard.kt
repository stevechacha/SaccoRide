package com.dev.chacha.presentation.account

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BalanceCard(
    @StringRes title: Int,
    balance: String,
    @StringRes transactionText: Int,
    @DrawableRes drawable: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = balance,
                    fontSize = 24.sp
                )
                Text(
                    text = stringResource(id = transactionText),
                    style = MaterialTheme.typography.labelSmall

                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )

        }

    }

}