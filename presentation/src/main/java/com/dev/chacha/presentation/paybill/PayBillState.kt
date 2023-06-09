package com.dev.chacha.presentation.paybill

import com.dev.chacha.presentation.contactList.Contact

data class PayBillState(
    val businessNumber: String = "",
    val accountName: String = "",
    val accountNumber: String = "",
    val amount: String = "",
    val searchParams: String = "",
    val payBillList: List<PayBill> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
){
    private val isAmountValid: Boolean
        get() = amount.replace("-", "").trim().isNotEmpty() && amount.trim().toIntOrNull() != null && amount.trim().toInt() in 1..299999
    private val areDetailsFilled: Boolean
        get() = accountNumber.trim().isNotEmpty() || businessNumber.trim().isNotEmpty()
    val isPayBillEnabled = isAmountValid && areDetailsFilled
}

