package com.dev.chacha.presentation.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.account.component.RowIconText
import com.dev.chacha.presentation.account.component.UserAccountVerticalCard
import com.dev.chacha.presentation.piechart.PieChart

@Composable
fun AccountDetails() {
    Column {
        UserAccountVerticalCard(
            loanBalance = "100",
            loanLimit = "1000",
            loanDueDate = "12 June",
            savingAmount = "1000"
        )
        Spacer(modifier = Modifier.height(16.dp))
        PieChart(
            data = mapOf(
                Pair("Send Money", 15000),
                Pair("Buy Goods", 12000),
                Pair("Buy Airtime", 11000),
                Pair("Pay Bill", 13440),
                Pair("Others", 10000),
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        RowIconText(
            icon = R.drawable.ic_home,
            title = R.string.manage_pin,
            onClick = {}
        )
        Spacer(modifier = Modifier.height(16.dp))

        RowIconText(
            icon = R.drawable.ic_home,
            title = R.string.check_sacco_statement,
            onClick = {}
        )
    }

}