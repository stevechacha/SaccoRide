package com.dev.chacha.presentation.auth.reset_pin

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
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.auth.create_password.CreatePasswordViewModel
import com.dev.chacha.presentation.common.components.AppOutlinedTextField
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.theme.SaccoRideTheme

@Composable
fun ResetPinScreen(
    onClickAction: () -> Unit
) {
    ResetPinContent(
        viewModel = ResetPinViewModel(),
        onClickAction = onClickAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPinContent(
    onClickAction: () -> Unit,
    viewModel: ResetPinViewModel
) {
    val (pin, setPin) = rememberSaveable { mutableStateOf("") }
    val (newPin, setNewPin) = rememberSaveable { mutableStateOf("") }
    val (confirmNewPin, setConfirmNewPin) = rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Reset pin",
                showBackArrow = true
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Reset your pin",
                fontSize = 24.sp
            )

            AppOutlinedTextField(
                value = pin,
                onValueChange = { setPin(it) },
                hint = stringResource(id = R.string.current_pin),
                keyboardType = KeyboardType.NumberPassword,
                isPasswordVisible = viewModel.showPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowPassword(it)
                }
            )

            AppOutlinedTextField(
                value = newPin,
                onValueChange = { setNewPin(it) },
                keyboardType = KeyboardType.NumberPassword,
                hint = stringResource(id = R.string.new_pin),
                isPasswordVisible = viewModel.showPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowPassword(it)
                }
            )

            AppOutlinedTextField(
                value = confirmNewPin,
                onValueChange = { setConfirmNewPin(it) },
                keyboardType = KeyboardType.NumberPassword,
                hint = stringResource(id = R.string.confirmNewPin),
                isPasswordVisible = viewModel.showConfirmPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowConfirmPassword(it)
                }
            )
            
            Spacer(modifier = Modifier.height(12.dp))

            ContinueButton(
                text = stringResource(id = R.string.continuee),
                onClick = onClickAction,
                enable = pin.isNotEmpty() && newPin.isNotEmpty() && confirmNewPin.isNotEmpty() && newPin == confirmNewPin
            )
            
        }
    }
}

@Composable
@Preview
fun ResetPasswordScreenPreview() {
        ResetPinScreen(
            onClickAction = {}
        )

}
