package com.dev.chacha.presentation.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    Card(
        onClick = { onClick() },
        modifier = modifier.size(width = 180.dp, height = 100.dp)
    ) {
        Box(modifier.fillMaxSize()) {
            Text(
                "Clickable",
                modifier.align(Alignment.Center),
                style = MaterialTheme.typography.labelSmall

            )
        }
    }
}

@Composable
@Preview
fun FinanceCardPreview() {
    FinanceCard(onClick = { /*TODO*/ }) {
    }
}