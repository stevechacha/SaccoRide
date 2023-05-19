package com.dev.chacha.presentation.send_money

import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

class SendMoneyViewModel : ViewModel() {
    @OptIn(ExperimentalMaterialApi::class)
    private val _sheetState = mutableStateOf(BottomSheetValue.Collapsed)
    @OptIn(ExperimentalMaterialApi::class)
    val sheetState: State<BottomSheetValue> = _sheetState

    private val _mobileNumber = mutableStateOf("")
    val mobileNumber: State<String> = _mobileNumber

    private val _amount = mutableStateOf("")
    val amount: State<String> = _amount

    private val _selectedRecipient = mutableStateOf(RecipientProvider.Mpesa)
    val selectedRecipient: State<RecipientProvider> = _selectedRecipient

    private val _phoneNumber = mutableStateOf("")
    val phoneNumber: State<String> = _phoneNumber

    private val _coroutineScope = viewModelScope
    val coroutineScope: CoroutineScope = _coroutineScope

    @OptIn(ExperimentalMaterialApi::class)
    fun updateSheetState(state: BottomSheetValue) {
        _sheetState.value = state
    }

    fun updateMobileNumber(number: String) {
        _mobileNumber.value = number
    }

    fun updateAmount(amount: String) {
        _amount.value = amount
    }

    fun updateSelectedRecipient(recipient: RecipientProvider) {
        _selectedRecipient.value = recipient
    }

    fun updatePhoneNumber(number: String) {
        _phoneNumber.value = number
    }

}