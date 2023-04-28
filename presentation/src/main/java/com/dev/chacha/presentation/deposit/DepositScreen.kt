package com.dev.chacha.presentation.deposit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DepositScreen() {
    Scaffold { paddingValues->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center
        ) {

          //  LoanScreens()


        }

    }


}

@Composable
@Preview
fun PreviewText() {
    Surface(modifier = Modifier.fillMaxSize()) {
        DepositScreen()
    }
}