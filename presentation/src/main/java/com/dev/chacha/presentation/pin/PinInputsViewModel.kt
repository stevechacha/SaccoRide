package com.dev.chacha.presentation.pin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PinInputsViewModel : ViewModel() {
    private val _showPin = mutableStateOf(false)
    val showPin: State<Boolean> = _showPin

    fun setShowPin(showPin: Boolean) {
        _showPin.value = showPin
    }


}