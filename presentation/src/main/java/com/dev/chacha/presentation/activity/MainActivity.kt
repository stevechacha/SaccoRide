package com.dev.chacha.presentation.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.bottomnav.BottomNavigationBar
import com.dev.chacha.presentation.common.navigation.HomeNavGraph
import com.dev.chacha.presentation.common.navigation.RootNavGraph
import com.dev.chacha.presentation.common.theme.SaccoRideTheme
import com.dev.chacha.presentation.common.theme.Theme
import com.dev.chacha.presentation.fingerprint.Biometric
import com.dev.chacha.presentation.theme.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity(), Biometric.AuthListener {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val viewModel: ThemeViewModel = hiltViewModel()
            val themeValue by viewModel.theme.collectAsState(
                initial = Theme.FOLLOW_SYSTEM.themeValue,
                context = Dispatchers.Main.immediate
            )
            SaccoRideTheme(theme = themeValue) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    RootNavGraph(navController = navController)

                    lifecycleScope.launchWhenStarted {
//                        BiometricHelper(this@MainActivity,this@MainActivity).activity
                        Biometric(
                            this@MainActivity,
                            navController,
                            this@MainActivity,
                        ).authenticate()
                    }

                }

            }

        }
    }


    override fun onAuthSuccess() {
        Timber.e("Message : Auth Success")
    }

    override fun onAuthFailure() {
        Timber.e("Error : Auth Failure")

    }

    fun onAuthError(error: String) {
        Timber.e("Error : $error")
    }
}

