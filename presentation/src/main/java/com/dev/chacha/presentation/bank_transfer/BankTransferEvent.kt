package com.dev.chacha.presentation.bank_transfer

sealed interface BankTransferEvent{
    object GetBankList: BankTransferEvent
    object GetAccountList: BankTransferEvent
    object BankTransfer : BankTransferEvent
    data class BankNameChanged(val bankName: String): BankTransferEvent
    data class  AccountNameChanged(val accountName: String): BankTransferEvent
    data class SearchBank(val searchParams: String): BankTransferEvent
    data class AmountChanged(val amount: String): BankTransferEvent
    data class TransferDescriptionChanged(val  transferDescription: String): BankTransferEvent
}