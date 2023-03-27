package com.dev.chacha.presentation.common.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.theme.SaccoRideTheme


@Composable
fun SubmitButton(
    onClickAction: () -> Unit
) {
    val submitButtonColor = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.onBackground,
        contentColor = MaterialTheme.colorScheme.onPrimary
    )
    Button(
        onClick = onClickAction,
        modifier = Modifier.padding(16.dp),
        colors = submitButtonColor
    ) {
        Text(
            text = stringResource(id = R.string.submit),
            modifier = Modifier.padding(5.dp)
        )
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SubmitButtonPreview() {
    SaccoRideTheme {
        SubmitButton(
            onClickAction = { }
        )
    }
}