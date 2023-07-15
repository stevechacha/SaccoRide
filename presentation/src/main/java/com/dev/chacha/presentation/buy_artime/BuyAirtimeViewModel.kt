package com.dev.chacha.presentation.buy_artime

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BuyAirtimeViewModel : ViewModel(){

    val buyAirtimeState = MutableStateFlow(BuyAirtimeState())
    private val _targetRadio = MutableStateFlow("myself")
    var targetRadio =_targetRadio.asStateFlow().value

    init {
        validateInput()
    }

    fun handleBuyAirtimeEvent(buyAirtimeEvent: BuyAirtimeEvent) {
        when(buyAirtimeEvent){
            is BuyAirtimeEvent.AmountChanged -> {
                buyAirtimeState.update { it.copy(amount = buyAirtimeEvent.amount) }
            }
            is BuyAirtimeEvent.PhoneNumberChanged -> {
                buyAirtimeState.update { it.copy(phoneNumber = buyAirtimeEvent.phoneNumber) }
            }
            is BuyAirtimeEvent.BuyAirtimeClicked->{
                buyAirtimeState.update { it.copy(isLoading = true) }
            }
            is BuyAirtimeEvent.TargetRadionButton->{
                buyAirtimeState.update { it.copy(targetRadio = buyAirtimeEvent.targetRadio) }
            }

        }
    }

    private fun validateInput() {
        val amount = buyAirtimeState.value.amount
        val phoneNumber = buyAirtimeState.value.phoneNumber

        val isAmountValid = amount.trim().isNotEmpty() && amount.trim().toIntOrNull() != null && amount.trim().toInt() in 5..9999
        val areDetailsFilled = when (targetRadio) {
            "myself" -> true
            "someone_else" -> phoneNumber.trim().isNotEmpty()
            else -> false
        }
        buyAirtimeState.value.isBuyAirtimeEnabled == isAmountValid && areDetailsFilled
    }


    fun updateTargetRadio(newTargetRadio: String) {
        targetRadio = newTargetRadio
        validateInput()
    }


}