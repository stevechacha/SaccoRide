package com.dev.chacha.data.local.account

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dev.chacha.domain.model.AccountType


/*
* •	account balance (e.g. decimal or integer)
•	transaction hi story (e.g. list of Transaction objects)
•	account type (e.g. checking, savings, loan) (e.g. string ,, enumerated type)
•	account ID (e.g. string or integer)
*
* */
@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey val id: Int,
    val accountId: String,
    var accountBalance: Double,
    val accountIcon: String,
    var accountType: AccountType,
    val accountHolderName: String,
    val accountNumber: String,
)

//    var transactionHistory: List<Transaction>,

