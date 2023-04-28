
package com.dev.chacha.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.dev.chacha.data.preferences.ThemePreferences
import com.dev.chacha.domain.repo.ThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {

    @Provides
    @Singleton
    fun provideThemePreferences(dataStore: DataStore<Preferences>) =
        ThemePreferences(dataStore)

    @Provides
    @Singleton
    fun provideThemeRepository(themePreferences: ThemePreferences): ThemeRepository {
        return com.dev.chacha.data.repo.ThemeRepositoryImpl(themePreferences = themePreferences)
    }
}
