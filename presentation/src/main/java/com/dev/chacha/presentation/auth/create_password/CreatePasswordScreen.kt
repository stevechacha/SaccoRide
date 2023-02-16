package com.dev.chacha.presentation.auth.create_password

import androidx.compose.foundation.layout.*
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
import com.dev.chacha.presentation.common.components.AppOutlinedTextField
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.theme.SaccoRideTheme


@Composable
fun CreatePasswordScreen(
    onClickAction: () -> Unit
) {
    CreatePasswordContent(
        onClickAction = onClickAction,
        viewModel = CreatePasswordViewModel()
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePasswordContent(
    onClickAction: () -> Unit,
    viewModel: CreatePasswordViewModel
) {
    val (password, setPassword) = rememberSaveable { mutableStateOf("") }
    val (confirmPassword, setConfirmPassword) = rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Create Password",
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
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {

                    AppOutlinedTextField(
                        value = password,
                        onValueChange = { setPassword(it) },
                        keyboardType = KeyboardType.Password,
                        hint = stringResource(id = R.string.password),
                        error = viewModel.passwordError.value,
                        isPasswordVisible = viewModel.showPassword.value,
                        onPasswordToggleClick = {
                            viewModel.setShowPassword(it)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AppOutlinedTextField(
                        value = password,
                        onValueChange = { setConfirmPassword(it) },
                        keyboardType = KeyboardType.Password,
                        hint = stringResource(id = R.string.confirmPassword),
                        error = viewModel.passwordError.value,
                        isPasswordVisible = viewModel.showConfirmPassword.value,
                        onPasswordToggleClick = {
                            viewModel.setShowConfirmPassword(it)
                        }
                    )

                    /* Button */

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {

                        Button(
                            onClick = { onClickAction() },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium,
                        ) {
                            Text(
                                text = "Continue",
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
fun CreatePasswordScreenPreview() {
    SaccoRideTheme {
        CreatePasswordContent(
            onClickAction = { /*TODO*/ },
            viewModel = CreatePasswordViewModel()
        )
    }
}
