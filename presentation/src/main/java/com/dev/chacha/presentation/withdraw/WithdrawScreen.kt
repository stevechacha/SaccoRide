package com.dev.chacha.presentation.withdraw

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField

@Composable
fun WithdrawScreen(
    ignoredNavigateBack:()->Unit
) {
    Scaffold(
        topBar = {
            AppToolbar(
                title = "View your statement",
                showBackArrow = true,
                navigateBack = {ignoredNavigateBack()}
            )
        }
    ) { paddingValues ->

        val availableBalance = remember { mutableStateOf(10000.0) }
        var withdrawalAmount by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Withdraw")

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Available Balance: $${availableBalance.value}",
            )

            Spacer(modifier = Modifier.height(16.dp))

            RideOutlinedTextField(
                value = withdrawalAmount,
                onValueChange = { withdrawalAmount = it },
                hint = stringResource(id = R.string.amount),
                keyboardType = KeyboardType.Number,
            )

            Spacer(modifier = Modifier.height(16.dp))

            ContinueButton(
                onClick = { performWithdrawal(withdrawalAmount.toDouble()) },
                modifier = Modifier.align(Alignment.End),
                text = "Withdraw"
            )
        }


    }

}

private fun performWithdrawal(ignoredAmount: Double) {
    // Perform withdrawal logic here
    // Subtract the `amount` from the account balance or perform any other necessary operations
    // You can update the available balance accordingly
}


@Composable
fun WithdrawToolbar() {
    SmallTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.withdraw))
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.icon_arrow_back),
                    contentDescription = null
                )
            }
        }
    )
}