package com.dev.chacha.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.dev.chacha.data.local.account.AccountEntity

@Dao
interface AccountsDao : BaseDao<AccountEntity> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(accounts: List<AccountEntity>): List<Long>

}