package com.dev.chacha.data.local.loans

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LoansDao {
    @Query("SELECT * FROM loans")
    fun getAll(): List<LoanEntity>

    @Insert
    fun insert(loan: LoanEntity)


}
