package com.dev.chacha.data.local.account

interface AccountsRepo {
    fun getAllAccounts(): List<AccountEntity>

    suspend fun insert(accounts: List<AccountEntity>): List<Long>


}