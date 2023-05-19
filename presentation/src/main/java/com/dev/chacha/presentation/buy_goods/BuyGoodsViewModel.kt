package com.dev.chacha.presentation.buy_goods

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BuyGoodsViewModel : ViewModel() {
    private val _state = MutableStateFlow(BuyGoodsState())
    val state: StateFlow<BuyGoodsState> = _state

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    fun onTillNumberChanged(tillNumber: String) {
        _state.value = state.value.copy(tillNumber = tillNumber)
    }

    fun onTillNameChanged(tillName: String) {
        _state.value = state.value.copy(tillName = tillName)
    }

    fun onAmountChanged(amount: String) {
        _state.value = state.value.copy(amount = amount)
    }

    fun onDialogDismissed() {
        _showDialog.value = false
    }


    fun isInputValid(): Boolean {
        val currentState = state.value
        val tillNumber = currentState.tillNumber
        val amount = currentState.amount

        val isValidTillNumber = tillNumber.isNotEmpty() && tillNumber.length > 5
        val isValidAmount =
            amount.isNotEmpty() && amount.toDouble() > 0 && amount.toDouble() < 300000

        return isValidTillNumber && isValidAmount
    }


    fun onContinueButtonClicked() {
        val currentState = state.value
        val tillNumber = currentState.tillNumber
        val amount = currentState.amount

        val isValidTillNumber = tillNumber.isNotEmpty() && tillNumber.length > 5
        val isValidAmount =
            amount.isNotEmpty() && amount.toDouble() > 0 && amount.toDouble() < 300000

        if (isInputValid()) {
            _showDialog.value = true
        }

        if (isValidTillNumber && isValidAmount) {
            _showDialog.value = true
        } else {
            // Show an error message or handle invalid input
        }
    }


}




