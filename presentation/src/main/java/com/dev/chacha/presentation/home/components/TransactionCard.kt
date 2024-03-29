package com.dev.chacha.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R

@Composable
fun TransactionCard(
    onSendMoneyClicked: () -> Unit,
    onBuyAirtimeClicked: () -> Unit,
    onBankTransferClicked: () -> Unit,
    onPayBillClicked: () -> Unit,
    onWithdrawClicked: () -> Unit,
    onDepositClicked: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6F),
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
                    .padding(start=16.dp, end = 16.dp, top = 10.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextImageItem(
                    onItemClick = onSendMoneyClicked,
                    drawable = R.drawable.send_money_icon,
                    stringRes = R.string.send_money
                )
                TextImageItem(
                    onItemClick = onBankTransferClicked,
                    drawable = R.drawable.bank,
                    stringRes = R.string.bank_transfer
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
                    .padding(start=16.dp, end = 16.dp, bottom = 16.dp),
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
                    drawable = R.drawable.withdrawals_icon,
                    stringRes = R.string.withdraw
                )
                TextImageItem(
                    onItemClick = onDepositClicked,
                    drawable = R.drawable.icon_deposit,
                    stringRes = R.string.deposit
                )
            }

        }

    }

}