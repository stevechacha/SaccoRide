package com.dev.chacha.presentation.account.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R

@Composable
fun BalanceCard(
    @DrawableRes drawable: Int,
    username: String,
    contact: String,
    balance: String,
    onClick: ()->Unit
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
        ) {
            Box(
                modifier = Modifier.size(80.dp),
                contentAlignment = Alignment.TopStart
            ){
                Icon(
                    painter = painterResource(id = drawable),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )

            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = username,
                )
                Text(
                    text = contact,
                )
                Text(
                    text = stringResource(id = R.string.available_balance),
                )

                Text(
                    text = balance.toString(),
                )

                Button(
                    onClick = { onClick() },
                    modifier = Modifier.width(200.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = stringResource(id = R.string.statement))

                }
            }

        }

    }

}