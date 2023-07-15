package com.dev.chacha.presentation.base

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RenderAdaptivePane(
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit,
) {
    Row {
        Box(
            Modifier
                .fillMaxSize()
                .weight(1f)
        )
        Spacer(Modifier.width(16.dp))
        Box(
            Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = contentAlignment,
        ) {
            content()
        }
    }

}