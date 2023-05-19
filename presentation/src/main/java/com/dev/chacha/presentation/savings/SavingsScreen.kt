package com.dev.chacha.presentation.savings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SavingsScreen(
    navigateBack:()->Unit
) {
    val savingsAccount = remember { mutableStateOf(5000.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Savings Account")

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Current Balance: Ksh${savingsAccount.value}",
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Perform withdrawal */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Withdraw")
        }
    }

}
