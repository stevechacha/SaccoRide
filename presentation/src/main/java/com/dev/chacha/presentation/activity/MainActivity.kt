package com.dev.chacha.presentation.activity

import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.biometric.BiometricHelper
import com.dev.chacha.presentation.bottomnav.BottomNavigationBar
import com.dev.chacha.presentation.common.navigation.HomeNavGraph
import com.dev.chacha.presentation.common.navigation.RootNavGraph
import com.dev.chacha.presentation.common.theme.SaccoRideTheme
import com.dev.chacha.presentation.fingerprint.BiometricChecker
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity(),BiometricChecker.AuthListener {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            com.dev.chacha.presentation.common.theme.SaccoRideTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    BiometricLoginScreen(onClick = { biometricHelper.authenticate() })
                    val navController = rememberNavController()
                    RootNavGraph(navController = navController)
                    showBiometricPrompt()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun showBiometricPrompt() = lifecycleScope.launchWhenStarted{
        BiometricChecker(this@MainActivity,this@MainActivity).authenticate()
    }

    override fun onAuthSuccess(message: String) {
        Timber.e("Message : $message")
    }

    override fun onAuthError(error: String) {
        Timber.e("Error : $error")
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { if (bottomBarState.value) BottomNavigationBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            HomeNavGraph(
                navController = navController,
                showBottomBar = { bottomBarState.value = it },
            )

        }
    }
}