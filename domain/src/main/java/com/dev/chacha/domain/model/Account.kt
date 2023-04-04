package com.dev.chacha.domain.model


/*

Account class:
    account balance (e.g. decimal or integer)
    transaction history (e.g. list of Transaction objects)
    account type (e.g. checking, savings, loan) (e.g. string or enumerated type)
    account ID (e.g. string or integer)



*/


data class Account(
    val accountId: String,
    val balance: Double,
    val transactionHistory: List<Transaction>,
    val accountType: AccountType
) {
    fun deposits() {
    }

    fun widthrawals() {

    }
}


enum class AccountType {
    Checking,
    Savings,
    Loan
}

