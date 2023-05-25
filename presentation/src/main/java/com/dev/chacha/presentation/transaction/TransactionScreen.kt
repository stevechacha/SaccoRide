package com.dev.chacha.presentation.transaction

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.transaction.components.HorizontalCardItem
import com.dev.chacha.presentation.transaction.components.TransactTopBar


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionScreen(
    onSendMoneyClicked: () -> Unit,
    onBuyAirtimeClicked: () -> Unit,
    onBankTransferClicked: () -> Unit,
    onPayBillClicked: () -> Unit,
    onWithdrawClicked: () -> Unit,
    onDepositClicked: () -> Unit,
    onLoanClicked: () -> Unit,
    onMarketClicked: () -> Unit,
    onSavingsClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TransactTopBar(title = "Transact")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            CompositionLocalProvider(
                LocalOverscrollConfiguration provides null
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
                            drawable = R.drawable.send_money_icon,
                            text = R.string.send_money,
                            onItemClick = { onSendMoneyClicked() }
                        )
                    }
                    item {
                        HorizontalCardItem(
                            drawable = R.drawable.bank,
                            text = R.string.bank_transfer,
                            onItemClick = { onBankTransferClicked() })


                    }
                    item {
                        HorizontalCardItem(
                            drawable = R.drawable.ic_utility,
                            text = R.string.paybill,
                            onItemClick = { onPayBillClicked() }
                        )

                    }

                    item {
                        HorizontalCardItem(
                            drawable = R.drawable.ic_system_upate_24,
                            text = R.string.buy_airtime,
                            onItemClick = { onBuyAirtimeClicked() }
                        )

                    }
                    item {
                        HorizontalCardItem(
                            drawable = R.drawable.withdrawals_icon,
                            text = R.string.withdraw,
                            onItemClick = { onWithdrawClicked() }
                        )

                    }
                    item {
                        HorizontalCardItem(
                            drawable = R.drawable.icon_deposit,
                            text = R.string.deposit,
                            onItemClick = { onDepositClicked() }
                        )

                    }
                    item {
                        HorizontalCardItem(
                            drawable = R.drawable.loan_icon,
                            text = R.string.loan,
                            onItemClick = { onLoanClicked() }
                        )

                    }
                    item {
                        HorizontalCardItem(
                            drawable = R.drawable.market_icons,
                            text = R.string.our_market,
                            onItemClick = { onMarketClicked() }
                        )

                    }
                    item {
                        HorizontalCardItem(
                            drawable = R.drawable.savings_icons,
                            text = R.string.savings,
                            onItemClick = { onSavingsClicked() }
                        )

                    }

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
        onBankTransferClicked = { /*TODO*/ },
        onPayBillClicked = { /*TODO*/ },
        onWithdrawClicked = { /*TODO*/ },
        onDepositClicked = { /*TODO*/ },
        onLoanClicked = { /*TODO*/ },
        onMarketClicked = { /*TODO*/ },
        onSavingsClicked = {}
    )

}