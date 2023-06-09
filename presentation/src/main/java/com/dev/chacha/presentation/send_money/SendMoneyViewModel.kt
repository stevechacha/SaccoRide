package com.dev.chacha.presentation.send_money

import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SendMoneyViewModel : ViewModel() {
    @OptIn(ExperimentalMaterialApi::class)
    private val _sheetState = mutableStateOf(BottomSheetValue.Collapsed)
    @OptIn(ExperimentalMaterialApi::class)
    val sheetState: State<BottomSheetValue> = _sheetState

    val sendMoneyState = MutableStateFlow(SendMoneyState())


    fun onSendMoneyEvent(event: SendMoneyEvent){
        when(event){
            is SendMoneyEvent.AmountChanged -> {
                sendMoneyState.update { it.copy(amount = event.amount) }
            }
            SendMoneyEvent.CollapseSheet -> {
                sendMoneyState.update { it.copy(expanded = false) }
                sendMoneyState.update { it.copy(sheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed)) }
            }
            SendMoneyEvent.ExpandSheet -> {
                sendMoneyState.update { it.copy(expanded = true) }
                sendMoneyState.update { it.copy(sheetState = BottomSheetState(initialValue = BottomSheetValue.Expanded)) }
            }
            is SendMoneyEvent.PhoneNumberChanged -> {
                sendMoneyState.update { it.copy(phoneNumber = event.phoneNumber) }
            }
            is SendMoneyEvent.RecipientProviderSelected ->{
                sendMoneyState.update { it.copy(selectedRecipient = event.selectedRecipient) }
            }
        }
    }



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