package com.dev.chacha.data.local.account

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AccountsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(accounts: AccountEntity)

    @Delete
    fun deleteAccount(accounts: AccountEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllAccounts(accounts: List<AccountEntity>): List<Long>

    @Query("SELECT * FROM accounts WHERE id = :id")
    fun getLiveAccount(id: Int?): LiveData<AccountEntity>

    @Query("SELECT COUNT(id) FROM accounts")
    fun getDataCount(): Int

    @Update
    fun updateAccount(accounts: AccountEntity?)

    @Update
    suspend fun updateAccount(accounts: LiveData<AccountEntity>)

    @Query("DELETE FROM accounts")
    fun deleteAll()
}

