package com.dev.chacha.presentation.auth.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.auth.component.SignUpInText
import com.dev.chacha.presentation.auth.component.SignupText
import com.dev.chacha.presentation.common.components.AppOutlinedTextField
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.theme.PrimaryColor
import com.dev.chacha.presentation.common.theme.SaccoRideTheme


@Composable
fun RegisterScreen(
    onClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    RegisterContent(
        onClick = onClick,
        onLoginClick = onLoginClick
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(
    onClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    val (email, setEmail) = rememberSaveable { mutableStateOf("") }
    val (mobileNumber, setMobileNumber) = rememberSaveable { mutableStateOf("") }
    val (idNumber, setIdNumber) = rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Register",
                showForwardArrow = true,
                showBackArrow = true
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AppOutlinedTextField(
                value = email,
                onValueChange = { setEmail(it) },
                hint = stringResource(id = R.string.email_hint),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(16.dp))
            AppOutlinedTextField(
                value = mobileNumber,
                onValueChange = { setMobileNumber(it) },
                hint = stringResource(id = R.string.mobile_number_hint),
                keyboardType = KeyboardType.Phone
            )
            Spacer(modifier = Modifier.height(16.dp))

            AppOutlinedTextField(
                value = idNumber,
                onValueChange = { setIdNumber(it) },
                hint = stringResource(id = R.string.id_number_hint),
                keyboardType = KeyboardType.Number
            )

            Spacer(modifier = Modifier.height(16.dp))
            ContinueButton(
                onClick = { onClick() },
                text = stringResource(id = R.string.continuee)
            )

            Spacer(modifier = Modifier.height(16.dp))

            SignUpInText(
                onClick = { onLoginClick()},
                text = R.string.already_have_an_account ,
                signUpText = R.string.login
            )

        }
    }
}

@Composable
@Preview
fun RegisterScreenPreview() {
    SaccoRideTheme {
        RegisterScreen(
            onClick = {},
            onLoginClick = {}
        )
    }
}
