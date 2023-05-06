package com.dev.chacha.presentation.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.auth.component.SignUpInText
import com.dev.chacha.presentation.common.components.AppOutlinedTextField
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.theme.PrimaryColor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotClick: () -> Unit,
    viewModel: LoginViewModel = viewModel(),
) {
    LoginContent(
        onClick = onClick,
        onSignUpClick = onSignUpClick,
        onForgotClick = onForgotClick,
        viewModel = viewModel,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    onClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotClick: () -> Unit,
    viewModel: LoginViewModel,
) {
    val usernameState = viewModel.usernameState.value
    val passwordState = viewModel.passwordState.value
    val loginState = viewModel.loginState.value

    val snackBarHostState = remember { SnackbarHostState() }
    val localCoroutineScope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is LoginUiEvents.SnackBarEvent -> {
                    localCoroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            message = "Error message",
                            duration = SnackbarDuration.Short
                        )
                    }
                }

                is LoginUiEvents.NavigateEvent -> {
                    event.route
                    onClick()
                    localCoroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            message = "Login successful",
                            duration = SnackbarDuration.Short
                        )
                    }

                }
            }
        }
    }

    Scaffold(
        topBar = {
            AppToolbar(
                title = " ",
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(vertical = 16.dp, horizontal = 16.dp),
        ) {
            val text by rememberSaveable { mutableStateOf("") }
            val charLimit = 10

            Text(
                text = "Login",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(20.dp))

            AppOutlinedTextField(
                value = usernameState.text,
                onValueChange = {
                    viewModel.setUsername(it)
                },
                keyboardType = KeyboardType.Email,
                hint = stringResource(id = R.string.email_or_mobile_number),
                supportingText = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Limit: ${usernameState.text.length}/$charLimit",
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.labelSmall

                    )
                }
            )


            if (usernameState.error != "") {
                Text(
                    text = usernameState.error,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            AppOutlinedTextField(
                value = passwordState.text,
                onValueChange = { viewModel.setPassword(it) },
                keyboardType = KeyboardType.Password,
                hint = stringResource(id = R.string.password),
                isPasswordVisible = viewModel.showPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowPassword(it)
                },

                )
            if (passwordState.error != "") {
                Text(
                    text = passwordState.error,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.labelSmall

                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                modifier = Modifier
                    .clickable { onForgotClick() },
                text = "Forgot Password?",
                textAlign = TextAlign.End,
                color = PrimaryColor,
                style = MaterialTheme.typography.labelSmall
            )

            Spacer(modifier = Modifier.height(30.dp))

            ContinueButton(
                text = stringResource(id = R.string.continuee),
                onClick = onClick,
                enable = usernameState.text.isNotEmpty() && passwordState.text.isNotEmpty() && !loginState.isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            SignUpInText(
                onClick = { onSignUpClick() },
                text = R.string.dont_have_an_account,
                signUpText = R.string.sign_up
            )


        }

    }

}


@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreen(
        onClick = { /*TODO*/ },
        onSignUpClick = { /*TODO*/ },
        onForgotClick = { /*TODO*/ },
    )

}
