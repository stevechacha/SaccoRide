package com.dev.chacha.presentation.setting

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.chacha.domain.repo.ThemeRepository
import com.dev.chacha.presentation.common.theme.Theme
import com.dev.chacha.presentation.theme.ThemeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themeRepository: ThemeRepository
) : ViewModel() {

    private val _currentTheme = mutableStateOf(Theme.LIGHT_THEME.themeValue)
    val currentTheme: Int =_currentTheme.value
    /*val currentTheme: Int
        get() = _currentTheme.value*/



    fun updateTheme(themeValue: Int) {
        viewModelScope.launch {
            themeRepository.setTheme(themeValue = themeValue)
            _currentTheme.value = themeValue
        }
    }



}