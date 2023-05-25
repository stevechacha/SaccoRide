package com.dev.chacha.presentation.pin

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.theme.Brutalista
import com.dev.chacha.presentation.common.theme.PrimaryColor
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun LoanPinScreen(
    onClickAction: () -> Unit,
    navController: NavController
) {
    val inputPin = remember { mutableStateListOf<Int>() }
    val error = remember { mutableStateOf<String>("") }
    val showSuccess = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val pinInputsViewModel: PinInputsViewModel = viewModel()
    val interactionSource = remember { MutableInteractionSource() }
    val pinSizes = remember { mutableStateOf<Int>(4) }
    val pin = inputPin.joinToString("") { it.toString() }
    val name = "Stephen Chacha"


    if (inputPin.size == 4) {
        LaunchedEffect(true) {
            delay(100)
            if (inputPin.joinToString("") == password) {
                showSuccess.value = true
                delay(200)
                onClickAction()
            } else {
                inputPin.clear()
                error.value = "Wrong pin, Please retry!"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp) ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name.uppercase(),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = stringResource(id = R.string.enter_pin),
                    style = MaterialTheme.typography.labelSmall

                )
                if (showSuccess.value) {
                    LottieLoadingView(
                        context = context,
                        file = "success.json",
                        iterations = 1,
                        modifier = Modifier.size(100.dp)
                    )
                } else {

                    PinTextField(
                        value = pin,
                        onValueChange = { newValue ->
                            if (newValue.length <= pinSize) {
                                if (newValue.isNotEmpty()) {
                                    inputPin.addAll(newValue.map { it.toString().toInt() })
                                } else {
                                    inputPin.clear()
                                }
                            } else {
                                inputPin.clear()
                            }
                        },
                        keyboardType = KeyboardType.NumberPassword,
                        hint = stringResource(id = R.string.confirmPassword),
                        isPasswordVisible = pinInputsViewModel.showPin.value,
                        onPasswordToggleClick = {
                            pinInputsViewModel.setShowPin(it)
                        },
                        maxLength = 4,
                        interactionSource = interactionSource,

                    )
                }

            }

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp, top = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    (1..3).forEach {
                        PinKeyItem(
                            onClick = { inputPin.add(it) },
                            enabled = inputPin.size < 4 // Disable keys after reaching 4 digits
                        ) {
                            Text(
                                text = it.toString(),
                                fontSize = 22.sp,
                                fontFamily = Brutalista,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    (4..6).forEach {
                        PinKeyItem(
                            onClick = { inputPin.add(it) },
                            enabled = inputPin.size < 4 // Disable keys after reaching 4 digits

                        ) {
                            Text(
                                text = it
                                    .toString(),
                                fontSize = 22.sp,
                                fontFamily = Brutalista,
                                fontWeight = FontWeight.Bold

                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    (7..9).forEach {
                        PinKeyItem(
                            onClick = { inputPin.add(it) },
                            enabled = inputPin.size < 4 // Disable keys after reaching 4 digits

                        ) {
                            Text(
                                text = it.toString(),
                                fontSize = 22.sp,
                                fontFamily = Brutalista,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(4.dp),
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PinKeyItem(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Backspace,
                            contentDescription = "Clear",
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    if (inputPin.isNotEmpty()) {
                                        inputPin.removeLast()
                                    }
                                }
                        )
                    }

                    PinKeyItem(
                        onClick = { inputPin.add(0) },
                        enabled = inputPin.size < 4 // Disable keys after reaching 4 digits

                    ) {
                        Text(
                            text = "0",
                            modifier = Modifier.padding(4.dp),
                            fontSize = 22.sp,
                            fontFamily = Brutalista,
                            fontWeight = FontWeight.Bold

                        )
                    }
                    PinKeyItem(onClick = { /*TODO*/ }) {
                        TextButton(
                            onClick = { /*TODO*/ },
                            enabled = inputPin.isNotEmpty() && inputPin.size == 4 // Enable only when there are 4 digits

                        ) {
                            Text(
                                text = "OK",
                                color = if (inputPin.isEmpty() || inputPin.size<4) Color.Gray else PrimaryColor,
                                fontSize = 22.sp,
                                fontFamily = Brutalista,
                                fontWeight = FontWeight.Bold,
                            )
                        }

                    }

                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
@Preview
fun LoanPinScreenPreview() {
    LoanPinScreen(
        onClickAction = { },
        navController = rememberNavController()
    )

}