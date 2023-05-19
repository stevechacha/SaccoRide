package com.dev.chacha.data.preferences

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.dev.chacha.data.util.Constants.THEME_OPTIONS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemePreferences(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun saveTheme(themeValue: Int) {
        dataStore.edit { preferences ->
            preferences[THEME_OPTIONS] = themeValue
        }
    }

    val getTheme: Flow<Int> = dataStore.data.map { preferences ->
        preferences[THEME_OPTIONS] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

}


