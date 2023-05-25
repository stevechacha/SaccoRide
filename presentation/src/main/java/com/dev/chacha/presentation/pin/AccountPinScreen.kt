package com.dev.chacha.presentation.pin

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
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
fun AccountPinScreen(
    onClickAction: () -> Unit,
    navController: NavController
) {
    val inputPin = remember { mutableStateListOf<Int>() }
    val error = remember { mutableStateOf<String>("") }
    val showSuccess = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val pinInputsViewModel: PinInputsViewModel = viewModel()
    val pin = inputPin.joinToString("") { it.toString() }


    LaunchedEffect(key1 = inputPin) {
        if (inputPin.size == 4) {
            delay(100)
            if (inputPin.joinToString("") == password) {
                showSuccess.value = true
                delay(200)
            }
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter =  painterResource(id = R.drawable.account_circle),
                            contentDescription = null,
                            tint = PrimaryColor,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(40.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

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
                                    if (newValue.isEmpty()) {
                                        inputPin.addAll(newValue.map { it.toString().toInt() })
                                    }
                                }
                            },
                            keyboardType = KeyboardType.NumberPassword,
                            hint = stringResource(id = R.string.enter_pin),
                            isPasswordVisible = pinInputsViewModel.showPin.value,
                            onPasswordToggleClick = {
                                pinInputsViewModel.setShowPin(it)
                            },
                        )
                    }
                    Text(
                        text = error.value,
                        color = MaterialTheme.colorScheme.error,
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                }

                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 20.dp)
                        .align(Alignment.Center)
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
                        PinKeyItem(onClick = {
                            if (inputPin.isNotEmpty()) {
                                inputPin.removeLast()
                            }
                        }) {
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
                            enabled = inputPin.size < 4 // Disable key after reaching 4 digits
                        ) {
                            Text(
                                text = "0",
                                modifier = Modifier.padding(4.dp),
                                fontSize = 22.sp,
                                fontFamily = Brutalista,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        PinKeyItem(onClick = {
                            if (inputPin.joinToString("") == password) {
                                onClickAction()
                            } else {
                                inputPin.clear()
                                error.value = "Wrong pin, Please retry!"
                            }
                        }) {
                            TextButton(
                                onClick = {
                                    if (inputPin.joinToString("") == password) {
                                        onClickAction()
                                    } else {
                                        inputPin.clear()
                                        error.value = "Wrong pin, Please retry!"
                                    }
                                },
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

}


@RequiresApi(Build.VERSION_CODES.P)
@Composable
@Preview
fun AccountPinScreenPreview() {
    AccountPinScreen(
        onClickAction = { },
        navController = rememberNavController()
    )

}