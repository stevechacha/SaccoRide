package com.dev.chacha.presentation.transaction

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.transaction.components.HorizontalCardItem
import com.dev.chacha.presentation.transaction.components.TransactTopBar


@Composable
fun TransactionScreen(
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
    Scaffold(
        topBar = {
            TransactTopBar( title = "Transact")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    HorizontalCardItem(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.send_money,
                        onItemClick = { onSendMoneyClicked() }
                    )
                }
                item {
                    HorizontalCardItem(
                        drawable = R.drawable.shopping_cart,
                        text = R.string.buy_goods,
                        onItemClick = { onBuyGoodsClicked() })


                }
                item {
                    HorizontalCardItem(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.paybill,
                        onItemClick = { onPayBillClicked() }
                    )

                }

                item {
                    HorizontalCardItem(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.buy_airtime,
                        onItemClick = { onBuyAirtimeClicked() }
                    )

                }
                item {
                    HorizontalCardItem(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.withdraw,
                        onItemClick = { onWithdrawClicked() }
                    )

                }
                item {
                    HorizontalCardItem(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.deposit,
                        onItemClick = { onDepositClicked() }
                    )

                }

                item {
                    HorizontalCardItem(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.loan,
                        onItemClick = { onLoanClicked() }
                    )

                }
                item {
                    HorizontalCardItem(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.our_market,
                        onItemClick = { onMarketClicked() }
                    )

                }
                item {
                    HorizontalCardItem(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.savings,
                        onItemClick = { onSavingsClicked() }
                    )

                }

            }
        }

    }

}


@Composable
@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
fun TransactionScreenPreview() {
    TransactionScreen(
        onSendMoneyClicked = { /*TODO*/ },
        onBuyAirtimeClicked = { /*TODO*/ },
        onBuyGoodsClicked = { /*TODO*/ },
        onPayBillClicked = { /*TODO*/ },
        onWithdrawClicked = { /*TODO*/ },
        onDepositClicked = { /*TODO*/ },
        onLoanClicked = { /*TODO*/ },
        onMarketClicked = { /*TODO*/ },
        onSavingsClicked = {}
    )

}