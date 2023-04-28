package com.dev.chacha.presentation.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.theme.Theme
import com.dev.chacha.presentation.setting.SettingsViewModel
import com.dev.chacha.presentation.theme.component.ThemeItem

@Composable
@Preview
fun ThemeS() {
    val viewModel: SettingsViewModel = hiltViewModel()

    val selectedTheme = remember { mutableStateOf(viewModel.currentTheme) }
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Theme Setting",
                showBackArrow = true
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Choose your preferred theme ",
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(12.dp))
            ThemeScreen(
                onSelectTheme = { themeValue ->
                    selectedTheme.value = themeValue
                    viewModel.updateTheme(themeValue = themeValue)
                },
                selectedTheme = selectedTheme.value

            )
        }

    }

}

@Composable
fun ThemeScreen(
    onSelectTheme: (Int) -> Unit,
    selectedTheme: Int,
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        ThemeItem(
            themeName = "System Default",
            themeValue = Theme.FOLLOW_SYSTEM.themeValue,
            onSelectTheme = onSelectTheme,
            isSelected = selectedTheme == Theme.FOLLOW_SYSTEM.themeValue
        )
        ThemeItem(
            themeName = "Light theme",
            themeValue = Theme.LIGHT_THEME.themeValue,
            onSelectTheme = onSelectTheme,
            isSelected = selectedTheme == Theme.LIGHT_THEME.themeValue

        )
        ThemeItem(
            themeName = "Dark theme",
            themeValue = Theme.DARK_THEME.themeValue,
            onSelectTheme = onSelectTheme,
            isSelected = selectedTheme == Theme.DARK_THEME.themeValue

        )
        ThemeItem(
            themeName = "Material You",
            themeValue = Theme.MATERIAL_YOU.themeValue,
            onSelectTheme = onSelectTheme,
            isSelected = selectedTheme == Theme.MATERIAL_YOU.themeValue

        )

    }

}