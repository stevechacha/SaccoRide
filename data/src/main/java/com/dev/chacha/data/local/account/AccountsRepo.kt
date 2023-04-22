package com.dev.chacha.data.local.account

interface AccountsRepo {
    fun getAllAccounts(): List<Accounts>

    suspend fun insert(accounts: List<Accounts>): List<Long>


}