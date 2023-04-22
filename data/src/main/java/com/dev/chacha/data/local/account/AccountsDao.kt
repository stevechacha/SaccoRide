package com.dev.chacha.data.local.account

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dev.chacha.data.local.account.Accounts

@Dao
interface AccountsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(accounts: Accounts)

    @Delete
    fun deleteAccount(accounts: Accounts)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllAccounts(accounts: List<Accounts>): List<Long>

    @Query("SELECT * FROM accounts WHERE id = :id")
    fun getLiveAccount(id: Int?): LiveData<Accounts>

    @Query("SELECT COUNT(id) FROM accounts")
    fun getDataCount(): Int

    @Update
    fun updateAccount(accounts: Accounts?)

    @Update
    suspend fun updateAccount(accounts: LiveData<Accounts>)

    @Query("DELETE FROM accounts")
    fun deleteAll()
}

