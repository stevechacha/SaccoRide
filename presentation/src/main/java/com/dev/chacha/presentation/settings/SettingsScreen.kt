package com.dev.chacha.presentation.settings

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.account.component.AccountCard
import com.dev.chacha.presentation.account.component.AccountCards
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.theme.Theme
import com.dev.chacha.presentation.theme.ThemeViewModel
import com.dev.chacha.presentation.theme.component.ThemeCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navigateToTheme: () -> Unit,
    navigateToBiometricSettings: () -> Unit,
    navigateBack: () -> Unit
) {

    val selectedTheme by viewModel.currentTheme.collectAsState()
    val themeViewModel: ThemeViewModel = hiltViewModel()
    val themeName by themeViewModel.theme.collectAsState(
        initial = Theme.LIGHT_THEME.themeName,
        context = Dispatchers.Main.immediate
    )
    val context = LocalContext.current
    val themes = listOf(
        Theme.LIGHT_THEME,
        Theme.DARK_THEME,
        Theme.FOLLOW_SYSTEM,
        Theme.MATERIAL_YOU
    )
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
                        title = when(themeName){
                            Theme.LIGHT_THEME.themeName -> "Light"
                            Theme.DARK_THEME.themeName -> "Dark"
                            Theme.FOLLOW_SYSTEM.themeName -> "System"
                            Theme.MATERIAL_YOU.themeName -> "Material You"
                            else -> { "System"}
                        },
                        icon = R.drawable.insights_icon,
                    )
                }
                item {
                    AccountCard(
                        onClick = {
                            navigateToBiometricSettings()
                        },
                        title = "Biometric",
                        icon = R.drawable.fingerprint_icon,
                    )
                }

                item {
                    Text(
                        text = "Date",
                        modifier = Modifier.clickable {

                        }
                    )
                }

            }
        }

    }

}

