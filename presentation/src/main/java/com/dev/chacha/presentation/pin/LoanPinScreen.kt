package com.dev.chacha.presentation.pin

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay

@OptIn(ExperimentalCoroutinesApi::class)
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
    val pinState by pinInputsViewModel.pinState.collectAsState()
    val interactionSource = remember { MutableInteractionSource() }
    val pinSizes = remember { mutableStateOf<Int>(4) }
    val pin = inputPin.joinToString("") { it.toString() }
    val name = "Stephen Chacha"


    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var networkCapabilities by remember {
        mutableStateOf(
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        )
    }
    var isNetworkAvailable by remember {
        mutableStateOf(
            networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        )
    }
    var isWifiConnected by remember {
        mutableStateOf(
            networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
        )
    }
    var isCellularConnected by remember {
        mutableStateOf(
            networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true
        )
    }

    DisposableEffect(Unit) {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                isNetworkAvailable =
                    networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
                isWifiConnected =
                    networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
                isCellularConnected =
                    networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                isNetworkAvailable = false
                isWifiConnected = false
                isCellularConnected = false
            }
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback)

        onDispose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

    if (!isNetworkAvailable) {
        AlertDialog(
            onDismissRequest = { /* Handle dismiss */ },
            title = { Text(text = "No Internet Connection") },
            text = { Text(text = "Please check your internet connection and try again.") },
            confirmButton = {
                TextButton(onClick = { navController.navigateUp()}) {
                    Text(
                        text = "Retry",
                    )
                }
            }
        )
        return
    }





    if (inputPin.size == 4) {
        LaunchedEffect(true) {
            delay(100)
            if (inputPin.joinToString("") == password) {
                showSuccess.value = true
                delay(200)
                onClickAction()
            } else {
                inputPin.clear()
                pinState.error

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
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = stringResource(id = R.string.enter_your_loan),
                    style = MaterialTheme.typography.labelSmall

                )
                Spacer(modifier = Modifier.height(12.dp))

                if (pinState.isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                } else if(pinState.isSuccess) {
                    LottieLoadingView(
                        context = context,
                        file = "success.json",
                        iterations = 1,
                        modifier = Modifier.size(100.dp)
                    )

                } else if (pinState.error.isNotEmpty()) {
                    AlertDialog(
                        onDismissRequest = { /* Handle dismiss */ },
                        title = { Text(text = "Error") },
                        confirmButton = {
                            TextButton(onClick = { /* Handle retry */ }) {
                                Text(text = "Retry")
                            }
                        }
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
                        hint = stringResource(id = R.string.pin),
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
                    .align(Alignment.Center)
                    .padding(top = 10.dp)
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