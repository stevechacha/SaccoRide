package com.dev.chacha.presentation.auth.forgot

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppOutlinedTextField
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.components.ContinueButton

@Composable
fun ForgotPasswordScreen(
    onClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    ForgotPasswordContent(
        onClick = onClick,
        onLoginClick = onLoginClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordContent(
    onClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    val (email, setEmail) = rememberSaveable { mutableStateOf("") }
    val (confirmPassword, setConfirmPassword) = rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Forgot Password",
                showForwardArrow = true,
                navigateBack = {}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp),
        ) {
            AppOutlinedTextField(
                value = email,
                onValueChange = { setEmail(it) },
                hint = stringResource(id = R.string.email_hint),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(30.dp))

            ContinueButton(
                text = stringResource(id = R.string.continuee),
                onClick = onClick,
                enable = email.isNotEmpty()

            )

        }
    }

}


@Composable
@Preview
fun ForgetPasswordScreenPreview() {
    ForgotPasswordScreen(
        onClick = {},
        onLoginClick = {}
    )
}

