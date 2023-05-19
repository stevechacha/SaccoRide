package com.dev.chacha.presentation.buy_artime

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BuyAirtimeViewModel : ViewModel(){
    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber = _phoneNumber.asStateFlow()

    private val _amount = MutableStateFlow("")
    val amount = _amount.asStateFlow()

    private val _mobileNumber = MutableStateFlow("")
    val mobileNumber = _mobileNumber.asStateFlow()

    private val _isButtonEnabled = MutableStateFlow(false)
    val isButtonEnabled: StateFlow<Boolean> = _isButtonEnabled.asStateFlow()

    private val _targetRadio = MutableStateFlow("myself")
    var targetRadio =_targetRadio.asStateFlow().value

    init {
        validateInput()
    }

    private fun validateInput() {
        val isAmountValid = amount.value.trim().isNotEmpty() && amount.value.trim().toIntOrNull() != null && amount.value.trim().toInt() in 6..9999
        val areDetailsFilled = when (targetRadio) {
            "myself" -> true
            "someone_else" -> phoneNumber.value.trim().isNotEmpty()
            else -> false
        }
        _isButtonEnabled.value = isAmountValid && areDetailsFilled

    }

    fun updatePhoneNumber(newPhoneNumber: String) {
        val sanitizedPhoneNumber = newPhoneNumber.replace("-", "").trim()
        _phoneNumber.value = sanitizedPhoneNumber
        validateInput()

    }

    fun updateAmount(newAmount: String) {
        val sanitizedAmount = newAmount.replace("-", "").trim()
        _amount.value = sanitizedAmount
        validateInput()

    }

    fun updateTargetRadio(newTargetRadio: String) {
        targetRadio = newTargetRadio
        validateInput()
    }




}