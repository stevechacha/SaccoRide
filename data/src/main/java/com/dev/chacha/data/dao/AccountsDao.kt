package com.dev.chacha.data.dao

import androidx.room.Dao
import com.dev.chacha.data.db.model.AccountsEntity

@Dao
interface AccountsDao : BaseDao<AccountsEntity> {
}