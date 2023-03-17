package com.dev.chacha.presentation.onboarding

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OnBoardingViewModel : ViewModel(){
    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> get() = _currentPage

    fun setCurrentPage(currentPage: Int) {
        _currentPage.value = currentPage
    }
}