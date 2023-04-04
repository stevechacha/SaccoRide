package com.dev.chacha.data.local.account

import androidx.room.Entity
import com.dev.chacha.domain.model.Transaction


/*
* •	account balance (e.g. decimal or integer)
•	transaction hi story (e.g. list of Transaction objects)
•	account type (e.g. checking, savings, loan) (e.g. string ,, enumerated type)
•	account ID (e.g. string or integer)
*
* */
@Entity(tableName = "accounts")
data class Accounts(
    val id: Int,
    val accountId: String,
    var accountBalance: Double,
    val accountIcon: String,
    var transactionHistory: List<Transaction>,
    var accountType: String,
    val accountHolderName: String,
    val accountNumber: String,
)
