package com.dev.chacha.presentation.paybill

sealed interface PayBillEvent{
    object GetPayBill: PayBillEvent
    data class SearchPayBill(val searchParams: String): PayBillEvent
    data class BusinessNumberChanged(val businessNumber: String):PayBillEvent
    data class AccountNameChanged(val accountName: String):PayBillEvent
    data class AccountNumberChanged(val accountNumber: String):PayBillEvent
    data class AmountChanged(val amount: String): PayBillEvent

}