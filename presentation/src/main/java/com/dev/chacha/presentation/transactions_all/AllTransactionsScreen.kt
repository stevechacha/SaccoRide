package com.dev.chacha.presentation.transactions_all

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.navigation.HomeAction
import com.dev.chacha.presentation.transaction_history.transactionsItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllTransactionsScreen(
    navigateBack:()->Unit
) {
    Scaffold(topBar = {
        AppToolbar(
            title = "All Transactions",
            showBackArrow = true,
            navigateBack = {navigateBack()}
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            AllTransactionContent(
                transactions = transactionsItem,
                onTransactionClick = {}
            )
        }
    }
}

@Composable
@Preview
fun AllTransactionsScreenPreview() {
    AllTransactionsScreen(
        navigateBack = {}
    )
}