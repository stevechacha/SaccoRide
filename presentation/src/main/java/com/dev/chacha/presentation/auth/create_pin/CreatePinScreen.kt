package com.dev.chacha.presentation.auth.create_pin

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
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppOutlinedTextField


@Composable
fun CreatePinScreen(
    onClickAction: () -> Unit
) {
    CreatePinContent(
        onClickAction = onClickAction,
        viewModel = CreatePinViewModel()
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePinContent(
    onClickAction: () -> Unit,
    viewModel: CreatePinViewModel
) {
    val (pin, setPin) = rememberSaveable { mutableStateOf("") }
    val (confirmPin, setConfirmPin) = rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Create Pin",
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
                        value = pin,
                        onValueChange = { setPin(it) },
                        hint = stringResource(id = R.string.pin_hint),
                        keyboardType = KeyboardType.NumberPassword,
                        error = viewModel.passwordError.value,
                        isPasswordVisible = viewModel.showPassword.value,
                        onPasswordToggleClick = {
                            viewModel.setShowPassword(it)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AppOutlinedTextField(
                        value = confirmPin,
                        onValueChange = { setConfirmPin(it) },
                        keyboardType = KeyboardType.NumberPassword,
                        hint = stringResource(id = R.string.confirmPin),
                        error = viewModel.passwordError.value,
                        isPasswordVisible = viewModel.showPassword.value,
                        onPasswordToggleClick = {
                            viewModel.setShowPassword(it)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

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
fun CreatePinScreenPreview() {
    CreatePinContent(
        onClickAction = { /*TODO*/ },
        viewModel = CreatePinViewModel()
    )
}

