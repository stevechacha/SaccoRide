package com.dev.chacha.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int
)
