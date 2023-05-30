package com.dev.chacha.presentation.fingerprint

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dev.chacha.presentation.common.navigation.DestinationGraph

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BiometricScreen(navController: NavController) {
    val context = LocalContext.current

    val biometricChecker = remember { BiometricChecker(authListener = object : BiometricChecker.AuthListener {
        override fun onAuthSuccess(message: String) {
            // If authentication is successful, navigate to another screen
            navController.navigate(DestinationGraph.HOME_SCREEN_ROUTE)
        }

        override fun onAuthError(error: String) {
            // Handle authentication error
        }
    }, activity = context as  ComponentActivity, navController = navController) }

    // UI layout for biometric authentication screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display biometric authentication message
        Text(text = "Please authenticate with biometrics.")

        // Authenticate the user
        biometricChecker.authenticate()
    }
}
