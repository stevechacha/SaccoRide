package com.dev.chacha.presentation.theme

import com.dev.chacha.presentation.common.theme.Theme

data class ThemeState(
    val currentTheme: Int = Theme.LIGHT_THEME.themeValue
)
