package com.dev.chacha.presentation.home

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.compiler.plugins.kotlin.ComposeFqNames.remember
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.home.components.HomeServiceCard
import com.dev.chacha.presentation.home.components.TransactionCard
import com.dev.chacha.presentation.transaction_history.components.TransHistoryItem
import com.dev.chacha.presentation.transaction_history.transactionsItem
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSendMoneyClicked: () -> Unit,
    onBuyAirtimeClicked: () -> Unit,
    onPayBillClicked: () -> Unit,
    onWithdrawClicked: () -> Unit,
    onDepositClicked: () -> Unit,
    onLoanClicked: () -> Unit,
    onMarketClicked: () -> Unit,
    onSavingsClicked: () -> Unit,
    navigateToAllTransactions: ()-> Unit,
    onBankTransferClicked: ()->Unit,
    navigateBack: ()->Unit
) {
    val topAppBarState = rememberTopAppBarScrollState()
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec, topAppBarState)
    val showAllTransactions by remember { mutableStateOf(false) }


    val now = Calendar.getInstance() // get the current time
    val hour = now.get(Calendar.HOUR_OF_DAY) // get the hour component of the current time

    val greeting = when (hour) { // determine the appropriate greeting based on the hour
        in 0..11 -> "Good morning"
        in 12..17 -> "Good afternoon"
        else -> "Good evening"
    }


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = "${greeting},Stephen",
                        fontSize = 20.sp,
                        maxLines = 2,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onSavingsClicked() }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                item {
                    TransactionCard(
                        onSendMoneyClicked = onSendMoneyClicked,
                        onBuyAirtimeClicked = onBuyAirtimeClicked,
                        onBankTransferClicked = onBankTransferClicked,
                        onPayBillClicked = onPayBillClicked,
                        onWithdrawClicked = onWithdrawClicked,
                        onDepositClicked = onDepositClicked
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                    HomeServiceCard(
                        onLoanClicked = onLoanClicked,
                        onMarketClicked = onMarketClicked,
                        onSavingsClicked = onSavingsClicked
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.recent_transaction),
                            style = MaterialTheme.typography.headlineSmall,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1

                        )
                        Text(
                            text = stringResource(id = R.string.see_all),
                            style = MaterialTheme.typography.bodyMedium,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier.clickable {
                                navigateToAllTransactions()
                            }
                        )
                    }


                }

                if (showAllTransactions) {
                    items(transactionsItem) { transaction ->
                        TransHistoryItem(
                            transactionItem = transaction,
                            onTransactionClick = {}
                        )
                    }
                } else {
                    items(transactionsItem.take(5)) { transaction ->
                        TransHistoryItem(
                            transactionItem = transaction,
                            onTransactionClick = {}
                        )
                    }
                }

            }

        }
    }
}

data class User(
    val title: String? = null,
    val icon: Int
)

@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
    HomeScreen(
        onSendMoneyClicked = { /*TODO*/ },
        onBuyAirtimeClicked = { /*TODO*/ },
        onBankTransferClicked = { /*TODO*/ },
        onPayBillClicked = { /*TODO*/ },
        onWithdrawClicked = { /*TODO*/ },
        onDepositClicked = { /*TODO*/ },
        onLoanClicked = { /*TODO*/ },
        onMarketClicked = { /*TODO*/ },
        onSavingsClicked = {},
        navigateToAllTransactions = {},
        navigateBack = {}
    )

}