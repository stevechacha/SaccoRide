package com.dev.chacha.presentation.paybill

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.contactList.Contact
import com.dev.chacha.presentation.contactList.ContactState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PayBillViewModel : ViewModel() {

    private val _payBillState = MutableStateFlow(PayBillState())
    val payBillState: StateFlow<PayBillState> = _payBillState

    private var initialPayBills = listOf<PayBill>()

    private var searchJob: Job? = null

    fun onPayBillEvent(payBillEvent: PayBillEvent) {
        when (payBillEvent) {
            PayBillEvent.GetPayBill -> {
                viewModelScope.launch {
                    try {
                        val payBills = getPayBillList()
                        initialPayBills = payBills
                        _payBillState.value = _payBillState.value.copy(
                            payBillList = initialPayBills.take(1000),
                            isLoading = false
                        )
                    } catch (e: Exception) {
                        _payBillState.value = _payBillState.value.copy(
                            error = e.message ?: "Unknown error",
                            isLoading = false
                        )
                    }
                }
            }
            is PayBillEvent.SearchPayBill -> {
                if (payBillEvent.searchParams.isNotEmpty()) {
                    _payBillState.update { it.copy(searchParams = payBillEvent.searchParams, isLoading = true) }
                    searchJob?.cancel()
                    searchJob = viewModelScope.launch {
                        delay(500L)
                        val filteredPayBills = initialPayBills.filter {
                            it.name.contains(payBillState.value.searchParams, ignoreCase = true) ||
                                    it.name.contains(payBillState.value.searchParams)
                        }
                        _payBillState.value = _payBillState.value.copy(
                            payBillList = filteredPayBills,
                            isLoading = false
                        )
                    }
                } else {
                    _payBillState.value = _payBillState.value.copy(
                        payBillList = initialPayBills.take(10),
                        isLoading = false
                    )
                }
            }
            is PayBillEvent.AccountNameChanged -> {
                _payBillState.update { it.copy(accountName = payBillEvent.accountName) }
            }
            is PayBillEvent.AccountNumberChanged -> {
                _payBillState.update { it.copy(accountNumber = payBillEvent.accountNumber) }
            }
            is PayBillEvent.AmountChanged -> {
                _payBillState.update { it.copy(amount = payBillEvent.amount.toString()) }
            }
            is PayBillEvent.BusinessNumberChanged -> {
                _payBillState.update { it.copy(businessNumber = payBillEvent.businessNumber) }
            }

            else -> {}
        }
    }

    private fun getPayBillList(): List<PayBill> {
        return listOf(
            PayBill(
                name = "PayBill 1",
                businessNumber = "123456",
                accountNumber = "987654",
                amount = 100.0,
                date = "2023-05-30",
                image = R.drawable.home_icon
            ),
            PayBill(
                name = "PayBill 2",
                businessNumber = "654321",
                accountNumber = "456789",
                amount = 200.0,
                date = "2023-05-29",
                image = R.drawable.home_icon
            ),
            // Add more PayBill objects as needed
        ).sortedBy { it.name }
    }


}
