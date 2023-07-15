package com.dev.chacha.presentation.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.fingerprint.Biometric
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.P)
@AndroidEntryPoint
class MainActivity : ComponentActivity(), Biometric.AuthListener {
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
                Biometric(this@MainActivity, navController, this@MainActivity).authenticate()
            }
            lifecycleScope.launchWhenResumed {
                Biometric(this@MainActivity, navController, this@MainActivity).authenticate()

            }.isCancelled
            lifecycleScope.launchWhenCreated {
                Biometric(this@MainActivity, navController, this@MainActivity).authenticate()
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


