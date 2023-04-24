package com.dev.chacha.presentation.transaction_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dev.chacha.presentation.common.theme.SaccoRideTheme
import com.dev.chacha.presentation.transaction_history.TransactionsItem
import com.dev.chacha.presentation.transaction_history.transactionsItem

@Composable
fun TransHistoryItem(
    transactionItem: TransactionsItem,
    onTransactionClick: (TransactionsItem) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if (transactionItem.image != null) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(colorScheme.onSurface.copy(alpha = 0.08F)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = transactionItem.image,
                        contentDescription = "profile_image",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(shape = CircleShape),
                        placeholder = null
                    )
                }

            } else {
                val names = transactionItem.name.split(" ")
                val initials = names[0].first().toString() + names[1].first().toString()
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(colorScheme.onSurface.copy(alpha = 0.08F)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = initials,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        style = typography.headlineSmall
                    )
                }

            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = transactionItem.name,
                        style = typography.labelSmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Start,
                        )
                    Text(
                        text = transactionItem.amount.toString(),
                        textAlign = TextAlign.End,
                        style = typography.labelSmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = transactionItem.contact,
                        style = typography.labelSmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Start,


                        )
                    Text(
                        text = "${transactionItem.date},${transactionItem.time}",
                        textAlign = TextAlign.End,
                        style = typography.labelSmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }

            }

        }


    }
}


@Composable
@Preview
fun TransHistoryItemPreview() {
    SaccoRideTheme {
        LazyColumn {
            items(transactionsItem.size) { index ->
                TransHistoryItem(
                    transactionItem = transactionsItem[index],
                    onTransactionClick = {}
                )
            }
        }

    }
}


