package com.dev.chacha.presentation.setting

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.account.component.AccountCard
import com.dev.chacha.presentation.common.components.AppToolbar


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navigateToTheme: () -> Unit,
    navigateToBiometricSettings: () -> Unit,
    navigateBack: () -> Unit
) {

    Scaffold(
        topBar = {
            AppToolbar(
                title = "App Settings ",
                showBackArrow = true,
                navigateBack = {navigateBack()}
            )
        }
    ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    AccountCard(
                        onClick = {
                            navigateToTheme()
                        },
                        title = "Theme",
                        icon = R.drawable.insights_icon
                    )
                }
                item {

                    AccountCard(
                        onClick = {
                            navigateToBiometricSettings()
                        },
                        title = "Enable FingerPrint Lock",
                        icon = R.drawable.fingerprint_icon,
                    )
                }

            }
        }

    }

}

