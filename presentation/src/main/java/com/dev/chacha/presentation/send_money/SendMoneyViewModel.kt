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

            }
            SendMoneyEvent.ExpandSheet -> {

            }
            is SendMoneyEvent.PhoneNumberChanged -> {
                sendMoneyState.update { it.copy(phoneNumber = event.phoneNumber) }
            }
            is SendMoneyEvent.RecipientProviderSelected ->{
                sendMoneyState.update { it.copy(selectedRecipient = event.selectedRecipient) }
            }
        }
    }

}