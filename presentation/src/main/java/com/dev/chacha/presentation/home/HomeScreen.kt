package com.dev.chacha.presentation.home

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.theme.SaccoRideTheme
import com.dev.chacha.presentation.transaction_history.TransHistoryItem
import com.dev.chacha.presentation.transaction_history.TransactionsItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSendMoneyClicked: () -> Unit,
    onBuyAirtimeClicked: () -> Unit,
    onBuyGoodsClicked: () -> Unit,
    onPayBillClicked: () -> Unit,
    onWithdrawClicked: () -> Unit,
    onDepositClicked: () -> Unit,
    onLoanClicked: () -> Unit,
    onMarketClicked: () -> Unit,
    onSavingsClicked: () -> Unit
) {
    val topAppBarState = rememberTopAppBarScrollState()
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec,topAppBarState)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = "Good Morning,John",
                        fontSize = 20.sp,
                        maxLines = 2,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(end = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
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
            val transactionsItem = listOf(
                TransactionsItem(
                    name = "Stephen Chacha",
                    contact = "0746656813",
                    amount = 68700.0,
                    date = "12 Apr",
                    time = "12:00 PM",
                    image = null
                ),
                TransactionsItem(
                    name = "Mary Tenui",
                    contact = "0746656813",
                    amount = 10000.0,
                    date = "12 Apr",
                    time = "12:00 PM",
                    image = null
                ),
                TransactionsItem(
                    name = "David Mtundi",
                    contact = "0746656813",
                    amount = 50000.0,
                    date = "12 Apr",
                    time = "12:00 PM",
                    image = null
                ),
                TransactionsItem(
                    name = "Nairobi Water",
                    contact = "123456",
                    amount = 1000.0,
                    date = "12 Apr",
                    time = "12:00 PM",
                    image = null
                ),
                TransactionsItem(
                    name = "Nairobi Water",
                    contact = "123456",
                    amount = 1000.0,
                    date = "12 Apr",
                    time = "12:00 PM",
                    image = R.drawable.ic_home
                ),
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ){
                item {
                    TransactionCard(
                        onSendMoneyClicked = onSendMoneyClicked,
                        onBuyAirtimeClicked = onBuyAirtimeClicked,
                        onBuyGoodsClicked = onBuyGoodsClicked,
                        onPayBillClicked = onPayBillClicked,
                        onWithdrawClicked = onWithdrawClicked,
                        onDepositClicked = onDepositClicked
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    PayWithRideSacco(
                        onLoanClicked = onLoanClicked,
                        onMarketClicked = onMarketClicked,
                        onSavingsClicked = onSavingsClicked
                    )
                }
                item{
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.recent_transaction),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                }
                items(transactionsItem.size){ index ->
                    TransHistoryItem(
                        transactionItem = transactionsItem[index],
                        onTransactionClick = {}
                    )

                }

            }

        }

    }
}

@Composable
fun TransactionCard(
    onSendMoneyClicked: () -> Unit,
    onBuyAirtimeClicked: () -> Unit,
    onBuyGoodsClicked: () -> Unit,
    onPayBillClicked: () -> Unit,
    onWithdrawClicked: () -> Unit,
    onDepositClicked: () -> Unit,
    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextImageItem(
                    onItemClick = onSendMoneyClicked,
                    drawable = R.drawable.ic_send_money,
                    stringRes = R.string.send_money
                )
                TextImageItem(
                    onItemClick = onBuyGoodsClicked,
                    drawable = R.drawable.ic_shopping_cart,
                    stringRes = R.string.buy_goods
                )

                TextImageItem(
                    onItemClick = onPayBillClicked,
                    drawable = R.drawable.ic_utility,
                    stringRes = R.string.paybill
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextImageItem(
                    onItemClick = onBuyAirtimeClicked,
                    drawable = R.drawable.ic_system_upate_24,
                    stringRes = R.string.buy_airtime
                )

                TextImageItem(
                    onItemClick = onWithdrawClicked,
                    drawable = R.drawable.ic_send_money,
                    stringRes = R.string.withdraw
                )
                TextImageItem(
                    onItemClick = onDepositClicked,
                    drawable = R.drawable.ic_system_upate_24,
                    stringRes = R.string.deposit
                )
            }

        }

    }

}

@Composable
fun PayWithRideSacco(
    onLoanClicked: () -> Unit,
    onMarketClicked: () -> Unit,
    onSavingsClicked: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeServiceItem(
            onItemClick = onLoanClicked,
            drawable = R.drawable.ic_utility,
            stringRes = R.string.loan
        )

        HomeServiceItem(
            onItemClick = onMarketClicked,
            drawable = R.drawable.ic_utility,
            stringRes = R.string.our_market
        )

        HomeServiceItem(
            onItemClick = onSavingsClicked,
            drawable = R.drawable.ic_utility,
            stringRes = R.string.savings
        )

    }

}



@Composable
fun HomeToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 20.dp, end = 20.dp, top = 19.dp, bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Welcome Back !",
                style = MaterialTheme.typography.labelSmall

            )
            Text(
                text = "Stephen Chacha",
                style = MaterialTheme.typography.labelSmall

            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.sessions_icon),
            contentDescription = "Current Balance",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.onSecondaryContainer)
        )
    }
}


data class User(
    val title: String? = null,
    val icon: Int
)

@Composable
@Preview
fun HomeScreenPreview() {
    SaccoRideTheme {
        HomeScreen(
            onSendMoneyClicked = { /*TODO*/ },
            onBuyAirtimeClicked = { /*TODO*/ },
            onBuyGoodsClicked = { /*TODO*/ },
            onPayBillClicked = { /*TODO*/ },
            onWithdrawClicked = { /*TODO*/ },
            onDepositClicked = { /*TODO*/ },
            onLoanClicked = { /*TODO*/ },
            onMarketClicked = { /*TODO*/ }) {

        }
    }
}