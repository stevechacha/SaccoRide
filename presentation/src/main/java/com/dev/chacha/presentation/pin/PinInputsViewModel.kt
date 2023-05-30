package com.dev.chacha.presentation.pin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dev.chacha.presentation.contactList.ContactState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PinInputsViewModel : ViewModel() {

    private val _pinState = MutableStateFlow(PinState())
    val pinState: StateFlow<PinState> = _pinState

    private val _showPin = mutableStateOf(false)
    val showPin: State<Boolean> = _showPin

    fun setShowPin(showPin: Boolean) {
        _showPin.value = showPin
    }


}