package com.dev.chacha.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.dev.chacha.data.local.account.AccountsEntity

@Dao
interface AccountsDao : BaseDao<AccountsEntity> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(accounts: List<AccountsEntity>): List<Long>

}