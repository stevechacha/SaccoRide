package com.dev.chacha.presentation.markets

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MarketViewModel : ViewModel() {

    val marketState = MutableStateFlow(MarketState())

    fun onTriggerEvent(event: MarketEvent) {
        when (event) {
            is MarketEvent.GetMarket -> {
                getMarket()
            }
        }
    }

    private fun getMarket() {
        marketState.update { it.copy(isLoading = true) }
    }


}


data class MarketState(
    val isLoading: Boolean = false,
)

sealed class MarketEvent {
    object GetMarket : MarketEvent()
}
