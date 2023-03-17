package com.dev.chacha.presentation.auth.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.theme.SaccoRideTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.chacha.presentation.common.components.AppOutlinedTextField
import com.dev.chacha.presentation.common.theme.PrimaryColor

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
                title = "Login",
                showBackArrow = true,
                showForwardArrow = true
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(vertical = 16.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val text by rememberSaveable { mutableStateOf("") }
            val charLimit = 10

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    AppOutlinedTextField(
                        value = usernameState.text,
                        onValueChange = {
                            viewModel.setUsername(it)
                        },
                        keyboardType = KeyboardType.Email,
                        hint = stringResource(id = R.string.email_or_mobile_number),
                        trailingIcon = {
                            IconButton(onClick = { expanded = true }) {
                                Icons.Filled.Home
                            }
                        },
                        supportingText = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Limit: ${usernameState.text.length}/$charLimit",
                                textAlign = TextAlign.End,
                            )
                        }
                    )


                    if (usernameState.error != "") {
                        Text(
                            text = usernameState.error ?: "",
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
                        leadingIcon = Icons.Default.Home

                    )
                    if (passwordState.error != "") {
                        Text(
                            text = passwordState.error ?: "",
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    Text(
                        modifier = Modifier.clickable { onForgotClick() },
                        text = "Forgot Password?",
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.End,
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        OutlinedButton(
                            onClick = { onSignUpClick() },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.outlinedButtonColors(),
                            shape = RoundedCornerShape(8),
                            border = BorderStroke(1.dp, PrimaryColor)
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                textAlign = TextAlign.Center,
                                text = "Register",
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                viewModel.login()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(),
                            shape = RoundedCornerShape(8)
                        ) {
                            Text(
                                text = "Enter password to sign in",
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(30.dp))

                    }

                }

            }

        }

    }

}

@Composable
@Preview
fun LoginScreenPreview() {
    SaccoRideTheme {
        LoginScreen(
            onClick = { /*TODO*/ },
            onSignUpClick = { /*TODO*/ },
            onForgotClick = { /*TODO*/ },
        )

    }
}
