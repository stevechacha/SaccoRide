package com.dev.chacha.presentation.transaction_history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.transaction_history.components.TransHistoryItem


@Composable
fun TransactionsHistory(
    transactions: List<TransactionsItem>,
    onTransactionClick: (TransactionsItem) -> Unit
) {
    Scaffold(topBar = {}) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(transactions.size) { index ->
                    TransHistoryItem(
                        transactionItem = transactions[index],
                        onTransactionClick = onTransactionClick
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TransHistory() {
    Column {
        LazyColumn {
            items(transactionsItem.size) {
                TransactionsHistory(
                    transactions = transactionsItem,
                    onTransactionClick = {}
                )
            }
        }
    }
}

