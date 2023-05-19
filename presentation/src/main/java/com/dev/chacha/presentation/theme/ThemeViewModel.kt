package com.dev.chacha.presentation.theme

import androidx.lifecycle.ViewModel
import com.dev.chacha.domain.repo.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
     themeRepository: ThemeRepository
): ViewModel() {
    val theme = themeRepository.themeStream


}