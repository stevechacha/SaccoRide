package com.dev.chacha.domain.model

import java.util.Date


/*
Transaction class:
    transaction amount (e.g. decimal or integer)
    transaction type (e.g. deposit, withdrawal, transfer,loan payment) (e.g. string or enumerated type)
    transaction date (e.g. date or datetime object)
    transaction ID (e.g. string or integer)
*/


data class Transaction(
    val transactionId: String,
    val transactionAmount: Double,
    val transactionType: TransactionType,
    val transactionDate: Date,
    val account: Account

)

enum class TransactionType {
    Deposit,
    Withdrawal,
    Transfer,
    LoanRepayment
}
