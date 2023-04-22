package com.dev.chacha.presentation.account

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.pychart.PieChart


@Composable
fun AccountScreen() {
    Scaffold(
        topBar = {
            AppToolbar(
                title = stringResource(id = R.string.account),
                showBackArrow = true
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            val balance = 20
            LazyColumn {
                item {
                    BalanceCard(
                        title = R.string.your_balane,
                        balance = "Ksh$balance",
                        transactionText = R.string.check_all_transaction,
                        drawable = R.drawable.ic_home
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    RowIconText(
                        drawable = R.drawable.ic_home,
                        text = R.string.manage_pin,
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    RowIconText(
                        drawable = R.drawable.ic_home,
                        text = R.string.check_sacco_statement,
                        onClick = {}
                    )

                    Spacer(modifier = Modifier.height(30.dp))
                    UserAccountVerticalCard(
                        loanBalance = "100",
                        loanLimit = "1000",
                        loanDueDate = "12 June",
                        savingAmount = "1000"
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    PieChart(
                        data = mapOf(
                            Pair("Send Money", 15000),
                            Pair("Buy Goods", 12000),
                            Pair("Buy Airtime", 11000),
                            Pair("Pay Bill", 13440),
                            Pair("Others", 10000),
                        )
                    )


                }
            }

        }

    }

}

@Composable
@Preview
fun AccountScreenPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        AccountScreen()
    }
}







