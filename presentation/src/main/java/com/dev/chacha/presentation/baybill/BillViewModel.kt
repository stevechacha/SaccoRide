package com.dev.chacha.presentation.baybill

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dev.chacha.presentation.paybill.PayBill

class BillViewModel : ViewModel() {
    private val bill = mutableListOf<PayBill>()

    private val _shouldShowDialog = mutableStateOf(false)
    val shouldShowDialog: State<Boolean> = _shouldShowDialog

    fun setShowDialogState(value: Boolean) {
        _shouldShowDialog.value = value
    }

    private fun addBill(payBill: PayBill) {
        bill.add(payBill)
    }


    fun onTransactionConfirm(){
        val payBill = PayBill(
            name = "name",
            amount = 0.0,
            accountNumber = "accountNumber",
            businessNumber = "businessNumber",
        )
        bill.add(payBill)
    }


}