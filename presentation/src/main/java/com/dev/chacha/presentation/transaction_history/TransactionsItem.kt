package com.dev.chacha.presentation.transaction_history


data class TransactionsItem(
    val name: String,
    val contact: String,
    val amount: String,
    val date: String,
    val time: String,
    val image: Int? = null
)

