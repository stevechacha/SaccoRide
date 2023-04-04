package com.dev.chacha.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dev.chacha.data.dao.AccountsDao
import com.dev.chacha.data.local.account.AccountsEntity

@Database(
    entities = [
      AccountsEntity::class
    ],
    version =1,
    exportSchema = false,
)
abstract class SaccoDatabase : RoomDatabase(){
    abstract fun accountsDao(): AccountsDao
}