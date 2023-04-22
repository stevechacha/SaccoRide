package com.dev.chacha.presentation.transaction_history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun TransHistoryItem(
    transactionItem: TransactionsItem,
    onTransactionClick: (TransactionsItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (transactionItem.image != null) {
            AsyncImage(
                model = transactionItem.image,
                contentDescription = "profile_image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = CircleShape),
                placeholder = null
            )

        } else {
            val names = transactionItem.name.split(" ")
            val initials = names[0].first().toString() + names[1].first().toString()
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )

            }

        }
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = transactionItem.name,
                    style = MaterialTheme.typography.labelSmall,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = transactionItem.amount.toString(),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.labelSmall

                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = transactionItem.contact,
                    style = MaterialTheme.typography.labelSmall,
                    overflow = TextOverflow.Ellipsis

                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${transactionItem.date} ${transactionItem.time}",
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.labelSmall
                )
            }


        }



    }

}

@Composable
@Preview
fun TransactionItemPreview() {
    Column {
        LazyColumn {
            items(20) {
                TransHistoryItem(
                    transactionItem = TransactionsItem(
                        name = "John Doe",
                        contact = "1234567890",
                        amount = 1000.0,
                        date = "12,Apr",
                        time = "12:00 PM",
                        image = null
                    )
                ) {

                }
            }
        }
    }

}

