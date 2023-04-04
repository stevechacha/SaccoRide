package com.dev.chacha.data.dao

import androidx.room.Dao
import com.dev.chacha.data.local.account.AccountsEntity

@Dao
interface AccountsDao : BaseDao<AccountsEntity> {
}