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
    TransactionsItem(
        name = "Stephen Chacha",
        contact = "0746656813",
        amount = 68700.0,
        date = "19 Apr",
        time = "19:00 PM",
        image = null
    ),
    TransactionsItem(
        name = "Mary Tenui",
        contact = "0721657645",
        amount = 10000.0,
        date = "12 Apr",
        time = "06:00 PM",
        image = null
    ),
    TransactionsItem(
        name = "Dennis Mwangi",
        contact = "0724873401",
        amount = 50000.0,
        date = "12 Apr",
        time = "12:00 PM",
        image = null
    ),
    TransactionsItem(
        name = "Nairobi Waters",
        contact = "123456",
        amount = 1000.0,
        date = "12 Apr",
        time = "12:00 PM",
        image = null
    ),
    TransactionsItem(
        name = "KCB MPESA",
        contact = "780780",
        amount = 1000.0,
        date = "24 Apr",
        time = "12:00 PM",
        image = R.drawable.ic_home
    ),
    TransactionsItem(
        name = "KCB MPESA",
        contact = "780780",
        amount = 1000.0,
        date = "24 Apr",
        time = "12:00 PM",
        image = R.drawable.ic_home
    ),
)


