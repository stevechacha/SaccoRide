package com.dev.chacha.presentation.deposit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
            Text(
                text = "Deposit Screen",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = "Deposit Screen",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = "Deposit Screen",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.labelSmall
            )

            Text(
                text = "Deposit Screen",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Deposit Screen",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Deposit Screen",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Deposit Screen",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall
            )

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