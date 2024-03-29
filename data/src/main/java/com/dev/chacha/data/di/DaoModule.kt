package com.dev.chacha.data.di

import android.content.Context
import androidx.room.Room
import com.dev.chacha.data.db.SaccoDatabase
import com.dev.chacha.data.preferences.ThemePreferences
import com.dev.chacha.domain.repo.ThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {


}