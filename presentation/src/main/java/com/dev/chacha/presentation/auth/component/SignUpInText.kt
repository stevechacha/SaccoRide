package com.dev.chacha.presentation.auth.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.R
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.dev.chacha.presentation.common.theme.PrimaryColor

@Composable
fun SignUpInText(
    @StringRes text: Int,
    @StringRes signUpText: Int,
    onClick: ()-> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = stringResource(id = text)
        )
        Text(
            text = stringResource(id = signUpText),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable(onClick = onClick),
            color = PrimaryColor
        )

    }

}

@Composable
fun SignupText(
    modifier: Modifier,
    onClick: () -> Unit,
    @StringRes text: Int,
    @StringRes signUpText: Int,
) {
    TextButton(
        modifier = modifier,
        onClick = { onClick() }) {
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = text) )
                append(" ")
                withStyle(
                    SpanStyle(
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(stringResource(id =signUpText))
                }
                append(" ")
//                append(stringResource(id = R.string.here))
            }
        )
    }
}