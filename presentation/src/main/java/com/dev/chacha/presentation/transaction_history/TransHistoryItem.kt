package com.dev.chacha.presentation.transaction_history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
            .fillMaxWidth()
            .padding(5.dp),
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
                    .size(40.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    fontSize = 26.sp
                )

            }

        }
        Column(
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(
                text = transactionItem.name
            )
            Text(
                text = transactionItem.contact
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = transactionItem.amount,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = "${transactionItem.date} ${transactionItem.time}",
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(end = 10.dp)

            )
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
                        amount = "₹ 1000",
                        date = "12/12/2021",
                        time = "12:00 PM",
                        image = null
                    )
                ) {

                }
            }
        }
    }

}

