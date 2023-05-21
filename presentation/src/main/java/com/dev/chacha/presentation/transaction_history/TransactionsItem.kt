package com.dev.chacha.presentation.transaction_history

import com.dev.chacha.presentation.R


data class TransactionsItem(
    val name: String,
    val contact: String,
    val amount: Double,
    val date: String,
    val time: String,
    val image: Int? = null
)

val transactionsItem = listOf(
    TransactionsItem("John Doe", "0746656813", 100.0, "2023-05-19", "10:30 AM"),
    TransactionsItem("Jane Smith", "+254746656813", 150.0, "2023-05-19", "11:45 AM"),
    TransactionsItem("Alice Johnson", "254746656813", 200.0, "2023-05-19", "09:15 AM"),
    TransactionsItem("Bob Brown", "0712345678", 75.0, "2023-05-19", "02:00 PM"),
    TransactionsItem("Eve Wilson", "254712345678", 50.0, "2023-05-19", "12:30 PM"),
    TransactionsItem("Eve Wilson", "0112345678", 50.0, "2023-05-19", "12:30 PM"),
    TransactionsItem("Nairobi Waters", "123456", 1000.0, "2023-05-12", "12:00 PM"),
    TransactionsItem("KCB MPESA", "780780", 1000.0, "2023-05-24", "12:00 PM", R.drawable.ic_home),
)





