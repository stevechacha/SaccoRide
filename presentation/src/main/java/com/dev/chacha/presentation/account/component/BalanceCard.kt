package com.dev.chacha.presentation.account.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.theme.Brutalista

@Composable
fun BalanceCard(
    @DrawableRes drawable: Int,
    username: String,
    contact: String,
    balance: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6F),
        )

    ) {
        Row(
            modifier = Modifier
                .padding(vertical = dimensionResource(id = R.dimen.margin_10), horizontal = 12.dp)
        ) {
            Icon(
                painter = painterResource(id = drawable),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = username,
                    fontFamily = Brutalista,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Text(
                    text = contact,
                    fontFamily = Brutalista,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Text(
                    text = stringResource(id = R.string.available_balance),
                    fontFamily = Brutalista,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Text(
                    text = balance,
                    fontFamily = Brutalista,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }
        }

    }

}