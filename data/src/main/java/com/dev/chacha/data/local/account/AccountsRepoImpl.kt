package com.dev.chacha.data.local.account

import com.dev.chacha.data.dao.AccountsDao
import com.dev.chacha.data.db.SaccoDatabase

class AccountsRepoImpl(db: SaccoDatabase): AccountsRepo {

    private val accountDao: AccountsDao = db.accountsDao()

    override fun getAllAccounts(): List<AccountEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(accounts: List<AccountEntity>): List<Long> {
        TODO("Not yet implemented")
    }
}