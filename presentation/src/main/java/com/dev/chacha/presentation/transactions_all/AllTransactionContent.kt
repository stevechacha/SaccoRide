package com.dev.chacha.presentation.transactions_all

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.dev.chacha.presentation.transaction_history.TransactionsItem
import com.dev.chacha.presentation.transaction_history.transactionsItem
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalFoundationApi::class)

@Composable
fun AllTransactionContent(
    transactions: List<TransactionsItem>,
    onTransactionClick: (TransactionsItem) -> Unit
) {
    Scaffold(topBar = {}) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val groupedTransactions = transactions.groupBy { it.date }

                val sortedGroupedTransactions = groupedTransactions.toSortedMap(compareByDescending { it })

                sortedGroupedTransactions.forEach { (date, transactionsByDate) ->
                    val formattedDate = formatDate(date)
                    stickyHeader {
                        if (formattedDate != null) {
                            DateHeader(date = formattedDate)
                        }
                    }
                    items(transactionsByDate.size) { index ->
                        TransactionHistoryItem(
                            transactionItem = transactionsByDate[index],
                            onTransactionClick = onTransactionClick
                        )
                    }
                }
            }
        }
    }
}

private fun formatDate(date: String): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val parsedDate = inputFormat.parse(date)
    return parsedDate?.let { outputFormat.format(it) }
}



@Composable
@Preview(showBackground = true)
fun PreviewAllTransactionScreen() {
    AllTransactionContent(
        transactions = transactionsItem,
        onTransactionClick = {}
    )

}