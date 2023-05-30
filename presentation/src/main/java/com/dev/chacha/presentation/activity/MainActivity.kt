package com.dev.chacha.presentation.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.common.theme.SaccoRideTheme
import com.dev.chacha.presentation.common.theme.Theme
import com.dev.chacha.presentation.fingerprint.Biometric
import com.dev.chacha.presentation.theme.ThemeViewModel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity(),Biometric.AuthListener {
    @OptIn(ExperimentalMaterialNavigationApi::class)
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MainScreen()
            val bottomSheetNavigator = rememberBottomSheetNavigator()
            val navController = rememberNavController(bottomSheetNavigator)
            lifecycleScope.launchWhenStarted {
                Biometric(
                    this@MainActivity,
                    navController,
                    this@MainActivity,
                ).authenticate()
            }
        }

    }

    override fun onAuthSuccess() {
        TODO("Not yet implemented")
    }

    override fun onAuthFailure() {
        TODO("Not yet implemented")
    }
}


