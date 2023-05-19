package com.dev.chacha.presentation.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.common.theme.PrimaryColor

@Composable
fun ContinueButton(
    text: String, modifier: Modifier = Modifier, onClick: (() -> Unit),
    enable: Boolean =  false
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = if (enable) PrimaryColor else LocalContentColor.current.copy(alpha = 0.12f),
            disabledContainerColor = LocalContentColor.current.copy(alpha = 0.12f)
        ),
        enabled = enable
    ) {
        Text(
            modifier = modifier.padding(5.dp),
            text = text,
            style = MaterialTheme.typography.labelSmall

        )
    }
}

@Composable
@Preview
fun ContinueButtonPreview() {
    Column {
        ContinueButton(text = "Continue", onClick = { }, enable = false)
        Spacer(modifier = Modifier.height(10.dp))
        ContinueButton(text = "Continue", onClick = { }, enable = true)
    }
}