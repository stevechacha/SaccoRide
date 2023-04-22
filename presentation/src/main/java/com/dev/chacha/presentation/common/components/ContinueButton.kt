package com.dev.chacha.presentation.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.common.theme.PrimaryColor

@Composable
fun ContinueButton(text: String, modifier: Modifier = Modifier, onClick: (() -> Unit)) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = PrimaryColor
        )
    ) {
        Text(
            modifier = modifier.padding(8.dp),
            text = text,
            style = MaterialTheme.typography.labelSmall

        )
    }
}