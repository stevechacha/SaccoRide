package com.dev.chacha.presentation.setting

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.chacha.domain.repo.ThemeRepository
import com.dev.chacha.presentation.common.theme.Theme
import com.dev.chacha.presentation.theme.ThemeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(InternalCoroutinesApi::class)
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themeRepository: ThemeRepository
) : ViewModel() {

    private val _currentTheme = MutableStateFlow(Theme.FOLLOW_SYSTEM.themeValue)
    val currentTheme: StateFlow<Int> = _currentTheme

    init {
        viewModelScope.launch {
            themeRepository.themeStream
                .stateIn(viewModelScope, SharingStarted.Lazily, 0)
                .combine(_currentTheme) { themeValue, currentTheme ->
                    if (themeValue != currentTheme) {
                        _currentTheme.value = themeValue
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    fun updateTheme(themeValue: Int) {
        viewModelScope.launch {
            themeRepository.setTheme(themeValue)
        }
    }


}