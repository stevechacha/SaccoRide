package com.dev.chacha.presentation.setting

import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.account.component.AccountCard
import com.dev.chacha.presentation.fingerprint.Biometrics
import com.dev.chacha.presentation.setting.component.FingerPrints


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navigateToTheme: ()->Unit
) {
    val isFingerprintEnabled = remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }
    val context = LocalContext.current


    val navController = rememberNavController()
    val biometric = remember {
        Biometrics(
            authListener = object : Biometrics.AuthListener {
                override fun onAuthSuccess(message: String) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }

                override fun onAuthError(error: String) {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            },
            navController = navController,
            activity = context as ComponentActivity,
            checked = checked
        )
    }

    Scaffold(
        topBar = {
            AppToolbar(
                title = "Setting ",
                showBackArrow = true
            )
        }
    ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize()){
            LazyColumn(
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                item {
                    AccountCard(
                        onClick = {
                          navigateToTheme()
                        } ,
                        title = "Theme",
                        icon = R.drawable.insights_icon
                    )
                }
                item {
                    FingerPrints(
                        onClick = {},
                        title = "Enable FingerPrint Lock" ,
                        icon = R.drawable.fingerprint_icon,

                    )
                }

            }
        }

    }

}

