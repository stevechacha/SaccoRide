package com.dev.chacha.data.local.transaction

import androidx.room.*

@Dao
interface TransactionsDao {
    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): List<TransactionsEntity>

    @Query("SELECT * FROM transactions WHERE transactionNumber = :transactionNumber")
    fun getTransaction(transactionNumber: String): TransactionsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transactionsEntity: TransactionsEntity)

    @Delete
    fun deleteTransaction(transactionsEntity: TransactionsEntity)

    @Update
    fun updateTransaction(transactionsEntity: TransactionsEntity)

    @Query("SELECT * FROM transactions WHERE id = :id")
    fun getById(id: Long): TransactionsEntity
}