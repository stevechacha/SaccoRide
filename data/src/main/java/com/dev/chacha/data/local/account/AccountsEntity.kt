package com.dev.chacha.data.local.account

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int
)
