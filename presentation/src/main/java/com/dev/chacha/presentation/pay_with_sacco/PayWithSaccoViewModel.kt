package com.dev.chacha.presentation.pay_with_sacco

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PayWithSaccoViewModel: ViewModel() {
    private val _targetPayWithSacco = MutableStateFlow("paybill")
    var targetPayWithSacco =_targetPayWithSacco.asStateFlow().value

    private val _buyGoodsWithSacco = MutableStateFlow("buygoods")
    var buyGoodsWithSacco =_buyGoodsWithSacco.asStateFlow().value

    fun handlePayWithSaccoEvent(payWithSaccoEvent: PayWithSaccoEvent) {
        when(payWithSaccoEvent){
            is PayWithSaccoEvent.PayWithSaccoClicked->{
                _targetPayWithSacco.value = payWithSaccoEvent.targetPayWithSacco
            }
        }
    }
}

sealed class PayWithSaccoEvent{
    data class PayWithSaccoClicked(val targetPayWithSacco: String): PayWithSaccoEvent()
}