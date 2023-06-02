package com.dev.chacha.presentation.bank_transfer


data class BankTransferState(
    val bankList: List<BankList> = emptyList(),
    val bankName: String = "",
    val accountList:List<AccountList> = emptyList(),
    val accountName: String ="",
    val searchParams: String = "",
    val amount: String = "",
    val transferDescription: String ="",
    val isLoading: Boolean = false,
    val error: String = ""
)
