package com.dev.chacha.data.repo

import com.dev.chacha.data.preferences.ThemePreferences
import com.dev.chacha.domain.repo.ThemeRepository
import kotlinx.coroutines.flow.Flow

class ThemeRepositoryImpl(
    private val themePreferences: ThemePreferences
) : ThemeRepository {
    override val themeStream: Flow<Int>
        get() = themePreferences.getTheme

    override suspend fun setTheme(themeValue: Int) {
        themePreferences.saveTheme(themeValue = themeValue)
    }
}


